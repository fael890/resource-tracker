package com.rafa.resourcetracker.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;

public class Process {
    private int id;
    private String name;
    private double cpuUsage;
    private double gpuUsage;
    private double memoryUsage;
    private double diskUsage;
    private double networkUsage;
    private LocalDateTime timestamp;

    public Process() { }

    public Process(int id, String name, double cpuUsage, double gpuUsage, double memoryUsage, double diskUsage,
            double networkUsage, LocalDateTime timestamp) {
        this.id = id;
        this.name = name;
        this.cpuUsage = cpuUsage;
        this.gpuUsage = gpuUsage;
        this.memoryUsage = memoryUsage;
        this.diskUsage = diskUsage;
        this.networkUsage = networkUsage;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
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
    public double getDiskUsage() {
        return diskUsage;
    }
    public void setDiskUsage(double diskUsage) {
        this.diskUsage = diskUsage;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Process other = (Process) obj;
        if (id != other.id)
            return false;
        return true;
    }

   

    
}
