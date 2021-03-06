package com.neeraj.microservice.movies.movieservice.domain;

import com.neeraj.microservice.movies.movieservice.model.TitlePrincipals;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TitleBasicsPrinciplesDto {

    private String tconst;
    private String titleType;
    private String primaryTitle;
    private String originalTitle;
    private boolean isAdult;
    private int startYear;
    private int endYear;
    private int runtimeMinutes;
    private String genres;
    private List<TitlePrincipals> principals;

}
