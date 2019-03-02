package com.neeraj.microservice.movies.movieservice.model;

import com.neeraj.microservice.movies.movieservice.model.entitycompositesconstrain.TitlePrincipalsUniqueConstrain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

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

    @EmbeddedId
    private TitlePrincipalsUniqueConstrain titlePrincipalsUniqueConstrain;
    private String nconst;
    private String category;
    private String job;
    private String characters;

    public TitlePrincipals() {
    }

    public TitlePrincipals(TitlePrincipalsUniqueConstrain titlePrincipalsUniqueConstrain, String nconst, String category, String job, String characters) {
        this.titlePrincipalsUniqueConstrain = titlePrincipalsUniqueConstrain;
        this.nconst = nconst;
        this.category = category;
        this.job = job;
        this.characters = characters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TitlePrincipals)) return false;

        TitlePrincipals that = (TitlePrincipals) o;

        if (!titlePrincipalsUniqueConstrain.equals(that.titlePrincipalsUniqueConstrain)) return false;
        return getNconst().equals(that.getNconst());
    }

    @Override
    public int hashCode() {
        int result = titlePrincipalsUniqueConstrain.hashCode();
        result = 31 * result + getNconst().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "TitlePrincipals{" +
                "titlePrincipalsUniqueConstrain=" + titlePrincipalsUniqueConstrain +
                ", nconst='" + nconst + '\'' +
                ", category='" + category + '\'' +
                ", job='" + job + '\'' +
                ", characters='" + characters + '\'' +
                '}';
    }

    public TitlePrincipalsUniqueConstrain getTitlePrincipalsUniqueConstrain() {
        return titlePrincipalsUniqueConstrain;
    }

    public TitlePrincipals setTitlePrincipalsUniqueConstrain(TitlePrincipalsUniqueConstrain titlePrincipalsUniqueConstrain) {
        this.titlePrincipalsUniqueConstrain = titlePrincipalsUniqueConstrain;
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
}
