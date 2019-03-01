package com.neeraj.microservices.movies.springbatch.repository;

import com.neeraj.microservices.movies.springbatch.model.TitleAkas;
import com.neeraj.microservices.movies.springbatch.model.entitycompositesconstrain.TitleAkasUniqueConstrain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TitleAkasRepository extends JpaRepository<TitleAkas, TitleAkasUniqueConstrain> {
}
