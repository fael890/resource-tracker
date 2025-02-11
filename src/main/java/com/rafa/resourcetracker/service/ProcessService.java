package com.rafa.resourcetracker.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.rafa.resourcetracker.dto.ProcessDTO;
import com.rafa.resourcetracker.entity.Process;
import oshi.SystemInfo;
import oshi.software.os.OSProcess;
import oshi.software.os.OperatingSystem;
import oshi.software.os.OperatingSystem.ProcessSorting;

@Service
public class ProcessService {
    private SystemInfo systemInfo = new SystemInfo();
    private OperatingSystem os = systemInfo.getOperatingSystem();

    public List<ProcessDTO> getProcessList(){
        List<OSProcess> osProcessList = os.getProcesses(null, ProcessSorting.CPU_DESC, 10);
        List<ProcessDTO> processList;
        
        processList = osProcessList.stream()
        .map(process -> new Process(
            process.getProcessID(), 
            process.getName(), 
            process.getProcessCpuLoadCumulative(),
            0.0,
            process.getResidentSetSize(),
            0.0,
            0.0,
            null
        ))
        .map(entity -> new ProcessDTO(entity)).toList();

        return processList;
    }
}
