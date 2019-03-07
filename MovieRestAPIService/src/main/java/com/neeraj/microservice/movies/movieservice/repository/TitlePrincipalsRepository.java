package com.neeraj.microservice.movies.movieservice.repository;

import com.neeraj.microservice.movies.movieservice.model.TitlePrincipals;
import com.neeraj.microservice.movies.movieservice.model.entitycompositesconstrain.TitlePrincipalsUniqueConstrain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TitlePrincipalsRepository extends JpaRepository<TitlePrincipals, TitlePrincipalsUniqueConstrain> {
    List<TitlePrincipals> findByTitlePrincipalsUniqueConstrainTconst(String tconst);
}
