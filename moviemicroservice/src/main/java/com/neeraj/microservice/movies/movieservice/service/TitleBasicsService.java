package com.neeraj.microservice.movies.movieservice.service;

import com.neeraj.microservice.movies.movieservice.exceptions.NoSuchObjectFoundException;
import com.neeraj.microservice.movies.movieservice.model.TitleBasics;
import com.neeraj.microservice.movies.movieservice.repository.TitleBasicsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TitleBasicsService {

    @Autowired
    private TitleBasicsRepository titleBasicsRepository;

    public TitleBasics findById(String tconst) {
        return titleBasicsRepository.findById(tconst).orElseThrow(() -> new NoSuchObjectFoundException("There is no any Title with ID:" + tconst));
    }
}
