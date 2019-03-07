package com.neeraj.microservice.movies.movieservice.graphql.model.entity;

/*

    This class contains the following information for titles:
        tconst (string) - alphanumeric unique identifier of the title
        titleType (string) – the type/format of the title (e.g. movie, short, tvseries, tvepisode, video, etc)
        primaryTitle (string) – the more popular title / the title used by the filmmakers on promotional materials at the point of release
        originalTitle (string) - original title, in the original language
        isAdult (boolean) - 0: non-adult title; 1: adult title
        startYear (YYYY) – represents the release year of a title. In the case of TV Series, it is the series start year
        endYear (YYYY) – TV Series end year. ‘\N’ for all other title types
        runtimeMinutes – primary runtime of the title, in minutes
        genres (string array) – includes up to three genres associated with the title

 */

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TitleBasics {

    @Id
    private String tconst;
    private String titleType;
    private String primaryTitle;
    private String originalTitle;
    private boolean isAdult;
    @Column(length = 4)
    private int startYear;
    @Column(length = 4)
    private int endYear;
    private int runtimeMinutes;
    private String genres;

    public TitleBasics() {
    }

    public TitleBasics(String tconst, String titleType, String primaryTitle, String originalTitle, boolean isAdult, int startYear, int endYear, int runtimeMinutes, String genres) {
        this.tconst = tconst;
        this.titleType = titleType;
        this.primaryTitle = primaryTitle;
        this.originalTitle = originalTitle;
        this.isAdult = isAdult;
        this.startYear = startYear;
        this.endYear = endYear;
        this.runtimeMinutes = runtimeMinutes;
        this.genres = genres;
    }

    @Override
    public String toString() {
        return "TitleBasics{" +
                "tconst='" + tconst + '\'' +
                ", titleType='" + titleType + '\'' +
                ", primaryTitle='" + primaryTitle + '\'' +
                ", originalTitle='" + originalTitle + '\'' +
                ", isAdult=" + isAdult +
                ", startYear=" + startYear +
                ", endYear=" + endYear +
                ", runtimeMinutes=" + runtimeMinutes +
                ", genres=" + genres +
                '}';
    }

    public String getTconst() {
        return tconst;
    }

    public TitleBasics setTconst(String tconst) {
        this.tconst = tconst;
        return this;
    }

    public String getTitleType() {
        return titleType;
    }

    public TitleBasics setTitleType(String titleType) {
        this.titleType = titleType;
        return this;
    }

    public String getPrimaryTitle() {
        return primaryTitle;
    }

    public TitleBasics setPrimaryTitle(String primaryTitle) {
        this.primaryTitle = primaryTitle;
        return this;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public TitleBasics setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
        return this;
    }

    public boolean isAdult() {
        return isAdult;
    }

    public TitleBasics setAdult(boolean adult) {
        isAdult = adult;
        return this;
    }

    public int getStartYear() {
        return startYear;
    }

    public TitleBasics setStartYear(int startYear) {
        this.startYear = startYear;
        return this;
    }

    public int getEndYear() {
        return endYear;
    }

    public TitleBasics setEndYear(int endYear) {
        this.endYear = endYear;
        return this;
    }

    public int getRuntimeMinutes() {
        return runtimeMinutes;
    }

    public TitleBasics setRuntimeMinutes(int runtimeMinutes) {
        this.runtimeMinutes = runtimeMinutes;
        return this;
    }

    public String getGenres() {
        return genres;
    }

    public TitleBasics setGenres(String genres) {
        this.genres = genres;
        return this;
    }
}
