package com.neeraj.microservices.movies.springbatch.service;

import com.neeraj.microservices.movies.springbatch.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class DBQueryService {

    @Autowired
    private NameBasicsRepository nameBasicsRepository;

    @Autowired
    private TitleAkasRepository titleAkasRepository;

    @Autowired
    private TitleBasicsRepository titleBasicsRepository;

    @Autowired
    private TitleCrewRepository titleCrewRepository;

    @Autowired
    private TitleEpisodeRepository titleEpisodeRepository;

    @Autowired
    private TitlePrincipalsRepository titlePrincipalsRepository;

    @Autowired
    private TitleRatingRepository titleRatingRepository;

    public Map<String, Long> getAllDBStatus(){
        Map<String, Long> output = new ConcurrentHashMap<>();

        output.put("Name Basics", nameBasicsRepository.count());
        output.put("Title Akas", titleAkasRepository.count());
        output.put("Title Basics", titleBasicsRepository.count());
        output.put("Title Crew", titleCrewRepository.count());
        output.put("Title Episode", titleEpisodeRepository.count());
        output.put("Title Principal", titlePrincipalsRepository.count());
        output.put("Title Rating", titleRatingRepository.count());

        return output;
    }
}
