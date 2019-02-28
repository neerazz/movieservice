package com.neeraj.microservices.movies.springbatch.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/*
    This class contains the principal cast/crew for titles
        tconst (string) - alphanumeric unique identifier of the title
        ordering (integer) – a number to uniquely identify rows for a given titleId
        nconst (string) - alphanumeric unique identifier of the name/person
        category (string) - the category of job that person was in
        job (string) - the specific job title if applicable, else '\N'
        characters (string) - the name of the character played if applicable, else '\N'
 */

@Entity
public class TitlePrincipals {

    @Id
    private String tconst;
    private int ordering;
    private String nconst;
    private String category;
    private String job;
    private String characters;

    public TitlePrincipals() {
    }

    public TitlePrincipals(String tconst, int ordering, String nconst, String category, String job, String characters) {
        this.tconst = tconst;
        this.ordering = ordering;
        this.nconst = nconst;
        this.category = category;
        this.job = job;
        this.characters = characters;
    }

    public String getTconst() {
        return tconst;
    }

    public TitlePrincipals setTconst(String tconst) {
        this.tconst = tconst;
        return this;
    }

    public int getOrdering() {
        return ordering;
    }

    public TitlePrincipals setOrdering(int ordering) {
        this.ordering = ordering;
        return this;
    }

    public String getNconst() {
        return nconst;
    }

    public TitlePrincipals setNconst(String nconst) {
        this.nconst = nconst;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public TitlePrincipals setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getJob() {
        return job;
    }

    public TitlePrincipals setJob(String job) {
        this.job = job;
        return this;
    }

    public String getCharacters() {
        return characters;
    }

    public TitlePrincipals setCharacters(String characters) {
        this.characters = characters;
        return this;
    }

    @Override
    public String toString() {
        return "TitlePrincipals{" +
                "tconst='" + tconst + '\'' +
                ", ordering=" + ordering +
                ", nconst='" + nconst + '\'' +
                ", category='" + category + '\'' +
                ", job='" + job + '\'' +
                ", characters='" + characters + '\'' +
                '}';
    }
}
