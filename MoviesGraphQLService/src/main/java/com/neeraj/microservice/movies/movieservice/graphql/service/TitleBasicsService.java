package com.neeraj.microservice.movies.movieservice.graphql.service;

import com.neeraj.microservice.movies.movieservice.graphql.exceptions.NoSuchObjectFoundException;
import com.neeraj.microservice.movies.movieservice.graphql.model.domain.*;
import com.neeraj.microservice.movies.movieservice.graphql.model.entity.NameBasics;
import com.neeraj.microservice.movies.movieservice.graphql.model.entity.TitleBasics;
import com.neeraj.microservice.movies.movieservice.graphql.model.entity.TitleCrew;
import com.neeraj.microservice.movies.movieservice.graphql.model.entity.TitlePrincipals;
import com.neeraj.microservice.movies.movieservice.graphql.repository.TitleBasicsRepository;
import com.neeraj.microservice.movies.movieservice.graphql.repository.TitlePrincipalsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TitleBasicsService {


    private TitleBasicsRepository titleBasicsRepository;
    private TitlePrincipalsRepository titlePrincipalsRepository;
    private NameBasicsService nameBasicsService;
    private PagingService pagingService;
    private ModelMapper modelMapper;

    @Autowired
    private TitleCrewService titleCrewService;

    @Autowired
    public TitleBasicsService(TitleBasicsRepository titleBasicsRepository, TitlePrincipalsRepository titlePrincipalsRepository, NameBasicsService nameBasicsService, PagingService pagingService) {
        this.titleBasicsRepository = titleBasicsRepository;
        this.titlePrincipalsRepository = titlePrincipalsRepository;
        this.nameBasicsService = nameBasicsService;
        this.pagingService = pagingService;
        modelMapper = new ModelMapper();
    }

    public Optional<TitleBasics> findById(String tconst) {
        return titleBasicsRepository.findById(tconst);
//                .orElseThrow(() -> new NoSuchObjectFoundException("There is no any Title with ID:" + tconst));
    }

    public Page<TitleBasics> searchTitleBasicsByName(String searchString, Integer maxResults, Integer pageNumber) {
        Pageable pageable = pagingService.getPageable(maxResults, pageNumber);
        return titleBasicsRepository.findByPrimaryTitleIgnoreCaseContaining(searchString, pageable);
    }

    public Optional<TitleBasics> searchTitleBasicsByID(String uniqueId) {
        return titleBasicsRepository.findById(uniqueId);
    }

    public TitleBasicsPrinciplesDto searchTitleBasicByIdWithPrinciples(String uniqueId) {
        TitleBasics titleBasics = searchTitleBasicsByID(uniqueId)
                .orElseThrow(() -> new NoSuchObjectFoundException("No any Title Found by ID:" + uniqueId));
        List<TitlePrincipals> titlePrincipals = getListOfCorrespondingPrinciples(titleBasics);

        TitleBasicsPrinciplesDto titleBasicsPrinciplesDto = modelMapper.map(titleBasics, TitleBasicsPrinciplesDto.class);

        return titleBasicsPrinciplesDto
                .toBuilder()
                .principals(titlePrincipals)
                .build();
    }

    private List<TitlePrincipals> getListOfCorrespondingPrinciples(TitleBasics titleBasics) {
        return titlePrincipalsRepository.findByTitlePrincipalsUniqueConstrainTconst(titleBasics.getTconst());
    }

    private List<NameBasics> getListOfCorrespondingNameBasics(List<TitlePrincipals> principals) {
        return principals
                .parallelStream()
                .map(p -> nameBasicsService.searchArtistById(p.getNconst()))
                .filter(optional -> optional.isPresent())
                .map(nameBasics -> nameBasics.get())
                .collect(Collectors.toList());
    }

    private List<NameBasics> getListOfCorrespondingNameBasics(String[] nameBasicIds) {
        return Arrays.asList(nameBasicIds)
                .parallelStream()
                .map(id -> nameBasicsService.searchArtistById(id))
                .filter(optional -> optional.isPresent())
                .map(nameBasics -> nameBasics.get())
                .collect(Collectors.toList());
    }

    public TitleBasicsNameDto searchTitleBasicByIdWithName(String uniqueId) {
        TitleBasics titleBasics = searchTitleBasicsByID(uniqueId)
                .orElseThrow(() -> new NoSuchObjectFoundException("No any Title Found by ID:" + uniqueId));

        List<TitlePrincipals> principles = getListOfCorrespondingPrinciples(titleBasics);
        List<NameBasics> nameBasics = getListOfCorrespondingNameBasics(principles);

        TitleBasicsNameDto titleBasicsPrinciplesDto = modelMapper.map(titleBasics, TitleBasicsNameDto.class);

        return titleBasicsPrinciplesDto.toBuilder()
                .nameBasics(nameBasics)
                .build();
    }

    public TitleBasicsNamePrincipleDto searchTitleBasicByIdWithNameWithPrinciples(String uniqueId) {

        TitleBasics titleBasics = searchTitleBasicsByID(uniqueId)
                .orElseThrow(() -> new NoSuchObjectFoundException("No any Title Found by ID:" + uniqueId));
        List<TitlePrincipals> principles = getListOfCorrespondingPrinciples(titleBasics);
        List<NameBasics> nameBasics = getListOfCorrespondingNameBasics(principles);

        TitleBasicsNamePrincipleDto titleBasicsNamePrincipleDto = modelMapper.map(titleBasics, TitleBasicsNamePrincipleDto.class);

        return titleBasicsNamePrincipleDto
                .toBuilder()
                .principals(principles)
                .artists(nameBasics)
                .build();
    }

    public List<TitleBasicsNamePrincipleDto> searchTitleBasicsByIdWithNameWithPrinciples(String searchString, Integer maxResults, Integer pageNumber) {

        return searchTitleBasicsByName(searchString, maxResults, pageNumber)
                .get()
                .parallel()
                .map(titleBasics -> searchTitleBasicByIdWithNameWithPrinciples(titleBasics.getTconst()))
                .collect(Collectors.toList());
    }

    public List<TitleBasicCrewDto> searchTitleBasicsByNameWithCrew(String name, Integer maxResults, Integer pageNumber) {

        return searchTitleBasicsByName(name, maxResults, pageNumber)
                .get()
                .parallel()
                .map(titleBasics ->
                        new TitleBasicCrewDto().toBuilder()
                                .titleBasics(titleBasics)
                                .titleCrew(titleCrewService.searchTitleCrewById(titleBasics.getTconst()).get())
                                .build()
                )
                .collect(Collectors.toList());
    }

    public List<TitleBasicCrewNameBasicDto> searchTitleBasicsByNameWithCrewWithName(String name, Integer maxResults, Integer pageNumber) {

        List<TitleBasics> titleBasics = searchTitleBasicsByName(name, maxResults, pageNumber).getContent();

        return titleBasics.parallelStream()
                .map(titleBasic -> {
                    TitleCrew titleCrew = titleCrewService.searchTitleCrewById(titleBasic.getTconst()).get();
                    String[] directorsString = titleCrew.getDirectors().split(",");
                    List<NameBasics> directorsList = getListOfCorrespondingNameBasics(directorsString);
                    String[] writers = titleCrew.getWriters().split(",");
                    List<NameBasics> writersList = getListOfCorrespondingNameBasics(writers);
                    return new TitleBasicCrewNameBasicDto()
                            .toBuilder()
                            .titleBasics(titleBasic)
                            .directors(directorsList)
                            .writers(writersList)
                            .build();
                })
                .collect(Collectors.toList());
    }

}
