package com.neeraj.microservice.movies.movieservice.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MapperFactory {

    @Autowired
    private NameBasicsMapper nameBasicsMapper;
    @Autowired
    private TitleAkasMapper titleAkasMapper;
    @Autowired
    private TitleBasicsMapper titleBasicsMapper;
    @Autowired
    private TitleCrewMapper titleCrewMapper;
    @Autowired
    private TitleEpisodeMapper titleEpisodeMapper;
    @Autowired
    private TitlePrincipalsMapper titlePrincipalsMapper;
    @Autowired
    private TitleRatingMapper titleRatingMapper;

    public StringToClassMapper getMapper(String fileName) {

        if (fileName.toUpperCase().contains("TITLE") && fileName.toUpperCase().contains("RATING")) {
            return titleRatingMapper;
        }
        if (fileName.toUpperCase().contains("TITLE") && fileName.toUpperCase().contains("PRINCIPALS")) {
            return titlePrincipalsMapper;
        }
        if (fileName.toUpperCase().contains("TITLE") && fileName.toUpperCase().contains("EPISODE")) {
            return titleEpisodeMapper;
        }
        if (fileName.toUpperCase().contains("TITLE") && fileName.toUpperCase().contains("CREW")) {
            return titleCrewMapper;
        }
        if (fileName.toUpperCase().contains("TITLE") && fileName.toUpperCase().contains("BASICS")) {
            return titleBasicsMapper;
        }
        if (fileName.toUpperCase().contains("TITLE") && fileName.toUpperCase().contains("AKAS")) {
            return titleAkasMapper;
        }
        if (fileName.toUpperCase().contains("NAME") && fileName.toUpperCase().contains("BASICS")) {
            return nameBasicsMapper;
        }
        return null;
    }
}
