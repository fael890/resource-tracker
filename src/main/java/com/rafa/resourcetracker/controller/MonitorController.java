package com.rafa.resourcetracker.controller;

import org.springframework.web.bind.annotation.RestController;

import com.rafa.resourcetracker.dto.MonitorDTO;
import com.rafa.resourcetracker.dto.ProcessDTO;
import com.rafa.resourcetracker.service.GPUMonitorService;
import com.rafa.resourcetracker.service.ProcessMonitorService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class MonitorController {
    @Autowired
    private ProcessMonitorService processService = new ProcessMonitorService();
    @Autowired
    private GPUMonitorService gpuMonitorService = new GPUMonitorService();

    @Autowired
    private SimpMessagingTemplate template;

    @Scheduled(fixedDelay=1000)
    @Async
	public void updateProcessList() {
        MonitorDTO monitor = new MonitorDTO(processService.getProcessList(), gpuMonitorService.nvidiaGpuUsageMonitor());

		this.template.convertAndSend("/topic/monitor", monitor);
	}

    @GetMapping("/testProcessList")
    public List<ProcessDTO> getProcessList() {
        return processService.getProcessList();
    }
    
}
