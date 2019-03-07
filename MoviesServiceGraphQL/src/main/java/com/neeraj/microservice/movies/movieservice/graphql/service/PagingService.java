package com.neeraj.microservice.movies.movieservice.graphql.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PagingService {

    public static String buildLinkHeader(final String uri, final String rel) {
        return "<" + uri + ">; rel=\"" + rel + "\"";
    }

    public Pageable getPageable(Integer maxResults, Integer pageNumber) {
        int maxResultCount = 50;
        int pageNumberCount = 1;
        if (maxResults > 0 && maxResults < 100 && maxResults != null) {
            maxResultCount = maxResults;
        }
        if (pageNumber > 0 && pageNumber < 1000 && pageNumber != null) {
            pageNumberCount = pageNumber;
        }
        return PageRequest.of(pageNumberCount, maxResultCount);
    }

//    private String createLinkHeader(PagedResources pr) {
//        final StringBuilder linkHeader = new StringBuilder();
//        linkHeader.append(buildLinkHeader(pr.getLinks("first").get(0).getHref(), "first"));
//        linkHeader.append(", ");
//        linkHeader.append(buildLinkHeader(pr.getLinks("next").get(0).getHref(), "next"));
//        return linkHeader.toString();
//    }
}
