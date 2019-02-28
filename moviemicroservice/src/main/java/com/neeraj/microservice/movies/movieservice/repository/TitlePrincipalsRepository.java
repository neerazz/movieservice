package com.neeraj.microservice.movies.movieservice.repository;

import com.neeraj.microservice.movies.movieservice.model.TitlePrincipals;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TitlePrincipalsRepository extends JpaRepository<TitlePrincipals, String> {
}
