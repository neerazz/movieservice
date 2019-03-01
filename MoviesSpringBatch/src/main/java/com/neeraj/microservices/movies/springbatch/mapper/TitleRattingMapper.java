package com.neeraj.microservices.movies.springbatch.mapper;

import com.neeraj.microservices.movies.springbatch.model.TitleRating;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class TitleRattingMapper extends BasicConversion implements FieldSetMapper {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public TitleRating mapFieldSet(FieldSet fieldSet) {

        TitleRating newObject = new TitleRating();

        try {
            newObject.setTconst(fieldSet.readString(0).trim())
                    .setAverageRating(getDoubleValue(fieldSet.readString(1)))
                    .setNumVotes(getIntegerValue(fieldSet.readString(2)));
        } catch (Exception e) {
            log.error("Error while mapping the fileset:{} to Object:{}.", Arrays.toString(fieldSet.getValues()), newObject);
        }
        log.trace("Input \n{} is transformed to \n{}.",Arrays.toString(fieldSet.getValues()),newObject.toString());
        return newObject;
    }
}
