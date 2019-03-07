package com.neeraj.microservice.movies.movieservice.graphql.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/*

    This class contains the tv episode information. Fields include:
        tconst (string) - alphanumeric identifier of episode
        parentTconst (string) - alphanumeric identifier of the parent TV Series
        seasonNumber (integer) – season number the episode belongs to
        episodeNumber (integer) – episode number of the tconst in the TV series

 */

@Entity
//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Builder
public class TitleEpisode {

    @Id
    private String tconst;
    private String parentTconst;
    private int seasonNumber;
    private int episodeNumber;

    public TitleEpisode() {
    }

    public TitleEpisode(String tconst, String parentTconst, int seasonNumber, int episodeNumber) {
        this.tconst = tconst;
        this.parentTconst = parentTconst;
        this.seasonNumber = seasonNumber;
        this.episodeNumber = episodeNumber;
    }

    public String getTconst() {
        return tconst;
    }

    public TitleEpisode setTconst(String tconst) {
        this.tconst = tconst;
        return this;
    }

    public String getParentTconst() {
        return parentTconst;
    }

    public TitleEpisode setParentTconst(String parentTconst) {
        this.parentTconst = parentTconst;
        return this;
    }

    public int getSeasonNumber() {
        return seasonNumber;
    }

    public TitleEpisode setSeasonNumber(int seasonNumber) {
        this.seasonNumber = seasonNumber;
        return this;
    }

    public int getEpisodeNumber() {
        return episodeNumber;
    }

    public TitleEpisode setEpisodeNumber(int episodeNumber) {
        this.episodeNumber = episodeNumber;
        return this;
    }

    @Override
    public String toString() {
        return "TitleEpisode{" +
                "tconst='" + tconst + '\'' +
                ", parentTconst='" + parentTconst + '\'' +
                ", seasonNumber=" + seasonNumber +
                ", episodeNumber=" + episodeNumber +
                '}';
    }
}
