package com.rafa.resourcetracker.dto;

public class GPUMonitorDTO {
    private String gpuUsage;
    private String vramUsage;
    private String totalVram;
    private String temperature;

    public GPUMonitorDTO(String gpuUsage, String vramUsage, String totalVram, String temperature) {
        this.gpuUsage = gpuUsage;
        this.vramUsage = vramUsage;
        this.totalVram = totalVram;
        this.temperature = temperature;
    }

    public String getGpuUsage() {
        return gpuUsage;
    }

    public String getVramUsage() {
        return vramUsage;
    }

    public String getTotalVram() {
        return totalVram;
    }

    public String getTemperature() {
        return temperature;
    }

    
}
