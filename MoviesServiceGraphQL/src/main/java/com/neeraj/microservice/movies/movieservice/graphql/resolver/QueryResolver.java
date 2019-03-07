package com.neeraj.microservice.movies.movieservice.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.neeraj.microservice.movies.movieservice.graphql.model.entity.NameBasics;
import com.neeraj.microservice.movies.movieservice.graphql.service.NameBasicsService;
import com.neeraj.microservice.movies.movieservice.graphql.service.TitleBasicsService;
import com.neeraj.microservice.movies.movieservice.graphql.service.TitleCrewService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QueryResolver implements GraphQLQueryResolver {

    private NameBasicsService nameBasicsService;
    private TitleBasicsService titleBasicsService;
    private TitleCrewService titleCrewService;

    public QueryResolver(NameBasicsService nameBasicsService, TitleBasicsService titleBasicsService, TitleCrewService titleCrewService) {
        this.nameBasicsService = nameBasicsService;
        this.titleBasicsService = titleBasicsService;
        this.titleCrewService = titleCrewService;
    }

    public List<NameBasics> getArtists(String name, Integer maxResults, Integer pageNumber) {
        return nameBasicsService.searchArtistByName(name, maxResults, pageNumber);
    }
}
