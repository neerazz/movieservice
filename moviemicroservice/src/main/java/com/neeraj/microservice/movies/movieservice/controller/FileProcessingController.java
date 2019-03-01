package com.neeraj.microservice.movies.movieservice.controller;

import com.neeraj.microservice.movies.movieservice.model.FilePathConfig;
import com.neeraj.microservice.movies.movieservice.service.FileProcessingService;
import com.neeraj.microservice.movies.movieservice.service.FileZipUnZipService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api()
public class FileProcessingController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private FileZipUnZipService fileZipUnZipService;
    @Autowired
    private FileProcessingService fileProcessingService;
    @Autowired
    private FilePathConfig pathConfig;

    @GetMapping("/hello")
    @ApiOperation(value = "This is a hard coded value which says 'Hello back from Server' ",
            notes = "This end point just says a hard coded Hello.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request. Please check the request data."),
            @ApiResponse(code = 500, message = "Internal Server Error. See error details in the response, for further assistance drop an email.")})
    public String sayHello() {
        return "Hello back from Server";
    }

    @GetMapping("/unzip/{fileName}")
    @ApiOperation(value = "Unzip files.",
            notes = "This API is to UnZip the file send with the request.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request. Please check the request data."),
            @ApiResponse(code = 500, message = "Internal Server Error. See error details in the response, for further assistance drop an email.")})
    public String unzipFile(@PathVariable("fileName") String fileName) {
        log.info("Unzipping the file:" + fileName);
        return fileZipUnZipService.unZipFile(pathConfig.getTitleratings());
    }

    @GetMapping("/unzip/allFiles")
    @ApiOperation(value = "Unzip files.",
            notes = "This API is to UnZip all file send with the request.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request. Please check the request data."),
            @ApiResponse(code = 500, message = "Internal Server Error. See error details in the response, for further assistance drop an email.")})
    public String unzipAllFile() {
        return fileZipUnZipService.unZipAllFile();
    }

    @GetMapping("/load/{fileName}")
    @ApiOperation(value = "Unzip files.",
            notes = "This API is to Load the file with file name sent with the request.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request. Please check the request data."),
            @ApiResponse(code = 500, message = "Internal Server Error. See error details in the response, for further assistance drop an email.")})
    public String loadZipFile(@PathVariable("fileName") String fileName) {

        fileProcessingService.processZipFile(pathConfig.getTitleratings());
//        new Thread(() -> fileProcessingService.processZipFile(pathConfig.getTitleratings()));
        return "Loading the file : " + fileName + " to DB.";
    }

    @GetMapping("/load/allFiles")
    @ApiOperation(value = "Unzip files.",
            notes = "This API is to Load all file send with the request.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad Request. Please check the request data."),
            @ApiResponse(code = 500, message = "Internal Server Error. See error details in the response, for further assistance drop an email.")})
    public String loadAllZipFile() {
//        new Thread(() -> fileProcessingService.processAllFiles());
        log.info("Processing all file:");
        fileProcessingService.processAllFiles();
        return "Batch Script submitted to load all the file to DB.";
    }
}