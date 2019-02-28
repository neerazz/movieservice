package com.neeraj.microservice.movies.movieservice.mapper;

import com.neeraj.microservice.movies.movieservice.model.TitleBasics;
import com.neeraj.microservice.movies.movieservice.repository.TitleBasicsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class TitleBasicsMapper implements StringToClassMapper {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TitleBasicsRepository titleBasicsRepository;

    @Override
    public Object convertToObject(String[] input) {

        log.debug(Arrays.toString(input));

        TitleBasics titleBasics = new TitleBasics()
                .setTconst(input[0])
                .setTitleType(input[1])
                .setPrimaryTitle(input[2])
                .setOriginalTitle(input[3])
                .setAdult(getBooleanValue(input[4]))
                .setStartYear(getIntegerValue(input[5]))
                .setEndYear(getIntegerValue(input[6]))
                .setRuntimeMinutes(getIntegerValue(input[7].trim()))
                .setGenres(input[8].trim());

        log.debug(titleBasics.toString());
        return titleBasics;
    }

    @Override
    public TitleBasics saveObject(Object object) {
        return titleBasicsRepository.save((TitleBasics) object);
    }

}
