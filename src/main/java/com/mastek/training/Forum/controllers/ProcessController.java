package com.mastek.training.Forum.controllers;

import com.mastek.training.Forum.services.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProcessController {

    @Autowired
    ProcessService processService;

    @GetMapping("/start-service/{serviceName}")
    public String startProcess(@PathVariable String serviceName) {
        return processService.startProcess(serviceName);
    }
}
