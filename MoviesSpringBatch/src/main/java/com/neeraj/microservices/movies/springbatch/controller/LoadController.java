package com.neeraj.microservices.movies.springbatch.controller;

import com.neeraj.microservices.movies.springbatch.service.JobCreatingService;
import io.swagger.annotations.ApiOperation;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/load")
public class LoadController {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private JobCreatingService jobCreatingService;

    @GetMapping("/allfiles")
    @ApiOperation(value = "NA", notes = "This end point Loads all the files into the table.")
    public BatchStatus load() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {

        Map<String, JobParameter> maps = new HashMap<>();
        maps.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters parameters = new JobParameters(maps);
        JobExecution jobExecution = jobLauncher.run(jobCreatingService.createJobWithAllSteps(), parameters);
        System.out.println("JobExecution: " + jobExecution.getStatus());
        return jobExecution.getStatus();
    }

    @GetMapping("/{number}")
    @ApiOperation(value = "Load A batch job, based on File ID.",
            notes = "This end point Loads all the files into the table.")
    public BatchStatus load(@PathVariable("number") int jobNumber) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {

        Map<String, JobParameter> maps = new HashMap<>();
        maps.put("time", new JobParameter(System.currentTimeMillis()));
        JobParameters parameters = new JobParameters(maps);
        JobExecution jobExecution = jobLauncher.run(jobCreatingService.createJobWithOneStep(jobNumber), parameters);
        System.out.println("JobExecution: " + jobExecution.getStatus());

        return jobExecution.getStatus();
    }
}
