package com.neeraj.microservices.movies.springbatch.repository;

import com.neeraj.microservices.movies.springbatch.model.TitlePrincipals;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TitlePrincipalsRepository extends JpaRepository<TitlePrincipals, String> {
}
