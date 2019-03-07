package com.neeraj.microservice.movies.movieservice.graphql.service;

import com.neeraj.microservice.movies.movieservice.graphql.exceptions.NoSuchObjectFoundException;
import com.neeraj.microservice.movies.movieservice.graphql.model.domain.TitleBasicsNameDto;
import com.neeraj.microservice.movies.movieservice.graphql.model.domain.TitleBasicsPrinciplesDto;
import com.neeraj.microservice.movies.movieservice.graphql.model.entity.NameBasics;
import com.neeraj.microservice.movies.movieservice.graphql.model.entity.TitleBasics;
import com.neeraj.microservice.movies.movieservice.graphql.model.entity.TitlePrincipals;
import com.neeraj.microservice.movies.movieservice.graphql.repository.TitleBasicsRepository;
import com.neeraj.microservice.movies.movieservice.graphql.repository.TitlePrincipalsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TitleBasicsService {

    @Autowired
    private TitleBasicsRepository titleBasicsRepository;

    @Autowired
    private TitlePrincipalsRepository titlePrincipalsRepository;

    @Autowired
    private NameBasicsService nameBasicsService;

    @Autowired
    private PagingService pagingService;

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

        ModelMapper modelMapper = new ModelMapper();
        TitleBasicsPrinciplesDto titleBasicsPrinciplesDto = modelMapper.map(titleBasics, TitleBasicsPrinciplesDto.class);
        titleBasicsPrinciplesDto.setPrincipals(titlePrincipals);
        return titleBasicsPrinciplesDto;
    }

    private List<TitlePrincipals> getListOfCorrespondingPrinciples(TitleBasics titleBasics) {
        return titlePrincipalsRepository.findByTitlePrincipalsUniqueConstrainTconst(titleBasics.getTconst());
    }

    public TitleBasicsNameDto searchTitleBasicByIdWithName(String uniqueId) {
        TitleBasics titleBasics = searchTitleBasicsByID(uniqueId)
                .orElseThrow(() -> new NoSuchObjectFoundException("No any Title Found by ID:" + uniqueId));

        List<NameBasics> nameBasics = getListOfCorrespondingPrinciples(titleBasics)
                .parallelStream()
                .map(p -> nameBasicsService.searchArtistById(p.getNconst()).get())
                .collect(Collectors.toList());

        ModelMapper modelMapper = new ModelMapper();
        TitleBasicsNameDto titleBasicsPrinciplesDto = modelMapper.map(titleBasics, TitleBasicsNameDto.class);
        titleBasicsPrinciplesDto.setNameBasics(nameBasics);

        return titleBasicsPrinciplesDto;
    }
}
