package com.neeraj.microservices.movies.springbatch.repository;

import com.neeraj.microservices.movies.springbatch.model.TitleCrew;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TitleCrewRepository extends JpaRepository<TitleCrew, String> {
}
