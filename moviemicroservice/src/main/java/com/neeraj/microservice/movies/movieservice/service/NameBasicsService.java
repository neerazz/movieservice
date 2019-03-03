package com.neeraj.microservice.movies.movieservice.service;

import com.neeraj.microservice.movies.movieservice.domain.NameBasicsResponse;
import com.neeraj.microservice.movies.movieservice.exceptions.NoSuchObjectFoundException;
import com.neeraj.microservice.movies.movieservice.model.NameBasics;
import com.neeraj.microservice.movies.movieservice.model.TitleBasics;
import com.neeraj.microservice.movies.movieservice.repository.NameBasicsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class NameBasicsService {

    @Autowired
    private NameBasicsRepository nameBasicsRepository;

    @Autowired
    private TitleBasicsService titleBasicsService;

    public List<NameBasics> searchByName(String searchString, Integer maxResults, Integer pageNumber) {
        int maxResultCount = 50;
        int pageNumberCount = 1;
        if (maxResults > 0 && maxResults < 100 && maxResults != null) {
            maxResultCount = maxResults;
        }
        if (pageNumber > 0 && pageNumber < 1000 && pageNumber != null) {
            pageNumberCount = pageNumber;
        }
        return nameBasicsRepository.findByPrimaryNameIgnoreCaseContaining(searchString, PageRequest.of(pageNumberCount, maxResultCount));
    }

    public NameBasics getNameBasicByID(String anyString) {
        return nameBasicsRepository.findById(anyString)
                .orElseThrow(() -> new NoSuchObjectFoundException("No any Artist Found by ID:" + anyString));
    }

    public NameBasicsResponse getNameBasicByIDAndTitles(String uniqueId) {
        NameBasics nameBasics = getNameBasicByID(uniqueId);
        Set<TitleBasics> titleBasics = Arrays.asList(nameBasics.getKnownForTitles().split(","))
                .parallelStream()
                .filter(tconst -> tconst.trim().length() == 9)
                .map(tconst -> titleBasicsService.findById(tconst))
                .collect(Collectors.toSet());

        return new NameBasicsResponse()
                .setNconst(nameBasics.getNconst())
                .setPrimaryName(nameBasics.getPrimaryName())
                .setBirthYear(nameBasics.getBirthYear())
                .setDeathYear(nameBasics.getDeathYear())
                .setPrimaryProfession(nameBasics.getPrimaryProfession())
                .setKnownForTitles(titleBasics);
    }
}
