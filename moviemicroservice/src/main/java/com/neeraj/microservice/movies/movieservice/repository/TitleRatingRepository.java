package com.neeraj.microservice.movies.movieservice.repository;

import com.neeraj.microservice.movies.movieservice.model.TitleRating;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TitleRatingRepository extends PagingAndSortingRepository<TitleRating, String> {
}
