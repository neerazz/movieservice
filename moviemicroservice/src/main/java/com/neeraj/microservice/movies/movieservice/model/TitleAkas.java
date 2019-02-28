package com.neeraj.microservice.movies.movieservice.model;

import javax.persistence.Entity;
import javax.persistence.Id;

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
public class TitleAkas {

    @Id
    private String titleId;
    private int ordering;
    private String title;
    private String region;
    private String language;
    private String types;
    private String attributes;
    private boolean isOriginalTitle;

    public TitleAkas() {
    }

    public TitleAkas(String titleId, int ordering, String title, String region, String language, String types, String attributes, boolean isOriginalTitle) {
        this.titleId = titleId;
        this.ordering = ordering;
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
                "titleId='" + titleId + '\'' +
                ", ordering=" + ordering +
                ", title='" + title + '\'' +
                ", region='" + region + '\'' +
                ", language='" + language + '\'' +
                ", types='" + types + '\'' +
                ", attributes='" + attributes + '\'' +
                ", isOriginalTitle=" + isOriginalTitle +
                '}';
    }

    public String getTitleId() {
        return titleId;
    }

    public TitleAkas setTitleId(String titleId) {
        this.titleId = titleId;
        return this;
    }

    public int getOrdering() {
        return ordering;
    }

    public TitleAkas setOrdering(int ordering) {
        this.ordering = ordering;
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
