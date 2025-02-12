package com.rafa.resourcetracker.controller;

import org.springframework.web.bind.annotation.RestController;

import com.rafa.resourcetracker.dto.ProcessDTO;
import com.rafa.resourcetracker.service.ProcessService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;


@RestController
public class ProcessController {
    @Autowired
    private ProcessService processService = new ProcessService();

    @Autowired
    private SimpMessagingTemplate template;

    @Scheduled(fixedDelay=2000)
	public void updateProcessList() {
        List<ProcessDTO> processList = processService.getProcessList();
		this.template.convertAndSend("/topic/process", processList);
	}
}
