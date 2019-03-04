package com.neeraj.microservice.movies.movieservice.controller;

import com.neeraj.microservice.movies.movieservice.domain.NameBasicsTitleBasicsDto;
import com.neeraj.microservice.movies.movieservice.exceptions.NoSuchObjectFoundException;
import com.neeraj.microservice.movies.movieservice.model.NameBasics;
import com.neeraj.microservice.movies.movieservice.service.NameBasicsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.neeraj.microservice.movies.movieservice.constants.APIConstants.*;

@RestController
@Api(value = "/artist-name")
@RequestMapping("/artist-name")
public class NameController {

    @Autowired
    private NameBasicsService nameBasicsService;

    @GetMapping(SEARCH_ARTIST_BY_ARTISTID)
    @ApiOperation(value = "This accepts the unique Artist ID and return the Artist Details.")
    public NameBasics searchArtistById(
            @PathVariable("uniqueId") String uniqueId) {
        return nameBasicsService.searchArtistById(uniqueId)
                .orElseThrow(() -> new NoSuchObjectFoundException("No any Artist Found by ID:" + uniqueId));
    }

    @GetMapping(SEARCH_ARTIST_BY_NAME)
    @ApiOperation(value = "This accepts a string and return and return list of Artists.' ")
    public Page<NameBasics> searchArtistByName(
            @RequestParam("searchString") String searchString,
            @RequestParam("size") Integer maxResults,
            @RequestParam("page") Integer pageNumber) {
        return nameBasicsService.searchArtistByName(searchString, maxResults, pageNumber);
    }

    @GetMapping(SEARCH_ARTIST_BY_ARTISTID_WITHTITLES)
    @ApiOperation(value = "This accepts the unique Artist ID and return the Artist Details,along with all the popular Title details.")
    public NameBasicsTitleBasicsDto searchByArtistIdWithTitles(@PathVariable("uniqueId") String uniqueId) {
        return nameBasicsService.searchByArtistIdWithTitles(uniqueId);
    }

    @GetMapping(SEARCH_ARTIST_BY_NAME_WITHTITLES)
    @ApiOperation(value = "This accepts a string and return and return list of Artists Details, along with all the popular Title details.")
    public List<NameBasicsTitleBasicsDto> searchArtistByNameWithTitles(
            @RequestParam("searchString") String searchString,
            @RequestParam("size") Integer maxResults,
            @RequestParam("page") Integer pageNumber) {
        return nameBasicsService.searchArtistByNameWithTitles(searchString, maxResults, pageNumber);
    }
}
