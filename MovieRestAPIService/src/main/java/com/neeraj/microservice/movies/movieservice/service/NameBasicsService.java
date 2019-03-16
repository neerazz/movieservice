package com.neeraj.microservice.movies.movieservice.service;

import com.neeraj.microservice.movies.movieservice.domain.NameBasicsTitleBasicsDto;
import com.neeraj.microservice.movies.movieservice.exceptions.NoSuchObjectFoundException;
import com.neeraj.microservice.movies.movieservice.model.NameBasics;
import com.neeraj.microservice.movies.movieservice.model.TitleBasics;
import com.neeraj.microservice.movies.movieservice.repository.NameBasicsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class NameBasicsService {

    private NameBasicsRepository nameBasicsRepository;

    @Autowired
    private TitleBasicsService titleBasicsService;

    @Autowired
    private PagingService pagingService;

    public NameBasicsService(NameBasicsRepository nameBasicsRepository) {
        this.nameBasicsRepository = nameBasicsRepository;
    }

    public List<NameBasics> searchArtistByName(String searchString, Integer maxResults, Integer pageNumber) {
        Pageable pageable = pagingService.getPageable(maxResults, pageNumber);
        return nameBasicsRepository.findByPrimaryNameIgnoreCaseContaining(searchString, pageable);
    }

    public Optional<NameBasics> searchArtistById(String anyString) {
        return nameBasicsRepository.findById(anyString);
    }

    public NameBasicsTitleBasicsDto searchByArtistIdWithTitles(String uniqueId) {
        NameBasics nameBasics = searchArtistById(uniqueId)
                .orElseThrow(() -> new NoSuchObjectFoundException("No any Artist Found by ID:" + uniqueId));
        return getNameBasicsWithIndividuallyTitles(nameBasics);
    }

    public List<NameBasicsTitleBasicsDto> searchArtistByNameWithTitles(String searchString, Integer maxResults, Integer pageNumber) {

        List<NameBasics> nameBasics = searchArtistByName(searchString, maxResults, pageNumber);
        return nameBasics.parallelStream()
                .map(nameBasic -> getNameBasicsWithIndividuallyTitles(nameBasic))
                .collect(Collectors.toList());

    }

    private NameBasicsTitleBasicsDto getNameBasicsWithIndividuallyTitles(NameBasics nameBasics) {
        Set<TitleBasics> titleBasics = Arrays.asList(nameBasics.getKnownForTitles().split(","))
                .parallelStream()
                .filter(tconst -> tconst.trim().length() == 9)
                .map(tconst -> titleBasicsService.findById(tconst))
                .filter(Optional::isPresent)
                .map(tb -> tb.get())
                .collect(Collectors.toSet());
        ModelMapper modelMapper = new ModelMapper();
        NameBasicsTitleBasicsDto nameBasicsTitleBasicsDto = modelMapper.map(nameBasics, NameBasicsTitleBasicsDto.class);
        return nameBasicsTitleBasicsDto.setKnownForTitles(titleBasics);
    }
}
