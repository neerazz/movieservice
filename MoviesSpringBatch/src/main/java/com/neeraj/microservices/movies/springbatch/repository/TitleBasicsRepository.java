package com.neeraj.microservices.movies.springbatch.repository;

import com.neeraj.microservices.movies.springbatch.model.TitleBasics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TitleBasicsRepository extends JpaRepository<TitleBasics, String> {
}
