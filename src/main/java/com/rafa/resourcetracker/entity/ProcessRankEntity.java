package com.rafa.resourcetracker.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "process_rank")
public class ProcessRankEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int pid;
    @Column(columnDefinition = "TEXT")
    private String name;
    private double cpuUsage;
    private double ramUsage;
    private double diskReadUsage;
    private double diskWriteUsage;
    private LocalDateTime lastUpdate;

    public ProcessRankEntity(){ }
    

    public ProcessRankEntity(int pid, String name, double cpuUsage, double ramUsage, double diskReadUsage,
            double diskWriteUsage, LocalDateTime lastUpdate) {
        this.pid = pid;
        this.name = name;
        this.cpuUsage = cpuUsage;
        this.ramUsage = ramUsage;
        this.diskReadUsage = diskReadUsage;
        this.diskWriteUsage = diskWriteUsage;
        this.lastUpdate = lastUpdate;
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
    public void setTimestamp(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        ProcessRankEntity other = (ProcessRankEntity) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    
}
