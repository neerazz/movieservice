package com.neeraj.microservice.movies.movieservice.graphql.repository;

import com.neeraj.microservice.movies.movieservice.graphql.model.entity.NameBasics;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NameBasicsRepository extends JpaRepository<NameBasics, String> {
    List<NameBasics> findByPrimaryNameIgnoreCaseContaining(String searchString, Pageable pageable);
}
