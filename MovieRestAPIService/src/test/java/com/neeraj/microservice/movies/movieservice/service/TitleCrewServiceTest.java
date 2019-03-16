package com.neeraj.microservice.movies.movieservice.service;

import com.neeraj.microservice.movies.movieservice.model.TitleCrew;
import com.neeraj.microservice.movies.movieservice.repository.TitleCrewRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

/*
  This is a very light version of the testing.
 */
@RunWith(MockitoJUnitRunner.class)
public class TitleCrewServiceTest {

    private TitleCrewService titleCrewService;

    @Mock
    private TitleCrewRepository titleCrewRepository;

    @Before
    public void setup() {
        titleCrewService = new TitleCrewService(titleCrewRepository);
    }

    @Test
    public void searchTitleCrewById_returns_titlecrew() {

        TitleCrew titleCrew = new TitleCrew()
                .setTconst("t00001")
                .setWriters("n00001");
        given(titleCrewRepository.findById(anyString())).willReturn(java.util.Optional.ofNullable(titleCrew));

        Optional<TitleCrew> titleCrewById = titleCrewService.searchTitleCrewById(titleCrew.getTconst());

        Assertions.assertThat(titleCrewById.isPresent()).isTrue();
        Assertions.assertThat(titleCrewById.get()).isEqualTo(titleCrew);
    }

    @Test(expected = NullPointerException.class)
    public void searchTitleCrewById_returns_titlecrewNotFound() {

        given(titleCrewRepository.findById(anyString())).willReturn(null);
        Optional<TitleCrew> titleCrew = titleCrewService.searchTitleCrewById("t000");
        Assertions.assertThat(titleCrew.get()).isEqualTo(null);
    }
}