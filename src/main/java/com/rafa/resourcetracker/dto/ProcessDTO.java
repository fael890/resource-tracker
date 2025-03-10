package com.rafa.resourcetracker.dto;

import com.rafa.resourcetracker.entity.ProcessEntity;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;

public class ProcessDTO {
    private int pid;
    private String name;
    private double cpuUsage;
    private double ramUsage;
    private double diskReadUsage;
    private double diskWriteUsage;
    private LocalDateTime timestamp;

    public ProcessDTO(ProcessEntity entity){
        BeanUtils.copyProperties(entity, this);
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

    public void setDiskReadUsage(double readDiskUsage) {
        this.diskReadUsage = readDiskUsage;
    }

    public double getDiskWriteUsage() {
        return diskWriteUsage;
    }

    public void setDiskWriteUsage(double writeDiskUsage) {
        this.diskWriteUsage = writeDiskUsage;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    
}
