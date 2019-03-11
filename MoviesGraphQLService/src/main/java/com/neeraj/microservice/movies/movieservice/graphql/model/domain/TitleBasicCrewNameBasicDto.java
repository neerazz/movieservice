package com.neeraj.microservice.movies.movieservice.graphql.model.domain;

import com.neeraj.microservice.movies.movieservice.graphql.model.entity.NameBasics;
import com.neeraj.microservice.movies.movieservice.graphql.model.entity.TitleBasics;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
public class TitleBasicCrewNameBasicDto {

    private TitleBasics titleBasics;
    private List<NameBasics> directors;
    private List<NameBasics> writers;
}
