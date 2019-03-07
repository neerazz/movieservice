package com.neeraj.microservice.movies.movieservice.graphql.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/*

        This class contains the following information for names:
            nconst (string) - alphanumeric unique identifier of the name/person
            primaryName (string)– name by which the person is most often credited
            birthYear – in YYYY format
            deathYear – in YYYY format if applicable, else '\N'
            primaryProfession (array of strings)– the top-3 professions of the person
            knownForTitles (array of tconsts) – titles the person is known for

 */
@Entity
@Table(name = "NAMEBASIC")
public class NameBasics {

    @Id
    private String nconst;
    private String primaryName;
    @Column(length = 4)
    private int birthYear;
    @Column(length = 4)
    private int deathYear;
    private String primaryProfession;
    private String knownForTitles;

    public NameBasics() {
    }

    public NameBasics(String nconst, String primaryName, int birthYear, int deathYear, String primaryProfession, String knownForTitles) {
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
        if (!(o instanceof NameBasics)) return false;

        NameBasics that = (NameBasics) o;

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

    public NameBasics setNconst(String nconst) {
        this.nconst = nconst;
        return this;
    }

    public String getPrimaryName() {
        return primaryName;
    }

    public NameBasics setPrimaryName(String primaryName) {
        this.primaryName = primaryName;
        return this;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public NameBasics setBirthYear(int birthYear) {
        this.birthYear = birthYear;
        return this;
    }

    public int getDeathYear() {
        return deathYear;
    }

    public NameBasics setDeathYear(int deathYear) {
        this.deathYear = deathYear;
        return this;
    }

    public String getPrimaryProfession() {
        return primaryProfession;
    }

    public NameBasics setPrimaryProfession(String primaryProfession) {
        this.primaryProfession = primaryProfession;
        return this;
    }

    public String getKnownForTitles() {
        return knownForTitles;
    }

    public NameBasics setKnownForTitles(String knownForTitles) {
        this.knownForTitles = knownForTitles;
        return this;
    }

    @Override
    public String toString() {
        return "NameBasics{" +
                "nconst='" + nconst + '\'' +
                ", primaryName='" + primaryName + '\'' +
                ", birthYear='" + birthYear + '\'' +
                ", deathYear='" + deathYear + '\'' +
                ", primaryProfession='" + primaryProfession + '\'' +
                ", knownForTitles=" + knownForTitles +
                '}';
    }
}
