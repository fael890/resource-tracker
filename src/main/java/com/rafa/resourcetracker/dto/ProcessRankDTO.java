package com.rafa.resourcetracker.dto;

import java.time.LocalDateTime;

import com.rafa.resourcetracker.entity.ProcessRankEntity;

public class ProcessRankDTO {
    private Long id;
    private int pid;
    private String name;
    private double cpuUsage;
    private double ramUsage;
    private double diskReadUsage;
    private double diskWriteUsage;
    private LocalDateTime lastUpdate;
    
    public ProcessRankDTO() { }

    public ProcessRankDTO(ProcessDTO entity) {
        this.pid = entity.getPid();
        this.name = entity.getName();
        this.cpuUsage = entity.getCpuUsage();
        this.ramUsage = entity.getRamUsage();
        this.diskReadUsage = entity.getDiskReadUsage();
        this.diskWriteUsage = entity.getDiskWriteUsage();
        this.lastUpdate = LocalDateTime.now();
    }

    public ProcessRankDTO(ProcessRankEntity entity) {
        this.pid = entity.getPid();
        this.name = entity.getName();
        this.cpuUsage = entity.getCpuUsage();
        this.ramUsage = entity.getRamUsage();
        this.diskReadUsage = entity.getDiskReadUsage();
        this.diskWriteUsage = entity.getDiskWriteUsage();
        this.lastUpdate = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    
}
