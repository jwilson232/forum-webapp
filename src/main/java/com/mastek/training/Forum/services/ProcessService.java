package com.mastek.training.Forum.services;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProcessService {

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    TaskService taskService;

    @Autowired
    RepositoryService repositoryService;

    public String startProcess(String serviceName) {

        runtimeService.startProcessInstanceByKey(serviceName);
        return processInformation();
    }

    public String processInformation() {

        List<Task> taskList = taskService.createTaskQuery().orderByTaskCreateTime().asc().list();

        StringBuilder processAndTaskInfo = new StringBuilder();

        processAndTaskInfo.append("Number of process definition available: "
                + repositoryService.createProcessDefinitionQuery().count() + " | Task Details= ");

        taskList.forEach(task -> {

            processAndTaskInfo.append("ID: " + task.getId() + ", Name: " + task.getName() + ", Assignee: "
                    + task.getAssignee() + ", Description: " + task.getDescription());
        });

        return processAndTaskInfo.toString();
    }
}
