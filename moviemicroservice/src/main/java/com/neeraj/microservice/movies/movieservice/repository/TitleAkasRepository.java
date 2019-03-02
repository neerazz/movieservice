package com.neeraj.microservice.movies.movieservice.repository;

import com.neeraj.microservice.movies.movieservice.model.TitleAkas;
import com.neeraj.microservice.movies.movieservice.model.entitycompositesconstrain.TitleAkasUniqueConstrain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TitleAkasRepository extends JpaRepository<TitleAkas, TitleAkasUniqueConstrain> {
}
