package com.rafa.resourcetracker.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;

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

    private int numberOfLogicalCPU = processor.getLogicalProcessorCount();

    public List<ProcessDTO> fetchProcessList(){
        List<OSProcess> osProcessList = os.getProcesses(null, ProcessSorting.CPU_DESC, 20);
        LocalDateTime timestamp = LocalDateTime.now();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return osProcessList.stream().map(
            process -> {
                OSProcess secondSnapshot = os.getProcess(process.getProcessID());
                
                return new ProcessDTO(
                    process.getProcessID(), 
                    process.getName(),
                    (calculateProcessCpuUsage(process, secondSnapshot)),
                    process.getResidentSetSize() / (1024.0 * 1024.0),
                    process.getBytesRead() / (1024.0 * 1024.0),
                    process.getBytesWritten() / (1024.0 * 1024.0),
                    timestamp,
                    null
                );
            }
        ).toList();
    }

    public List<ProcessDTO> getProcessList(){
        return fetchProcessList();
    }

    public double calculateProcessCpuUsage(OSProcess firstProcessSnapshot, OSProcess secondProcessSnapshot){
        if (secondProcessSnapshot == null) {
            return 0;
        }

        double cpuUsage = secondProcessSnapshot.getProcessCpuLoadBetweenTicks(firstProcessSnapshot);

        return (cpuUsage / this.numberOfLogicalCPU) * 100;
    }

    // public List<ProcessDTO> getChildProcessList(OSProcess process){
    //     List<OSProcess> childProcesses = os.getDescendantProcesses(process.getProcessID(), null, null, 0);
    //     List<ProcessDTO> childProcessesDTO;
    //     LocalDateTime now = LocalDateTime.now();
    //     childProcessesDTO = childProcesses.stream().map(
    //         cp -> new ProcessDTO(
    //             cp.getProcessID(),
    //             cp.getName(),
    //             (cp.getProcessCpuLoadBetweenTicks(cp)*100d)/processor.getLogicalProcessorCount(),
    //             convertBytesToMB(cp.getResidentSetSize()),
    //             convertBytesToMB(cp.getBytesRead()),
    //             convertBytesToMB(cp.getBytesWritten()),
    //             now
    //         )
    //     ).toList();

    //     return childProcessesDTO;
    // }
}
