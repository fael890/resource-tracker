package com.rafa.resourcetracker.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.stereotype.Service;

import com.rafa.resourcetracker.dto.GPUMonitorDTO;

@Service
public class GPUMonitorService {
    public GPUMonitorDTO nvidiaGpuUsageMonitor(){
        ProcessBuilder builder = new ProcessBuilder("powershell", "-Command", "nvidia-smi --query-gpu=utilization.gpu,memory.used,memory.total,temperature.gpu --format=csv");
        builder.redirectErrorStream(true);
        try {
            Process process = builder.start();

            try(BufferedReader r = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line = r.readLine();
                line = r.readLine();
                if(line == null) {
                    throw new IOException("nvidia-smi returns null");
                }
                String[] result = line.split(",");
                if(result.length < 4){
                    throw new IOException("nividia-smi returns irregular data");
                }

                return new GPUMonitorDTO(
                    result[0].trim(), 
                    result[1].trim(), 
                    result[2].trim(), 
                    result[3].trim()
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
