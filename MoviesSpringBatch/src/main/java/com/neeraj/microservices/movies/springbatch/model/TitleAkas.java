package com.neeraj.microservices.movies.springbatch.model;

import com.neeraj.microservices.movies.springbatch.model.entitycompositesconstrain.TitleAkasUniqueConstrain;

import javax.persistence.*;

/*
        This class contains the following information for titles:
            titleId (string) - a tconst, an alphanumeric unique identifier of the title
            ordering (integer) – a number to uniquely identify rows for a given titleId
            title (string) – the localized title
            region (string) - the region for this version of the title
            language (string) - the language of the title
            types (array) - Enumerated set of attributes for this alternative title. One or more of the following: "alternative", "dvd", "festival", "tv", "video", "working", "original", "imdbDisplay". New values may be added in the future without warning
            attributes (array) - Additional terms to describe this alternative title, not enumerated
            isOriginalTitle (boolean) – 0: not original title; 1: original title

 */

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "titleId", "ordering"}))
public class TitleAkas {

    @EmbeddedId
    private TitleAkasUniqueConstrain akasUniqueConstrain;
    private String title;
    private String region;
    private String language;
    private String types;
    private String attributes;
    private boolean isOriginalTitle;

    public TitleAkas() {
    }

    public TitleAkas(TitleAkasUniqueConstrain akasUniqueConstrain, String title, String region, String language, String types, String attributes, boolean isOriginalTitle) {
        this.akasUniqueConstrain = akasUniqueConstrain;
        this.title = title;
        this.region = region;
        this.language = language;
        this.types = types;
        this.attributes = attributes;
        this.isOriginalTitle = isOriginalTitle;
    }

    @Override
    public String toString() {
        return "TitleAkas{" +
                "akasUniqueConstrain=" + akasUniqueConstrain +
                ", title='" + title + '\'' +
                ", region='" + region + '\'' +
                ", language='" + language + '\'' +
                ", types='" + types + '\'' +
                ", attributes='" + attributes + '\'' +
                ", isOriginalTitle=" + isOriginalTitle +
                '}';
    }

    public TitleAkasUniqueConstrain getAkasUniqueConstrain() {
        return akasUniqueConstrain;
    }

    public TitleAkas setAkasUniqueConstrain(TitleAkasUniqueConstrain akasUniqueConstrain) {
        this.akasUniqueConstrain = akasUniqueConstrain;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public TitleAkas setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getRegion() {
        return region;
    }

    public TitleAkas setRegion(String region) {
        this.region = region;
        return this;
    }

    public String getLanguage() {
        return language;
    }

    public TitleAkas setLanguage(String language) {
        this.language = language;
        return this;
    }

    public String getTypes() {
        return types;
    }

    public TitleAkas setTypes(String types) {
        this.types = types;
        return this;
    }

    public String getAttributes() {
        return attributes;
    }

    public TitleAkas setAttributes(String attributes) {
        this.attributes = attributes;
        return this;
    }

    public boolean isOriginalTitle() {
        return isOriginalTitle;
    }

    public TitleAkas setOriginalTitle(boolean originalTitle) {
        isOriginalTitle = originalTitle;
        return this;
    }
}