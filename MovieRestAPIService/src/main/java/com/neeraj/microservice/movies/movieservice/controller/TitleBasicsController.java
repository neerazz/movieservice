package com.neeraj.microservice.movies.movieservice.controller;

import com.neeraj.microservice.movies.movieservice.domain.TitleBasicsNameDto;
import com.neeraj.microservice.movies.movieservice.domain.TitleBasicsPrinciplesDto;
import com.neeraj.microservice.movies.movieservice.exceptions.NoSuchObjectFoundException;
import com.neeraj.microservice.movies.movieservice.model.TitleBasics;
import com.neeraj.microservice.movies.movieservice.service.TitleBasicsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.neeraj.microservice.movies.movieservice.constants.APIConstants.*;

@RestController
public class TitleBasicsController {

    @Autowired
    private TitleBasicsService titleBasicsService;

    @GetMapping(SEARCH_TITLEBASICS_BY_NAME)
    @ApiOperation(value = "This accepts a string and return and return list of Titles matching the string. ")
    public Page<TitleBasics> getTitleByName(
            @RequestParam("searchString") String searchString,
            @RequestParam("size") Integer maxResults,
            @RequestParam("page") Integer pageNumber) {
        return titleBasicsService.searchTitleBasicsByName(searchString, maxResults, pageNumber);
    }

    @GetMapping(SEARCH_TTITLEBASICS_BY_TITLEID)
    @ApiOperation(value = "This accepts the unique Artist ID and return the Titles Details.")
    public TitleBasics getTitlebyID(
            @PathVariable("uniqueId") String uniqueId) {
        return titleBasicsService.searchTitleBasicsByID(uniqueId)
                .orElseThrow(() -> new NoSuchObjectFoundException("No any Title Found by ID:" + uniqueId));
    }

    @GetMapping(SEARCH_TTITLEBASICS_BY_TITLEID_WITHPRINCIPLES)
    @ApiOperation(value = "This accepts the unique Title ID and return the Titles Details, along with all the Title Principle details.")
    public TitleBasicsPrinciplesDto getTitlebyIdWithPrinciples(
            @PathVariable("uniqueId") String uniqueId) {
        return titleBasicsService.searchTitleBasicByIdWithPrinciples(uniqueId);
    }

    @GetMapping(SEARCH_TTITLEBASICS_BY_TITLEID_WITHPRINCIPLES_WITHNAME)
    @ApiOperation(value = "This accepts the unique Title ID and return the Titles Details, along with all the Name Basic details.")
    public TitleBasicsNameDto getTitlebyIdWithPrinciplesWithName(
            @PathVariable("uniqueId") String uniqueId) {
        return titleBasicsService.searchTitleBasicByIdWithName(uniqueId);
    }
}
