package com.neeraj.microservice.movies.movieservice.graphql.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TitleRating {

    @Id
//    @CsvBindByName(column = "tconst", required = true)
    private String tconst;
    //    @CsvBindByName(column = "averageRating", required = true)
    private Double averageRating;
    //    @CsvBindByName(column = "numVotes", required = true)
    private long numVotes;

    public TitleRating(String tconst, Double averageRating, long numVotes) {
        this.tconst = tconst;
        this.averageRating = averageRating;
        this.numVotes = numVotes;
    }

    public TitleRating() {
    }

    public String getTconst() {
        return tconst;
    }

    public TitleRating setTconst(String tconst) {
        this.tconst = tconst;
        return this;
    }

    public Double getAverageRating() {
        return averageRating;
    }

    public TitleRating setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
        return this;
    }

    public long getNumVotes() {
        return numVotes;
    }

    public TitleRating setNumVotes(long numVotes) {
        this.numVotes = numVotes;
        return this;
    }

    @Override
    public String toString() {
        return "TitleRating{" +
                "tconst='" + tconst + '\'' +
                ", averageRating=" + averageRating +
                ", numVotes=" + numVotes +
                '}';
    }
}
