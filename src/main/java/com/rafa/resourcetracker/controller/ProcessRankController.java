package com.rafa.resourcetracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafa.resourcetracker.dto.MonitorDTO;
import com.rafa.resourcetracker.dto.ProcessRankDTO;
import com.rafa.resourcetracker.entity.ProcessRankEntity;
import com.rafa.resourcetracker.service.ProcessRankService;

@RestController
public class ProcessRankController {
    @Autowired
    ProcessRankService processRankService = new ProcessRankService();

    @Autowired
    private SimpMessagingTemplate template;

    // @Scheduled(fixedDelay=30000)
    // @Async
	// public void updateProcessList() {
    //     processRankService.updateProcessRank();
	// }

    @GetMapping("/processRank")
    public List<ProcessRankDTO> updateRank() {
        List<ProcessRankDTO> processRankDto = processRankService.updateProcessRank();
        return processRankDto;
    }

    @GetMapping("/testOrderBy")
    public List<ProcessRankEntity> getProcessRank() {
        List<ProcessRankEntity> processRank = processRankService.test();
        return processRank;
    }
}
