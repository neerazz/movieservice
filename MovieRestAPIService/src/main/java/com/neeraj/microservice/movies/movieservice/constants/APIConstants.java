package com.neeraj.microservice.movies.movieservice.constants;

public class APIConstants {

    public static final String SEARCH_ARTIST_BY_NAME = "/artist-name/name";
    public static final String SEARCH_ARTIST_BY_ARTISTID = "/artist-name/name/{uniqueId}";
    public static final String SEARCH_ARTIST_BY_NAME_WITHTITLES = "/artist-name/name/withTitle";
    public static final String SEARCH_ARTIST_BY_ARTISTID_WITHTITLES = "/artist-name/name/{uniqueId}/withTitle";

    public static final String SEARCH_TITLEBASICS_BY_NAME = "/title-name/name";
    public static final String SEARCH_TTITLEBASICS_BY_TITLEID = "/title-name/name/{uniqueId}";
    public static final String SEARCH_TTITLEBASICS_BY_TITLEID_WITHPRINCIPLES = "/title-name/name/{uniqueId}/withPrincipals";
    public static final String SEARCH_TTITLEBASICS_BY_TITLEID_WITHPRINCIPLES_WITHNAME = "/title-name/name/{uniqueId}/withName";

    public static final String SEARCH_TITLECREW_BY_TITLEID = "/title-crew/{uniqueId}";

}