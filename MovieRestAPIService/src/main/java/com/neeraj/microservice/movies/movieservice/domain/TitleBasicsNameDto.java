package com.neeraj.microservice.movies.movieservice.domain;

import com.neeraj.microservice.movies.movieservice.model.NameBasics;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
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
