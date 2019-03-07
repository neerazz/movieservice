package com.neeraj.microservice.movies.movieservice.graphql.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/*
    This class contains the director and writer information for all the titles in IMDb. Fields include:
        tconst (string) - alphanumeric unique identifier of the title
        directors (array of nconsts) - director(s) of the given title
        writers (array of nconsts) – writer(s) of the given title

 */
@Entity
public class TitleCrew {

    @Id
    private String tconst;

    private String directors;

    private String writers;

    public TitleCrew() {
    }


    public TitleCrew(String tconst, String directors, String writers) {
        this.tconst = tconst;
        this.directors = directors;
        this.writers = writers;
    }

    public String getTconst() {
        return tconst;
    }

    public TitleCrew setTconst(String tconst) {
        this.tconst = tconst;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TitleCrew)) return false;

        TitleCrew titleCrew = (TitleCrew) o;

        return getTconst().equals(titleCrew.getTconst());
    }

    @Override
    public int hashCode() {
        return getTconst().hashCode();
    }

    public String getDirectors() {
        return directors;
    }

    public TitleCrew setDirectors(String directors) {
        this.directors = directors;
        return this;
    }

    public String getWriters() {
        return writers;
    }

    public TitleCrew setWriters(String writers) {
        this.writers = writers;
        return this;
    }

    @Override
    public String toString() {
        return "TitleCrew{" +
                "tconst='" + tconst + '\'' +
                ", directors=" + directors +
                ", writers=" + writers +
                '}';
    }
}