package com.neeraj.microservice.movies.movieservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neeraj.microservice.movies.movieservice.model.NameBasics;
import com.neeraj.microservice.movies.movieservice.service.NameBasicsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NameControllerTest {

    private MockMvc mockMVC;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private NameBasicsService nameBasicsService;

    @Before
    public void setUp() {
        this.mockMVC = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void test_searchByName_positive() {

        NameBasics nameBasics = new NameBasics()
                .setNconst("nn00001")
                .setPrimaryName("Sample Name")
                .setBirthYear(1992)
                .setDeathYear(0)
                .setPrimaryProfession("Singing, Dancing, Hiking")
                .setKnownForTitles("");

        given(nameBasicsService.searchArtistById(anyString()))
                .willReturn(java.util.Optional.ofNullable(nameBasics));


    }

}
