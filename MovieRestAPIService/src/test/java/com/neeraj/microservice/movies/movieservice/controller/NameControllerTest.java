package com.neeraj.microservice.movies.movieservice.controller;

import com.neeraj.microservice.movies.movieservice.domain.NameBasicsTitleBasicsDto;
import com.neeraj.microservice.movies.movieservice.model.NameBasics;
import com.neeraj.microservice.movies.movieservice.service.NameBasicsService;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static com.neeraj.microservice.movies.movieservice.constants.APIConstants.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(NameController.class)
public class NameControllerTest {

    @Autowired
    private MockMvc mockMVC;

    private String maxResults = "10";
    private String pageNumber = "1";

    @MockBean
    private NameBasicsService nameBasicsService;

    @Test
    public void searchArtistById_test_happy_path() throws Exception {
        NameBasics aTestObject = createTestNameBasicObject();

        given(nameBasicsService.searchArtistById(aTestObject.getNconst()))
                .willReturn(java.util.Optional.ofNullable(aTestObject));

        mockMVC
                .perform(get(SEARCH_ARTIST_BY_ARTISTID, aTestObject.getNconst()))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test(expected = IllegalArgumentException.class)
    public void searchArtistById_test_sad_path() throws Exception {
        mockMVC
                .perform(get(SEARCH_ARTIST_BY_ARTISTID))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void searchByName_happy_path() throws Exception {
        NameBasics nameBasics = createTestNameBasicObject();
        given(nameBasicsService.searchArtistByName(anyString(), anyInt(), anyInt()))
                .willReturn(Collections.singletonList(nameBasics));

        mockMVC
                .perform(
                        get(SEARCH_ARTIST_BY_NAME)
                                .param("searchString", nameBasics.getPrimaryName())
                                .param("size", maxResults)
                                .param("page", pageNumber)
                                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", Matchers.hasSize(1)));

        verify(nameBasicsService).searchArtistByName(anyString(), anyInt(), anyInt());
    }

    @Test
    public void searchByName_sad_path_with_missingParm() throws Exception {
        NameBasics nameBasics = createTestNameBasicObject();
        given(nameBasicsService.searchArtistByName(anyString(), anyInt(), anyInt()))
                .willReturn(Collections.singletonList(nameBasics));

        mockMVC
                .perform(
                        get(SEARCH_ARTIST_BY_NAME)
                                .param("size", maxResults)
                                .param("page", pageNumber)
                                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void searchByName_sad_path_with_incorrectparmtype() throws Exception {
        NameBasics nameBasics = createTestNameBasicObject();
        given(nameBasicsService.searchArtistByName(anyString(), anyInt(), anyInt()))
                .willReturn(Collections.singletonList(nameBasics));

        mockMVC
                .perform(
                        get(SEARCH_ARTIST_BY_NAME)
                                .param("searchString", nameBasics.getPrimaryName())
                                .param("size", " 10 ")
                                .param("page", pageNumber)
                                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print());
    }

    @Test
    public void searchByArtistIdWithTitles_happy_path() throws Exception {
        NameBasicsTitleBasicsDto nameBasicTitleBasic = createNameBasicsTitleBasicsDto();
        given(nameBasicsService.searchByArtistIdWithTitles(anyString()))
                .willReturn(nameBasicTitleBasic);

        mockMVC
                .perform(
                        get(
                                SEARCH_ARTIST_BY_ARTISTID_WITHTITLES,
                                nameBasicTitleBasic.getNconst()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nconst").value(nameBasicTitleBasic.getNconst()))
                .andExpect(jsonPath("$.deathYear").value(nameBasicTitleBasic.getDeathYear()))
                .andExpect(jsonPath("$.knownForTitles").isEmpty());
    }

    @Test
    public void searchByArtistIdWithTitles_sad_path() throws Exception {
        mockMVC
                .perform(get(SEARCH_ARTIST_BY_ARTISTID_WITHTITLES, ""))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    private NameBasicsTitleBasicsDto createNameBasicsTitleBasicsDto() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(createTestNameBasicObject(), NameBasicsTitleBasicsDto.class);
    }

    private NameBasics createTestNameBasicObject() {
        return new NameBasics()
                .setNconst("nn00001")
                .setPrimaryName("Sample Name")
                .setBirthYear(1992)
                .setDeathYear(0)
                .setPrimaryProfession("Singing, Dancing, Hiking")
                .setKnownForTitles("");
    }
}
