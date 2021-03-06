package com.neeraj.microservices.movies.springbatch.mapper;

import com.neeraj.microservices.movies.springbatch.model.TitleAkas;
import com.neeraj.microservices.movies.springbatch.model.entitycompositesconstrain.TitleAkasUniqueConstrain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class TitleAkasMapper extends BasicConversion implements FieldSetMapper {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public TitleAkas mapFieldSet(FieldSet fieldSet) {

        TitleAkas newObject = new TitleAkas();

        TitleAkasUniqueConstrain titleAkasUniqueConstrain = new TitleAkasUniqueConstrain();

        try {
            titleAkasUniqueConstrain.setTitleId(fieldSet.readString(0))
                    .setOrdering(fieldSet.readString(1));

            newObject.setAkasUniqueConstrain(titleAkasUniqueConstrain)
                    .setTitle(fieldSet.readString(2))
                    .setRegion(fieldSet.readString(3))
                    .setLanguage(fieldSet.readString(4))
                    .setTypes(fieldSet.readString(5))
                    .setAttributes(fieldSet.readString(6))
                    .setOriginalTitle(getBooleanValue(fieldSet.readString(7)));
        } catch (Exception e) {
            log.error("Error while mapping the fileset:{} to Object:{}.", Arrays.toString(fieldSet.getValues()), newObject);
        }
        log.trace("Input \n{} is transformed to \n{}.",Arrays.toString(fieldSet.getValues()),newObject.toString());
        return newObject;
    }
}