package com.neeraj.microservice.movies.movieservice.constants;

public class APIConstants {

    public static final String SEARCH_ARTIST_BY_NAME = "/name";
    public static final String SEARCH_ARTIST_BY_ARTISTID = "/name/{uniqueId}";

    public static final String SEARCH_ARTIST_BY_NAME_WITHTITLES = "/name/withTitle";
    public static final String SEARCH_ARTIST_BY_ARTISTID_WITHTITLES = "/name/{uniqueId}/withTitle";

    public static final String SEARCH_TITLEBASICS_BY_NAME = "/name";
    public static final String SEARCH_TTITLEBASICS_BY_TITLEID = "/name/{uniqueId}";
    public static final String SEARCH_TTITLEBASICS_BY_TITLEID_WITHPRINCIPLES = "/name/{uniqueId}/withPrincipals";
    public static final String SEARCH_TTITLEBASICS_BY_TITLEID_WITHPRINCIPLES_WITHNAME = "/name/{uniqueId}/withName";

    public static final String SEARCH_TITLECREW_BY_TITLEID = "/{uniqueId}";

}
