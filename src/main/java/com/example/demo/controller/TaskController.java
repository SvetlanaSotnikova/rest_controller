package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.services.DataProcessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private DataProcessingService dataProcessingService;

    @GetMapping
    public List<String> getAllTask() {
        List<String> tasks = new ArrayList<>();
        tasks.add("sort");
        tasks.add("filter");
        tasks.add("calc");
        return tasks;
    }

    @GetMapping("/sort")
    public List<User> getSortUsers() {
        return dataProcessingService
                .sortUserByAge(dataProcessingService
                        .getRepository().getUsers());
    }

    // filter
    @GetMapping("/filter")
    public List<User> getFilterUsers() {
        return dataProcessingService
                .filterUserByAge(dataProcessingService
                        .getRepository().getUsers(), 20);
    }

    //calc
    @GetMapping("/calc")
    public double getCalcUsers() {
        return dataProcessingService
                .calculateAverageAge(dataProcessingService
                        .getRepository().getUsers());
    }
}
