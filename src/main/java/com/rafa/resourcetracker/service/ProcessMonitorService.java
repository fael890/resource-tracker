package com.rafa.resourcetracker.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import com.rafa.resourcetracker.dto.ProcessDTO;
import com.rafa.resourcetracker.entity.ProcessEntity;

import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;
import oshi.software.os.OperatingSystem.ProcessSorting;

@Service
public class ProcessMonitorService{
    private SystemInfo systemInfo = new SystemInfo();
    private OperatingSystem os = systemInfo.getOperatingSystem();
    private CentralProcessor processor = systemInfo.getHardware().getProcessor();

    public List<ProcessDTO> getProcessList(){
        List<OSProcess> osProcessList = os.getProcesses(null, ProcessSorting.CPU_DESC, 10);
        List<ProcessDTO> processList;
        LocalDateTime now = LocalDateTime.now();
        processList = osProcessList.stream()
        .map(
            process -> new ProcessEntity(
                process.getProcessID(), 
                process.getName(),
                (process.getProcessCpuLoadBetweenTicks(process)*100d)/processor.getLogicalProcessorCount(),
                convertBytesToMB(process.getResidentSetSize()),
                convertBytesToMB(process.getBytesRead()),
                convertBytesToMB(process.getBytesWritten()),
                now
            ) 
        )
        .map(entity -> new ProcessDTO(entity)).toList();

        return processList;
    }

    public double convertBytesToMB(double value){
        return value / (1024 * 1024);
    }
}
