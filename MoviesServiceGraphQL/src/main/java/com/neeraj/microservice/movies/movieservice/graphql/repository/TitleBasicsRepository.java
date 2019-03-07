package com.neeraj.microservice.movies.movieservice.graphql.repository;

import com.neeraj.microservice.movies.movieservice.graphql.model.entity.TitleBasics;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TitleBasicsRepository extends JpaRepository<TitleBasics, String> {
    Page<TitleBasics> findByPrimaryTitleIgnoreCaseContaining(String searchString, Pageable pageable);
}
