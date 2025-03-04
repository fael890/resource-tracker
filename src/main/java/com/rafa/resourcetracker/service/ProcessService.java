package com.rafa.resourcetracker.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;
import com.rafa.resourcetracker.dto.ProcessDTO;
import com.rafa.resourcetracker.entity.ProcessEntity;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;
import oshi.software.os.OperatingSystem.ProcessSorting;

@Service
public class ProcessService{
    private SystemInfo systemInfo = new SystemInfo();
    private OperatingSystem os = systemInfo.getOperatingSystem();
    private CentralProcessor processor = systemInfo.getHardware().getProcessor();

    public List<ProcessDTO> getProcessList(){
        List<OSProcess> osProcessList = os.getProcesses(null, ProcessSorting.CPU_DESC, 10);
        List<ProcessDTO> processList;
        LocalDateTime now = LocalDateTime.now();
        processList = osProcessList.stream()
        .map(
            process -> new ProcessEntity(
                process.getProcessID(), 
                process.getName(),
                (process.getProcessCpuLoadBetweenTicks(process)*100d)/processor.getLogicalProcessorCount(),
                getGpuUsage(),
                convertBytesToMB(process.getResidentSetSize()),
                convertBytesToMB(process.getBytesRead()),
                convertBytesToMB(process.getBytesWritten()),
                0.0,
                now
            ) 
        )
        .map(entity -> new ProcessDTO(entity)).toList();

        return processList;
    }

    public String getGpuUsage() {
        try {
            ProcessBuilder builder = new ProcessBuilder("powershell", "-Command", "nvidia-smi --query-gpu=utilization.gpu,memory.used,memory.total,temperature.gpu --format=csv");
            builder.redirectErrorStream(true);
            Process p;
            p = builder.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            Stream<String> lines = r.lines();

            String result = lines.collect(Collectors.joining("\n"));
            return result;
            
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    public void getNetworkUsage(int pid){
        //System.out.println("HERE1");
        try {
            String command = "netstat -ano";
            ProcessBuilder builder = new ProcessBuilder("powershell", "-Command", command);
            Process p = builder.start();
            BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
            Stream<String> lines = r.lines();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public double convertBytesToMB(double value){
        return value / (1024 * 1024);
    }
}
