package com.neeraj.microservice.movies.movieservice.graphql.service;

import com.neeraj.microservice.movies.movieservice.graphql.model.entity.TitleCrew;
import com.neeraj.microservice.movies.movieservice.graphql.repository.TitleCrewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TitleCrewService {

    @Autowired
    private TitleCrewRepository titleCrewRepository;

    public Optional<TitleCrew> searchTitleCrewById(String uniqueId) {
        return titleCrewRepository.findById(uniqueId);
    }
}
