package com.neeraj.microservice.movies.movieservice.graphql.repository;

import com.neeraj.microservice.movies.movieservice.graphql.model.entity.TitlePrincipals;
import com.neeraj.microservice.movies.movieservice.graphql.model.entity.entitycompositesconstrain.TitlePrincipalsUniqueConstrain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TitlePrincipalsRepository extends JpaRepository<TitlePrincipals, TitlePrincipalsUniqueConstrain> {
    List<TitlePrincipals> findByTitlePrincipalsUniqueConstrainTconst(String tconst);
}
