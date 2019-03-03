package com.neeraj.microservice.movies.movieservice.controller;

import com.neeraj.microservice.movies.movieservice.domain.NameBasicsResponse;
import com.neeraj.microservice.movies.movieservice.model.NameBasics;
import com.neeraj.microservice.movies.movieservice.service.NameBasicsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.neeraj.microservice.movies.movieservice.constants.APIConstants.*;

@RestController
public class NameController {

    @Autowired
    private NameBasicsService nameBasicsService;

    @GetMapping(SEARCH_ARTIST_BY_NAME)
    @ApiOperation(value = "This accepts a string and return and return list of Artists.' ")
    public List<NameBasics> searchByName(
            @PathVariable("searchString") String searchString,
            @RequestParam("maxResults") Integer maxResults,
            @RequestParam("pageNumber") Integer pageNumber) {
        return nameBasicsService.searchByName(searchString, maxResults, pageNumber);
    }

    @GetMapping(SEARCH_ARTIST_BY_ARTISTID)
    @ApiOperation(value = "This accepts the unique Artist ID and return the Artist Details.")
    public NameBasics searchByArtistId(
            @PathVariable("uniqueId") String uniqueId) {
        return nameBasicsService.getNameBasicByID(uniqueId);
    }

    @GetMapping(SEARCH_ARTIST_BY_ARTISTID_WITHTITLES)
    @ApiOperation(value = "This accepts the unique Artist ID and return the Artist Details, along the Title details.")
    public NameBasicsResponse searchByArtistIdWithTitles(@PathVariable("uniqueId") String uniqueId) {
        return nameBasicsService.getNameBasicByIDAndTitles(uniqueId);
    }
}
