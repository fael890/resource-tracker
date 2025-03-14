package com.rafa.resourcetracker.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.HashSet;
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
        List<OSProcess> osProcessList = os.getProcesses(null, ProcessSorting.RSS_DESC, 20);
        List<ProcessDTO> processList;

        LocalDateTime now = LocalDateTime.now();
        processList = osProcessList.stream()
        .map(
            process -> new ProcessDTO(
                process.getProcessID(), 
                process.getName(),
                (process.getProcessCpuLoadBetweenTicks(process)*100d)/processor.getLogicalProcessorCount(),
                convertBytesToMB(process.getResidentSetSize()),
                convertBytesToMB(process.getBytesRead()),
                convertBytesToMB(process.getBytesWritten()),
                now,
                getChildProcessList(process)
            ) 
        ).toList();

        return processList;
    }

    public double convertBytesToMB(double value){
        return value / (1024 * 1024);
    }

    public List<ProcessDTO> getChildProcessList(OSProcess process){
        List<OSProcess> childProcesses = os.getDescendantProcesses(process.getProcessID(), null, null, 0);
        List<ProcessDTO> childProcessesDTO;
        LocalDateTime now = LocalDateTime.now();
        childProcessesDTO = childProcesses.stream().map(
            cp -> new ProcessDTO(
                cp.getProcessID(),
                cp.getName(),
                (cp.getProcessCpuLoadBetweenTicks(cp)*100d)/processor.getLogicalProcessorCount(),
                convertBytesToMB(cp.getResidentSetSize()),
                convertBytesToMB(cp.getBytesRead()),
                convertBytesToMB(cp.getBytesWritten()),
                now
            )
        ).toList();

        return childProcessesDTO;
    }

    
}
