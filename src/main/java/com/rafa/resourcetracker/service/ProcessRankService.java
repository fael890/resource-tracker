package com.rafa.resourcetracker.service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    
    public List<ProcessRankDTO> updateProcessRank(){
        List<ProcessDTO> processListDto = processService.getProcessList();
        List<ProcessRankEntity> processRank = processRankRepository.findAll();

        processListDto.forEach(p -> processRank.add(new ProcessRankEntity(
            p.getPid(), 
            p.getName(), 
            p.getCpuUsage(), 
            p.getMemoryUsage(), 
            p.getDiskReadUsage(), 
            p.getDiskWriteUsage(), 
            LocalDateTime.now()
        )));

        processRank.sort(Comparator.comparing(ProcessRankEntity::getCpuUsage).reversed());

        System.out.println(processRank.get(0));
        System.out.println("=======================================");
        System.out.println(processRankRepository.save(processRank.get(0)));
        System.out.println("=======================================");

        List<ProcessRankDTO> processRankDto = processRank.stream().map(p -> new ProcessRankDTO(p)).toList();

        return processRankDto;
    }

    public List<ProcessRankEntity> test(){
        List<ProcessRankEntity> processRank = processRankRepository.findAllOrderByCpuUsage();
        
        return processRank;
    }
}
