package com.neeraj.microservice.movies.movieservice.mapper;

import com.neeraj.microservice.movies.movieservice.model.NameBasics;
import com.neeraj.microservice.movies.movieservice.model.TitleCrew;
import com.neeraj.microservice.movies.movieservice.repository.TitleCrewRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class TitleCrewMapper implements StringToClassMapper {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TitleCrewRepository titleCrewRepository;

    @Override
    public Object convertToObject(String[] input) {

        Set<NameBasics> directors = splitValue(input[1])
                .parallelStream()
                .map(s -> new NameBasics().setNconst(s))
                .collect(Collectors.toSet());

        Set<NameBasics> writers = splitValue(input[2])
                .parallelStream()
                .map(s -> new NameBasics().setNconst(s))
                .collect(Collectors.toSet());

        log.debug(Arrays.toString(input));
        TitleCrew titleCrew = new TitleCrew()
                .setTconst(input[0])
                .setDirectors(directors)
                .setWriters(writers);

        log.debug(titleCrew.toString());
        return titleCrew;
    }

    @Override
    public TitleCrew saveObject(Object object) {
        return titleCrewRepository.save((TitleCrew) object);
    }

}
