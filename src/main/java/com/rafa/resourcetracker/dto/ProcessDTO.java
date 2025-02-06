package com.rafa.resourcetracker.dto;

import com.rafa.resourcetracker.entity.Process;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;

public class ProcessDTO {
    private Long id;
    private String name;
    private double cpuUsage;
    private double gpuUsage;
    private double memoryUsage;
    private double diskUsage;
    private double networkUsage;
    private LocalDateTime timestamp;

    public ProcessDTO(Process entity){
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
}
