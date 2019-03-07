package com.neeraj.microservice.movies.movieservice.graphql.model.domain;

import com.neeraj.microservice.movies.movieservice.graphql.model.entity.NameBasics;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class TitleBasicsNameDto {

    private String tconst;
    private String titleType;
    private String primaryTitle;
    private String originalTitle;
    private boolean isAdult;
    private int startYear;
    private int endYear;
    private int runtimeMinutes;
    private String genres;
    private List<NameBasics> nameBasics;
}
