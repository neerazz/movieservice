package com.neeraj.microservices.movies.springbatch.mapper;

import com.neeraj.microservices.movies.springbatch.model.NameBasics;
import com.neeraj.microservices.movies.springbatch.model.TitleBasics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class NameBasicsMapper extends BasicConversion implements FieldSetMapper {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public NameBasics mapFieldSet(FieldSet fieldSet) {

        NameBasics newObject = new NameBasics();

        try {
            List<TitleBasics> knownTitles = splitValue(fieldSet.readString(5))
                    .parallelStream()
                    .map(s -> new TitleBasics().setTconst(s))
                    .collect(Collectors.toList());

            newObject
                    .setNconst(fieldSet.readString(0))
                    .setPrimaryName(fieldSet.readString(1))
                    .setBirthYear(getIntegerValue(fieldSet.readString(2)))
                    .setDeathYear(getIntegerValue(fieldSet.readString(3)))
                    .setPrimaryProfession(fieldSet.readString(4))
                    .setKnownForTitles(knownTitles);
        } catch (Exception e) {
            log.error("Error while mapping the fileset:{} to Object:{}.", Arrays.toString(fieldSet.getValues()), newObject);
        }
        return newObject;
    }
}
