package com.neeraj.microservices.movies.springbatch.mapper;

import com.neeraj.microservices.movies.springbatch.model.TitleBasics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class TitleBasicsMapper extends BasicConversion implements FieldSetMapper {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public TitleBasics mapFieldSet(FieldSet fieldSet) {

        TitleBasics newObject = new TitleBasics();
        try {
            newObject.setTconst(fieldSet.readString(0))
                    .setTitleType(fieldSet.readString(1))
                    .setPrimaryTitle(fieldSet.readString(2))
                    .setOriginalTitle(fieldSet.readString(3))
                    .setAdult(getBooleanValue(fieldSet.readString(4)))
                    .setStartYear(getIntegerValue(fieldSet.readString(5)))
                    .setEndYear(getIntegerValue(fieldSet.readString(6)))
                    .setRuntimeMinutes(getIntegerValue(fieldSet.readString(7)))
                    .setGenres(fieldSet.readString(8));
        } catch (Exception e) {
            log.error("Error while mapping the fileset:{} to Object:{}.", Arrays.toString(fieldSet.getValues()), newObject);
        }
        log.trace("Input \n{} is transformed to \n{}.",Arrays.toString(fieldSet.getValues()),newObject.toString());
        return newObject;
    }
}
