package com.neeraj.microservice.movies.movieservice.graphql.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.neeraj.microservice.movies.movieservice.graphql.model.domain.NameBasicsTitleBasicsDto;
import com.neeraj.microservice.movies.movieservice.graphql.model.domain.TitleBasicsNamePrincipleDto;
import com.neeraj.microservice.movies.movieservice.graphql.model.entity.NameBasics;
import com.neeraj.microservice.movies.movieservice.graphql.model.entity.TitleBasics;
import com.neeraj.microservice.movies.movieservice.graphql.service.NameBasicsService;
import com.neeraj.microservice.movies.movieservice.graphql.service.TitleBasicsService;
import com.neeraj.microservice.movies.movieservice.graphql.service.TitleCrewService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

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

    public Optional<NameBasics> getArtist(String artistId) {
        return nameBasicsService.searchArtistById(artistId);
    }

    public NameBasicsTitleBasicsDto getArtistandtitles(String artistId) {
        return nameBasicsService.searchByArtistIdWithTitles(artistId);
    }

    public List<NameBasics> getArtists(String name, Integer maxResults, Integer pageNumber) {
        return nameBasicsService.searchArtistByName(name, maxResults, pageNumber);
    }

    public List<NameBasicsTitleBasicsDto> getArtistsandtitles(String name, Integer maxResults, Integer pageNumber) {
        return nameBasicsService.searchArtistByNameWithTitles(name, maxResults, pageNumber);
    }

    public TitleBasics getTitle(String titleID) {
        return titleBasicsService.findById(titleID).get();
    }

    public List<TitleBasics> getTitles(String name, Integer maxResults, Integer pageNumber) {
        return titleBasicsService.searchTitleBasicsByName(name, maxResults, pageNumber).getContent();
    }

    public TitleBasicsNamePrincipleDto getTitleandartistsandprinciples(String titleID) {
        return titleBasicsService.searchTitleBasicByIdWithNameWithPrinciples(titleID);
    }

    public List<TitleBasicsNamePrincipleDto> getTitlesandartistsandprinciples(String name, Integer maxResults, Integer pageNumber) {
        return titleBasicsService.searchTitleBasicsByIdWithNameWithPrinciples(name, maxResults, pageNumber);
    }
}
