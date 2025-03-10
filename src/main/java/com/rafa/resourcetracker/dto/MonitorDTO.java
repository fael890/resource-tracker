package com.rafa.resourcetracker.dto;

import java.time.LocalDateTime;
import java.util.List;

public class MonitorDTO {
    private List<ProcessDTO> processList;
    private GPUMonitorDTO gpuMonitor;
    private LocalDateTime timestamp;

    public MonitorDTO(List<ProcessDTO> processList, GPUMonitorDTO gpuMonitor) {
        this.processList = processList;
        this.gpuMonitor = gpuMonitor;
        this.timestamp = LocalDateTime.now();
    }

    public List<ProcessDTO> getProcessList() {
        return processList;
    }

    public void setProcessList(List<ProcessDTO> processList) {
        this.processList = processList;
    }

    public GPUMonitorDTO getGpuMonitor() {
        return gpuMonitor;
    }

    public void setGpuMonitor(GPUMonitorDTO gpuMonitor) {
        this.gpuMonitor = gpuMonitor;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestampw(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    
}
