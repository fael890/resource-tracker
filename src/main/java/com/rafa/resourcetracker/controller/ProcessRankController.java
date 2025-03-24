package com.rafa.resourcetracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafa.resourcetracker.dto.ProcessRankDTO;
import com.rafa.resourcetracker.service.ProcessRankService;

@RestController
@CrossOrigin
public class ProcessRankController {
    @Autowired
    ProcessRankService processRankService = new ProcessRankService();

    @GetMapping("/rank/cpu-usage")
    public List<ProcessRankDTO> getProcessRank() {
        List<ProcessRankDTO> processRankDto = processRankService.getAllProcesses();
        return processRankDto;
    }

    @DeleteMapping("/rank/reset")
    public void resetProcessRank(){
        processRankService.resetRank();
    }

    @Scheduled(fixedDelay=30000)
    @Async
	public void updateProcessRank() {
        processRankService.updateProcessRank();
	}
}
