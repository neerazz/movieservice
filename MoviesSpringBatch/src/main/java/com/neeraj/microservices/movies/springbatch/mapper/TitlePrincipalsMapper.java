package com.neeraj.microservices.movies.springbatch.mapper;

import com.neeraj.microservices.movies.springbatch.model.TitlePrincipals;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class TitlePrincipalsMapper extends BasicConversion implements FieldSetMapper {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public TitlePrincipals mapFieldSet(FieldSet fieldSet) {

        TitlePrincipals newObject = new TitlePrincipals();

        try {
            newObject.setTconst(fieldSet.readString(1))
                    .setOrdering(getIntegerValue(fieldSet.readString(1)))
                    .setNconst(fieldSet.readString(2))
                    .setCategory(fieldSet.readString(3))
                    .setJob(checkEmptyValue(fieldSet.readString(4)))
                    .setCharacters(checkEmptyValue(fieldSet.readString(5)));
        } catch (Exception e) {
            log.error("Error while mapping the fileset:{} to Object:{}.", Arrays.toString(fieldSet.getValues()), newObject);
        }
        return newObject;
    }
}
