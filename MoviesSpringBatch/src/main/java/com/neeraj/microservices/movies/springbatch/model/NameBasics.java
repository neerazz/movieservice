package com.neeraj.microservices.movies.springbatch.model;

import javax.persistence.*;
import java.util.List;

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
    private int birthYear;
    private int deathYear;
    private String primaryProfession;
    @OneToMany
    @JoinColumn(name = "tconst")
    private List<TitleBasics> knownForTitles;

    public NameBasics() {
    }

    public NameBasics(String nconst, String primaryName, int birthYear, int deathYear, String primaryProfession, List<TitleBasics> knownForTitles) {
        this.nconst = nconst;
        this.primaryName = primaryName;
        this.birthYear = birthYear;
        this.deathYear = deathYear;
        this.primaryProfession = primaryProfession;
        this.knownForTitles = knownForTitles;
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

    public List<TitleBasics> getKnownForTitles() {
        return knownForTitles;
    }

    public NameBasics setKnownForTitles(List<TitleBasics> knownForTitles) {
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
