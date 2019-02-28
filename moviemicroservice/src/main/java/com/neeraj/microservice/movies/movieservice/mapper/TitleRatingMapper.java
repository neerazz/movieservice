package com.neeraj.microservice.movies.movieservice.mapper;

import com.neeraj.microservice.movies.movieservice.model.TitleRating;
import com.neeraj.microservice.movies.movieservice.repository.TitleRatingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class TitleRatingMapper implements StringToClassMapper {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TitleRatingRepository titleRatingRepository;

    @Override
    public TitleRating convertToObject(String[] inputArray) {
        log.debug(Arrays.toString(inputArray));
        TitleRating titleRating = new TitleRating()
                .setTconst(inputArray[0].trim())
                .setAverageRating(getDobleValue(inputArray[1]))
                .setNumVotes(getIntegerValue(inputArray[2]));
        log.debug(titleRating.toString());
        return titleRating;
    }

    @Override
    public TitleRating saveObject(Object object) {
        return titleRatingRepository.save((TitleRating) object);
    }
}
