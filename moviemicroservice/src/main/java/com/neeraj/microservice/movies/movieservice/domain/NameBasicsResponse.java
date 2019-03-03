package com.neeraj.microservice.movies.movieservice.domain;

import com.neeraj.microservice.movies.movieservice.model.TitleBasics;

import java.util.Set;

public class NameBasicsResponse {
    private String nconst;
    private String primaryName;
    private int birthYear;
    private int deathYear;
    private String primaryProfession;
    private Set<TitleBasics> knownForTitles;

    public NameBasicsResponse() {
    }

    public NameBasicsResponse(String nconst, String primaryName, int birthYear, int deathYear, String primaryProfession, Set<TitleBasics> knownForTitles) {
        this.nconst = nconst;
        this.primaryName = primaryName;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
        this.primaryProfession = primaryProfession;
        this.knownForTitles = knownForTitles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NameBasicsResponse)) return false;

        NameBasicsResponse that = (NameBasicsResponse) o;

        if (getBirthYear() != that.getBirthYear()) return false;
        if (getDeathYear() != that.getDeathYear()) return false;
        if (!getNconst().equals(that.getNconst())) return false;
        return getPrimaryName().equals(that.getPrimaryName());
    }

    @Override
    public int hashCode() {
        int result = getNconst().hashCode();
        result = 31 * result + getPrimaryName().hashCode();
        result = 31 * result + getBirthYear();
        result = 31 * result + getDeathYear();
        return result;
    }

    public String getNconst() {
        return nconst;
    }

    public NameBasicsResponse setNconst(String nconst) {
        this.nconst = nconst;
        return this;
    }

    public String getPrimaryName() {
        return primaryName;
    }

    public NameBasicsResponse setPrimaryName(String primaryName) {
        this.primaryName = primaryName;
        return this;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public NameBasicsResponse setBirthYear(int birthYear) {
        this.birthYear = birthYear;
        return this;
    }

    public int getDeathYear() {
        return deathYear;
    }

    public NameBasicsResponse setDeathYear(int deathYear) {
        this.deathYear = deathYear;
        return this;
    }

    public String getPrimaryProfession() {
        return primaryProfession;
    }

    public NameBasicsResponse setPrimaryProfession(String primaryProfession) {
        this.primaryProfession = primaryProfession;
        return this;
    }

    public Set<TitleBasics> getKnownForTitles() {
        return knownForTitles;
    }

    public NameBasicsResponse setKnownForTitles(Set<TitleBasics> knownForTitles) {
        this.knownForTitles = knownForTitles;
        return this;
    }
}
