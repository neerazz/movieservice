package com.neeraj.microservice.movies.movieservice.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.Set;

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

    @OneToMany
    @JoinColumn(name = "nconsts")
    private Set<NameBasics> directors;

    @OneToMany
    @JoinColumn(name = "nconsts")
    private Set<NameBasics> writers;

    public TitleCrew() {
    }

    public TitleCrew(String tconst, Set<NameBasics> directors, Set<NameBasics> writers) {
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

    public Set<NameBasics> getDirectors() {
        return directors;
    }

    public TitleCrew setDirectors(Set<NameBasics> directors) {
        this.directors = directors;
        return this;
    }

    public Set<NameBasics> getWriters() {
        return writers;
    }

    public TitleCrew setWriters(Set<NameBasics> writers) {
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
