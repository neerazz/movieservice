package com.neeraj.microservices.movies.springbatch.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
@ConfigurationProperties(prefix = "file.path")
public class FilePathConfig {

    private String namebasics;
    private String titleakas;
    private String titlebasics;
    private String titlecrew;
    private String titleepisode;
    private String titleprincipals;
    private String titleratings;

    public String getNamebasics() {
        return namebasics;
    }

    public FilePathConfig setNamebasics(String namebasics) {
        this.namebasics = namebasics;
        return this;
    }

    public String getTitleakas() {
        return titleakas;
    }

    public FilePathConfig setTitleakas(String titleakas) {
        this.titleakas = titleakas;
        return this;
    }

    public String getTitlebasics() {
        return titlebasics;
    }

    public FilePathConfig setTitlebasics(String titlebasics) {
        this.titlebasics = titlebasics;
        return this;
    }

    public String getTitlecrew() {
        return titlecrew;
    }

    public FilePathConfig setTitlecrew(String titlecrew) {
        this.titlecrew = titlecrew;
        return this;
    }

    public String getTitleepisode() {
        return titleepisode;
    }

    public FilePathConfig setTitleepisode(String titleepisode) {
        this.titleepisode = titleepisode;
        return this;
    }

    public String getTitleprincipals() {
        return titleprincipals;
    }

    public FilePathConfig setTitleprincipals(String titleprincipals) {
        this.titleprincipals = titleprincipals;
        return this;
    }

    public String getTitleratings() {
        return titleratings;
    }

    public FilePathConfig setTitleratings(String titleratings) {
        this.titleratings = titleratings;
        return this;
    }
}
