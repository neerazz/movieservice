package com.neeraj.microservices.movies.springbatch.mapper;

import com.neeraj.microservices.movies.springbatch.model.TitleEpisode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class TitleEpisodeMapper extends BasicConversion implements FieldSetMapper {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public TitleEpisode mapFieldSet(FieldSet fieldSet) {

        TitleEpisode newObject = new TitleEpisode();

        try {
            newObject.setTconst(fieldSet.readString(0))
                    .setParentTconst(fieldSet.readString(1))
                    .setSeasonNumber(getIntegerValue(fieldSet.readString(2)))
                    .setEpisodeNumber(getIntegerValue(fieldSet.readString(3)));
        } catch (Exception e) {
            log.error("Error while mapping the fileset:{} to Object:{}.", Arrays.toString(fieldSet.getValues()), newObject);
        }
        return newObject;
    }
}
