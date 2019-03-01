package com.neeraj.microservice.movies.movieservice.controller;

import com.neeraj.microservice.movies.movieservice.model.FilePathConfig;
import com.neeraj.microservice.movies.movieservice.service.FileProcessingService;
import com.neeraj.microservice.movies.movieservice.service.FileZipUnZipService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileProcessingController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FileZipUnZipService fileZipUnZipService;
    @Autowired
    private FileProcessingService fileProcessingService;
    @Autowired
    private FilePathConfig pathConfig;

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello back from Server";
    }

    @GetMapping("/unzip/{fileName}")
    public String unzipFile(@PathVariable("fileName") String fileName) {
        log.info("Unzipping the file:" + fileName);
        return fileZipUnZipService.unZipFile(pathConfig.getTitleratings());
    }

    @GetMapping("/unzip/allFiles")
    public String unzipAllFile() {
        return fileZipUnZipService.unZipAllFile();
    }

    @GetMapping("/load/{fileName}")
    public String loadZipFile(@PathVariable("fileName") String fileName) {

        fileProcessingService.processZipFile(pathConfig.getTitleratings());
//        new Thread(() -> fileProcessingService.processZipFile(pathConfig.getTitleratings()));
        return "Loading the file : " + fileName + " to DB.";
    }

    @GetMapping("/load/allFiles")
    public String loadAllZipFile() {
//        new Thread(() -> fileProcessingService.processAllFiles());
        log.info("Processing all file:");
        fileProcessingService.processAllFiles();
        return "Batch Script submitted to load all the file to DB.";
    }
}