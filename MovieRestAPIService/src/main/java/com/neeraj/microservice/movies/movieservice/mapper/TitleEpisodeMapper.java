package com.neeraj.microservice.movies.movieservice.mapper;

import com.neeraj.microservice.movies.movieservice.model.TitleEpisode;
import com.neeraj.microservice.movies.movieservice.repository.TitleEpisodeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class TitleEpisodeMapper implements StringToClassMapper {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TitleEpisodeRepository titleEpisodeRepository;

    @Override
    public Object convertToObject(String[] inputArray) {

        log.info(Arrays.toString(inputArray));

        TitleEpisode titleEpisode = new TitleEpisode()
                .setTconst(inputArray[0])
                .setParentTconst(inputArray[1])
                .setSeasonNumber(getIntegerValue(inputArray[2]))
                .setEpisodeNumber(getIntegerValue(inputArray[3]));

        log.debug(titleEpisode.toString());
        return null;
    }

    @Override
    public TitleEpisode saveObject(Object object) {
        return titleEpisodeRepository.save((TitleEpisode) object);
    }

}
