package com.neeraj.microservice.movies.movieservice.graphql.repository;

import com.neeraj.microservice.movies.movieservice.graphql.model.entity.TitleAkas;
import com.neeraj.microservice.movies.movieservice.graphql.model.entity.entitycompositesconstrain.TitleAkasUniqueConstrain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TitleAkasRepository extends JpaRepository<TitleAkas, TitleAkasUniqueConstrain> {
}
