package com.neeraj.microservice.movies.movieservice.graphql.model.domain;

import com.neeraj.microservice.movies.movieservice.graphql.model.entity.TitleBasics;
import com.neeraj.microservice.movies.movieservice.graphql.model.entity.TitleCrew;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class TitleBasicCrewDto {

    private TitleBasics titleBasics;
    private TitleCrew titleCrew;
}
