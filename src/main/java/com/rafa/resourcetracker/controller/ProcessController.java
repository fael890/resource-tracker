package com.rafa.resourcetracker.controller;

import org.springframework.web.bind.annotation.RestController;

import com.rafa.resourcetracker.dto.ProcessDTO;
import com.rafa.resourcetracker.service.ProcessService;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class ProcessController {
    private ProcessService processService = new ProcessService();

    @GetMapping("/test")
    public List<ProcessDTO> getProcessList() {
        return processService.getProcessList();
    }
    
}
