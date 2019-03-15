package com.neeraj.microservice.movies.movieservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neeraj.microservice.movies.movieservice.model.TitleCrew;
import com.neeraj.microservice.movies.movieservice.service.TitleCrewService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static com.neeraj.microservice.movies.movieservice.constants.APIConstants.SEARCH_TITLECREW_BY_TITLEID;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TitleCrewControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private TitleCrewController titleCrewController;

    @Mock
    private TitleCrewService titleCrewService;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(titleCrewController).build();
    }

    @Test
    public void getTitleCrewById_happypath() throws Exception {
        TitleCrew titleCrew = createTitleCrew();
        when(titleCrewService.searchTitleCrewById(anyString())).thenReturn(java.util.Optional.ofNullable(titleCrew));

        mockMvc.perform(get(SEARCH_TITLECREW_BY_TITLEID, titleCrew.getTconst()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(convertObjectToJSON(titleCrew)));
    }

    private String convertObjectToJSON(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    private TitleCrew createTitleCrew() {
        return new TitleCrew()
                .setTconst("t00000");
    }
}