package com.rafa.resourcetracker.entity.gpu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NvidiaMonitor implements GPUMonitor{
    private String gpuUsage;
    private String vramUsage;
    private String totalVram;

    @Override
    public NvidiaMonitor gpuUsageMonitor(){
        // try {
        //     ProcessBuilder builder = new ProcessBuilder("powershell", "-Command", "nvidia-smi --query-gpu=utilization.gpu,memory.used,memory.total,temperature.gpu --format=csv");
        //     builder.redirectErrorStream(true);
        //     Process p;
        //     p = builder.start();
        //     BufferedReader r = new BufferedReader(new InputStreamReader(p.getInputStream()));
        //     Stream<String> lines = r.lines();

        //     String result = lines.collect(Collectors.joining("\n"));
        //     return result;
            
        // } catch (IOException e) {
        //     e.printStackTrace();
        //     return e.getMessage();
        // }
        return this;
    }
}
