package com.neeraj.microservice.movies.movieservice.repository;

import com.neeraj.microservice.movies.movieservice.model.TitleEpisode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TitleEpisodeRepository extends JpaRepository<TitleEpisode, String> {
}
