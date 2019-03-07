package com.neeraj.microservice.movies.movieservice.graphql.repository;

import com.neeraj.microservice.movies.movieservice.graphql.model.entity.TitleEpisode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TitleEpisodeRepository extends JpaRepository<TitleEpisode, String> {
}
