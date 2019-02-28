package com.neeraj.microservices.movies.springbatch.batch;

import com.neeraj.microservices.movies.springbatch.model.TitleRating;
import com.neeraj.microservices.movies.springbatch.repository.TitleRatingRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DBWriter implements ItemWriter<TitleRating> {

    @Autowired
    private TitleRatingRepository titleRatingRepository;

    @Override
    public void write(List<? extends TitleRating> titleRatings) {
        System.out.println("Storing " + titleRatings.size() + " objects to DB.");
        titleRatingRepository.saveAll(titleRatings);
    }
}
