package com.neeraj.microservices.movies.springbatch.repository;

import com.neeraj.microservices.movies.springbatch.model.TitleRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TitleRatingRepository extends JpaRepository<TitleRating, String> {
}
