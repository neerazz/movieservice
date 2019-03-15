package com.neeraj.microservice.movies.movieservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neeraj.microservice.movies.movieservice.domain.TitleBasicsNameDto;
import com.neeraj.microservice.movies.movieservice.domain.TitleBasicsPrinciplesDto;
import com.neeraj.microservice.movies.movieservice.model.TitleBasics;
import com.neeraj.microservice.movies.movieservice.service.TitleBasicsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static com.neeraj.microservice.movies.movieservice.constants.APIConstants.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/*
   Testing using SpringRunner.
*/

@RunWith(SpringRunner.class)
@SpringBootTest
public class TitleBasicsControllerTest {

    private MockMvc mockMvc;

    private String maxResults = "10";
    private String pageNumber = "1";

    @InjectMocks
    private TitleBasicsController titleBasicsController;

    @Mock
    private TitleBasicsService titleBasicsService;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(titleBasicsController).build();
    }

    @Test
    public void getTitleByName_test_happypath() throws Exception {
        Page<TitleBasics> pagedTitleBasicsList = createPagedTitleBasicsList();
        when(titleBasicsService.searchTitleBasicsByName(anyString(), anyInt(), anyInt()))
                .thenReturn(pagedTitleBasicsList);

        mockMvc
                .perform(
                        get(SEARCH_TITLEBASICS_BY_NAME)
                                .param("searchString", createTitleBasic().getOriginalTitle())
                                .param("size", maxResults)
                                .param("page", pageNumber)
                                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(getJsonString(pagedTitleBasicsList)));

        //    This step verifies weather the mocking is done or not.
        verify(titleBasicsService).searchTitleBasicsByName(anyString(), anyInt(), anyInt());
    }

    @Test
    public void getTitleByName_test_sadpath_paramater_validation() throws Exception {

        mockMvc
                .perform(
                        get(SEARCH_TITLEBASICS_BY_NAME)
                                .param("size", maxResults)
                                .param("page", pageNumber)
                                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isBadRequest());

        mockMvc
                .perform(
                        get(SEARCH_TITLEBASICS_BY_NAME)
                                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getTitleByName_test_sadpath_invalid_paramater() throws Exception {

        mockMvc
                .perform(
                        get(SEARCH_TITLEBASICS_BY_NAME)
                                .param("searchString", createTitleBasic().getOriginalTitle())
                                .param("size", maxResults + "  ")
                                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getTitlebyID_happypath() throws Exception {
        TitleBasics titleBasic = createTitleBasic();
        when(titleBasicsService.searchTitleBasicsByID(titleBasic.getTconst())).thenReturn(java.util.Optional.ofNullable(titleBasic));
        mockMvc
                .perform(get(SEARCH_TTITLEBASICS_BY_TITLEID, titleBasic.getTconst()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(getJsonString(titleBasic)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getTitlebyID_sadpath_empty() throws Exception {
        TitleBasics titleBasic = createTitleBasic();
        mockMvc
                .perform(get(SEARCH_TTITLEBASICS_BY_TITLEID))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getTitlebyIdWithPrinciples_happypath() throws Exception {
        TitleBasicsPrinciplesDto titlebasicPrincple = createTitlebasicPrincple();
        when(titleBasicsService.searchTitleBasicByIdWithPrinciples(anyString()))
                .thenReturn(titlebasicPrincple);

        mockMvc
                .perform(
                        get(
                                SEARCH_TTITLEBASICS_BY_TITLEID_WITHPRINCIPLES,
                                titlebasicPrincple.getTconst()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(getJsonString(titlebasicPrincple)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getTitlebyIdWithPrinciples_sadpath_invalidpathvalue() throws Exception {
        TitleBasicsPrinciplesDto titlebasicPrincple = createTitlebasicPrincple();
        when(titleBasicsService.searchTitleBasicByIdWithPrinciples(anyString()))
                .thenReturn(titlebasicPrincple);

        mockMvc
                .perform(get(SEARCH_TTITLEBASICS_BY_TITLEID_WITHPRINCIPLES))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void getTitlebyIdWithPrinciplesWithName_happypath() throws Exception {

        TitleBasicsNameDto titleBasicName = createTitleBasicName();
        when(titleBasicsService.searchTitleBasicByIdWithName(anyString())).thenReturn(titleBasicName);

        mockMvc
                .perform(
                        get(
                                SEARCH_TTITLEBASICS_BY_TITLEID_WITHPRINCIPLES_WITHNAME,
                                titleBasicName.getTconst()))
                .andDo(print())
                .andExpect(content().string(getJsonString(titleBasicName)));
    }

    private TitleBasicsNameDto createTitleBasicName() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(createTitleBasic(), TitleBasicsNameDto.class);
    }

    private TitleBasicsPrinciplesDto createTitlebasicPrincple() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(createTitleBasic(), TitleBasicsPrinciplesDto.class);
    }

    private Page<TitleBasics> createPagedTitleBasicsList() {
        List<TitleBasics> objectList = Collections.singletonList(createTitleBasic());
        Page<TitleBasics> output =
                new PageImpl<>(
                        objectList,
                        PageRequest.of(Integer.parseInt(maxResults), Integer.parseInt(pageNumber)),
                        objectList.size());
        return output;
    }

    private String getJsonString(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    private TitleBasics createTitleBasic() {
        return new TitleBasics()
                .setTconst("t00000")
                .setOriginalTitle("Original Title")
                .setPrimaryTitle("Primirary Title")
                .setAdult(false)
                .setRuntimeMinutes(20);
    }
}
