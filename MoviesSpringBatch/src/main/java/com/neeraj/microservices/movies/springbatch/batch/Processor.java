package com.neeraj.microservices.movies.springbatch.batch;

import com.neeraj.microservices.movies.springbatch.model.TitleRating;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class Processor implements ItemProcessor<TitleRating, TitleRating> {

    @Override
    public TitleRating process(TitleRating TitleRating) {
        return TitleRating;
    }
}