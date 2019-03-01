package com.neeraj.microservices.movies.springbatch.mapper;

import com.neeraj.microservices.movies.springbatch.model.TitleCrew;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class TitleCrewMapper extends BasicConversion implements FieldSetMapper {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public TitleCrew mapFieldSet(FieldSet fieldSet) {

        TitleCrew newObject = new TitleCrew();
        try {

//            Set<NameBasics> directors = splitValue(fieldSet.readString(1))
//                    .parallelStream()
//                    .map(s -> new NameBasics().setNconst(s))
//                    .collect(Collectors.toSet());
//
//            Set<NameBasics> writers = splitValue(fieldSet.readString(2))
//                    .parallelStream()
//                    .map(s -> new NameBasics().setNconst(s))
//                    .collect(Collectors.toSet());

            newObject.setTconst(fieldSet.readString(0))
                    .setDirectors(checkEmptyValue(fieldSet.readString(1)))
                    .setWriters(checkEmptyValue(fieldSet.readString(2)));
        } catch (Exception e) {
            log.error("Error while mapping the fileset:{} to Object:{}.", Arrays.toString(fieldSet.getValues()), newObject);
        }
        log.trace("Input \n{} is transformed to \n{}.",Arrays.toString(fieldSet.getValues()),newObject.toString());
        return newObject;
    }
}
