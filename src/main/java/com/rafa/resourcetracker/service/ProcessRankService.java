package com.rafa.resourcetracker.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private ProcessService processService;

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

        Map<String, Integer> tempHash = new HashMap<>();
        List<ProcessRankEntity> tempFilteredProcessRankList = new ArrayList<>();

        for (ProcessRankEntity process : processRank) {
            if (!tempHash.containsKey(process.getName())) {
                tempHash.put(process.getName(), 0);
                tempFilteredProcessRankList.add(process);
            } 
        }

        processRank.clear();
        processRank.addAll(tempFilteredProcessRankList);

        Long id = 1L;
        for (ProcessRankEntity process : processRank) {
            if (processRankRepository.existsById(id)) {
                updateProcessByIdInProcessRank(id, process);
            } else {
                processRankRepository.save(process);
            }
            if(processRankRepository.existsByName(process.getName())){
                updateProcessByIdInProcessRank(process.getName(), process);
                
            }
            id++;
        }

        List<ProcessRankDTO> processRankDto = processRank.stream().map(p -> new ProcessRankDTO(p)).toList();

        return processRankDto;
    }

    @Transactional
    public void updateProcessByIdInProcessRank(Long id, ProcessRankEntity process) {
        processRankRepository.updateById(
            id,
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
    public void updateProcessByIdInProcessRank(String name, ProcessRankEntity process) {
        processRankRepository.updateByName(
            process.getPid(), 
            name, 
            process.getCpuUsage(), 
            process.getRamUsage(), 
            process.getDiskReadUsage(), 
            process.getDiskWriteUsage(), 
            LocalDateTime.now()
            );
    }

    public List<ProcessRankEntity> test(){
        List<ProcessRankEntity> processRank = processRankRepository.findAllOrderByCpuUsage();
        
        return processRank;
    }
}
