package com.neeraj.microservice.movies.movieservice.repository;

import com.neeraj.microservice.movies.movieservice.model.TitleBasics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TitleBasicsRepository extends JpaRepository<TitleBasics, String> {
}
