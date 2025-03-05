package com.rafa.resourcetracker.controller;

import org.springframework.web.bind.annotation.RestController;

import com.rafa.resourcetracker.dto.ProcessDTO;
import com.rafa.resourcetracker.service.ProcessService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class ProcessController {
    @Autowired
    private ProcessService processService = new ProcessService();

    @Autowired
    private SimpMessagingTemplate template;

    @Scheduled(fixedDelay=1000)
	public void updateProcessList() {
        List<ProcessDTO> processList = processService.getProcessList();
		this.template.convertAndSend("/topic/process", processList);
	}

    @GetMapping("/testGpu")
    public void getGpuUsage() {
        try {
            processService.getGpuUsage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/testNetwork")
    public void getNetworkUsage() {
        try {
            processService.getNetworkUsage(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
