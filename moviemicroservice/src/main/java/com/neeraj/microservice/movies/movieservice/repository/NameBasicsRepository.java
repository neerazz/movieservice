package com.neeraj.microservice.movies.movieservice.repository;

import com.neeraj.microservice.movies.movieservice.model.NameBasics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NameBasicsRepository extends JpaRepository<NameBasics, String> {
}
