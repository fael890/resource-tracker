package com.rafa.resourcetracker.entity;

import java.time.LocalDateTime;

public class ProcessEntity {
    private int pid;
    private String name;
    private double cpuUsage;
    private double ramUsage;
    private double diskReadUsage;
    private double diskWriteUsage;
    private LocalDateTime timestamp;

    public ProcessEntity() { }

    public ProcessEntity(int pid, String name, double cpuUsage, double ramUsage, double diskReadUsage, double diskWriteUsage, LocalDateTime timestamp) {
        this.pid = pid;
        this.name = name;
        this.cpuUsage = cpuUsage;
        this.ramUsage = ramUsage;
        this.diskReadUsage = diskReadUsage;
        this.diskWriteUsage = diskWriteUsage;
        this.timestamp = timestamp;
    }

    public int getPid() {
        return pid;
    }
    public void setPid(int pid) {
        this.pid = pid;
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
    public double getRamUsage() {
        return ramUsage;
    }
    public void setRamUsage(double ramUsage) {
        this.ramUsage = ramUsage;
    }
    public double getDiskReadUsage() {
        return diskReadUsage;
    }
    public void setDiskReadUsage(double diskReadUsage) {
        this.diskReadUsage = diskReadUsage;
    }
    public double getDiskWriteUsage() {
        return diskWriteUsage;
    }
    public void setDiskWriteUsage(double diskWriteUsage) {
        this.diskWriteUsage = diskWriteUsage;
    }
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    
}
