package com.neeraj.microservice.movies.movieservice.mapper;

import com.neeraj.microservice.movies.movieservice.model.TitleAkas;
import com.neeraj.microservice.movies.movieservice.repository.TitleAkasRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class TitleAkasMapper implements StringToClassMapper {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TitleAkasRepository titleAkasRepository;

    @Override
    public Object convertToObject(String[] input) {
        log.trace(Arrays.toString(input));

        TitleAkas titleAkas = new TitleAkas()
                .setTitleId(input[0])
                .setOrdering(getIntegerValue(input[1]))
                .setTitle(input[2])
                .setRegion(input[3])
                .setLanguage(input[4])
                .setTypes(input[5])
                .setAttributes(input[6])
                .setOriginalTitle(getBooleanValue(input[7]));

        log.debug(titleAkas.toString());
        return titleAkas;
    }

    @Override
    public TitleAkas saveObject(Object object) {
        return titleAkasRepository.save((TitleAkas) object);
    }
}
