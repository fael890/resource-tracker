package com.rafa.resourcetracker.dto;

import com.rafa.resourcetracker.entity.ProcessEntity;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;

public class ProcessDTO {
    private Long id;
    private String name;
    private double cpuUsage;
    private double gpuUsage;
    private double memoryUsage;
    private double diskReadUsage;
    private double diskWriteUsage;
    private double networkUsage;
    private LocalDateTime timestamp;

    public ProcessDTO(ProcessEntity entity){
        BeanUtils.copyProperties(entity, this);
        // entity.getId(); 
        // entity.getName(); 
        // entity.getCpuUsage(); 
        // entity.getGpuUsage(); 
        // entity.getMemoryUsage(); 
        // entity.getDiskUsage(); 
        // entity.getNetworkUsage(); 
        // entity.getTimestamp();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCpuUsage() {
        return cpuUsage;
    }

    public void setCpuUsage(double cpuUsage) {
        this.cpuUsage = cpuUsage;
    }

    public double getGpuUsage() {
        return gpuUsage;
    }

    public void setGpuUsage(double gpuUsage) {
        this.gpuUsage = gpuUsage;
    }

    public double getMemoryUsage() {
        return memoryUsage;
    }

    public void setMemoryUsage(double memoryUsage) {
        this.memoryUsage = memoryUsage;
    }

    public double getDiskReadUsage() {
        return diskReadUsage;
    }

    public void setDiskReadUsage(double readDiskUsage) {
        this.diskReadUsage = readDiskUsage;
    }

    public double getDiskWriteUsage() {
        return diskWriteUsage;
    }

    public void setDiskWriteUsage(double writeDiskUsage) {
        this.diskWriteUsage = writeDiskUsage;
    }

    public double getNetworkUsage() {
        return networkUsage;
    }

    public void setNetworkUsage(double networkUsage) {
        this.networkUsage = networkUsage;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    
}
