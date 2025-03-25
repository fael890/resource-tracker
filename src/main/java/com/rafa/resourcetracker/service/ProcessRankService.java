package com.rafa.resourcetracker.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rafa.resourcetracker.dto.ProcessDTO;
import com.rafa.resourcetracker.dto.ProcessRankDTO;
import com.rafa.resourcetracker.entity.ProcessRankEntity;
import com.rafa.resourcetracker.repository.ProcessRankRepository;

@Service
public class ProcessRankService {
    @Autowired
    private ProcessRankRepository processRankRepository;

    @Autowired
    private ProcessMonitorService processService;

    @Transactional
    public List<ProcessRankDTO> updateProcessRank(){
        List<ProcessDTO> processListDto = processService.getProcessList();
        List<ProcessRankEntity> processRank = processRankRepository.findAll();

        processListDto.forEach(p -> processRank.add(new ProcessRankEntity(
            p.getPid(), 
            p.getName(), 
            p.getCpuUsage(), 
            p.getRamUsage(), 
            p.getDiskReadUsage(), 
            p.getDiskWriteUsage(), 
            LocalDateTime.now()
        )));

        processRank.sort(Comparator.comparing(ProcessRankEntity::getCpuUsage).reversed());
 
        List<ProcessRankEntity> filteredProcessRankList = new ArrayList<>();

        // remove equal processes in process rank
        for (ProcessRankEntity process : processRank) {
            if(!containsNameAndPid(filteredProcessRankList, process.getName(), process.getPid())){
                filteredProcessRankList.add(process);
            }
        }

        processRank.clear();
        
        // if pid and process exists in table process rank update row, else save a new process
        for (ProcessRankEntity process : filteredProcessRankList) {
            Optional<ProcessRankEntity> existingProcess = processRankRepository.findByPidAndName(process.getPid(), process.getName());

            if (existingProcess.isPresent()) {
                updateByPidAndName(process.getPid(), process);
            } else {
                processRankRepository.save(process);
            }
            processRankRepository.flush();
        }
       
        List<ProcessRankDTO> processRankDto = filteredProcessRankList.stream().map(p -> new ProcessRankDTO(p)).toList();

        return processRankDto;
    }

    public List<ProcessRankDTO> getAllProcessesOrderByCpuUsage(){
        List<ProcessRankEntity> processList = processRankRepository.findAllByOrderByCpuUsageDesc();

        return processList.stream().map(process -> new ProcessRankDTO(process)).toList();
    }

    @Transactional
    public void updateByPidAndName(int pid, ProcessRankEntity process){
        processRankRepository.updateByPidAndName(
            process.getPid(), 
            process.getName(), 
            process.getCpuUsage(), 
            process.getRamUsage(), 
            process.getDiskReadUsage(), 
            process.getDiskWriteUsage(), 
            LocalDateTime.now()
        );
    }

    @Transactional
    public void resetRank(){
        processRankRepository.deleteAll();
    }

    public boolean containsNameAndPid(final List<ProcessRankEntity> processes, final String name, final Integer pid){
        return processes.stream().anyMatch(p -> name.equals(p.getName()) && pid.equals(p.getPid()));
    }
}
