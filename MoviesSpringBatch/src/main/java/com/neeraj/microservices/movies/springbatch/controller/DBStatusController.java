package com.neeraj.microservices.movies.springbatch.controller;

import com.neeraj.microservices.movies.springbatch.service.DBQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/status")
public class DBStatusController {

    @Autowired
    private DBQueryService dbQueryService;

    @GetMapping("/counts")
    public Map<String, Long> getAllDBCount(){
        return dbQueryService.getAllDBStatus();
    }
}
