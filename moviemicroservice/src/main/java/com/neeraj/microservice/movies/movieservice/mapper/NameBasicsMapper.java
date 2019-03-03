package com.neeraj.microservice.movies.movieservice.mapper;

import com.neeraj.microservice.movies.movieservice.model.NameBasics;
import com.neeraj.microservice.movies.movieservice.repository.NameBasicsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class NameBasicsMapper implements StringToClassMapper {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private NameBasicsRepository nameBasicsRepository;

    @Override
    public Object convertToObject(String[] inputArray) {

        log.debug(Arrays.toString(inputArray));

        NameBasics nameBasics = new NameBasics()
                .setNconst(inputArray[0])
                .setPrimaryName(inputArray[1])
                .setBirthYear(getIntegerValue(inputArray[2]))
                .setDeathYear(getIntegerValue(inputArray[3]))
                .setPrimaryProfession(inputArray[4])
                .setKnownForTitles(checkEmptyValue(inputArray[5]));

        log.debug(nameBasics.toString());
        return nameBasics;
    }

    @Override
    public NameBasics saveObject(Object object) {
        return nameBasicsRepository.save((NameBasics) object);
    }

}
