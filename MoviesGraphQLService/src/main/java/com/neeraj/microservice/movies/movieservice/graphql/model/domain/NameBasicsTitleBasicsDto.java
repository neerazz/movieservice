package com.neeraj.microservice.movies.movieservice.graphql.model.domain;

import com.neeraj.microservice.movies.movieservice.graphql.model.entity.TitleBasics;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class NameBasicsTitleBasicsDto {
    private String nconst;
    private String primaryName;
    private int birthYear;
    private int deathYear;
    private String primaryProfession;
    private Set<TitleBasics> knownForTitles;
}
