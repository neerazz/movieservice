package com.neeraj.microservice.movies.movieservice.mapper;

import com.neeraj.microservice.movies.movieservice.model.TitlePrincipals;
import com.neeraj.microservice.movies.movieservice.model.entitycompositesconstrain.TitlePrincipalsUniqueConstrain;
import com.neeraj.microservice.movies.movieservice.repository.TitlePrincipalsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class TitlePrincipalsMapper implements StringToClassMapper {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private TitlePrincipalsRepository titlePrincipalsRepository;

    @Override
    public Object convertToObject(String[] inputArray) {
        log.debug(Arrays.toString(inputArray));

        TitlePrincipalsUniqueConstrain titlePrincipalsUniqueConstrain = new TitlePrincipalsUniqueConstrain()
                .setTconst(inputArray[0])
                .setOrdering(getIntegerValue(inputArray[1]));

        TitlePrincipals titlePrincipals = new TitlePrincipals()
                .setTitlePrincipalsUniqueConstrain(titlePrincipalsUniqueConstrain)
                .setNconst(inputArray[2])
                .setCategory(inputArray[3])
                .setJob(checkEmptyValue(inputArray[4]))
                .setCharacters(checkEmptyValue(inputArray[5]));

        log.debug(titlePrincipals.toString());
        return titlePrincipals;
    }

    @Override
    public TitlePrincipals saveObject(Object object) {
        return titlePrincipalsRepository.save((TitlePrincipals) object);
    }

}
