package com.neeraj.microservice.movies.movieservice.controller;

import com.neeraj.microservice.movies.movieservice.exceptions.NoSuchObjectFoundException;
import com.neeraj.microservice.movies.movieservice.model.TitleCrew;
import com.neeraj.microservice.movies.movieservice.service.TitleCrewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import static com.neeraj.microservice.movies.movieservice.constants.APIConstants.SEARCH_TITLECREW_BY_TITLEID;

@RestController
public class TitleCrewController {

    @Autowired
    private TitleCrewService titleCrewService;

    @GetMapping(SEARCH_TITLECREW_BY_TITLEID)
    public TitleCrew getTitleCrewById(@PathVariable("uniqueId") String uniqueId) {
        return titleCrewService.searchTitleCrewById(uniqueId)
                .orElseThrow(() -> new NoSuchObjectFoundException("No any TitleCrew Found by ID:" + uniqueId));
    }
}
