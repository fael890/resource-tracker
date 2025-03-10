package com.rafa.resourcetracker.controller;

import org.springframework.web.bind.annotation.RestController;

import com.rafa.resourcetracker.dto.MonitorDTO;
import com.rafa.resourcetracker.service.GPUMonitorService;
import com.rafa.resourcetracker.service.ProcessMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;

@RestController
public class MonitorController {
    @Autowired
    private ProcessMonitorService processService = new ProcessMonitorService();
    @Autowired
    private GPUMonitorService gpuMonitorService = new GPUMonitorService();

    @Autowired
    private SimpMessagingTemplate template;

    @Scheduled(fixedDelay=1000)
	public void updateProcessList() {
        MonitorDTO monitor = new MonitorDTO(processService.getProcessList(), gpuMonitorService.nvidiaGpuUsageMonitor());
        System.out.println(monitor.getGpuMonitor().getGpuUsage());

		this.template.convertAndSend("/topic/monitor", monitor);
	}
}
