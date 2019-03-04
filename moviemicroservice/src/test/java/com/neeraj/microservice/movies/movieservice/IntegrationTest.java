package com.neeraj.microservice.movies.movieservice;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neeraj.microservice.movies.movieservice.model.NameBasics;
import com.neeraj.microservice.movies.movieservice.wrapperclass.NameBasicsList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.util.List;

import static com.neeraj.microservice.movies.movieservice.constants.APIConstants.SEARCH_ARTIST_BY_NAME;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest {


    private MockMvc mockMVC;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        this.mockMVC = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void search_artist_by_name_shouldReturn_list_of_NameBasics() throws Exception {
        String searchString = "Test";
        String maxResults = "100";
        String pageNumber = "2";

        NameBasicsList nameBasicsList = new NameBasicsList();
        MvcResult mvcResult = mockMVC.perform(

//      Changed the end point URL. Removed the searchString from Path variable and keeping in Request Parm.
//                get(SEARCH_ARTIST_BY_NAME, searchString)
                get(SEARCH_ARTIST_BY_NAME)
                        .param("searchString", searchString)
                        .param("maxResults", maxResults)
                        .param("pageNumber", pageNumber)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();

        List<NameBasics> response = convertStringToListOfObjects(mvcResult.getResponse().getContentAsString());

//        Test to validate the response size.
        assertThat(response.size()).isLessThanOrEqualTo(Integer.parseInt(maxResults));

//        Test to check if the response received was correct.
        response
                .parallelStream()
                .map(o -> o.getPrimaryName())
                .forEach(
                        s -> assertThat(s.toUpperCase().contains(searchString.toUpperCase()))
                );
    }

    private List<NameBasics> convertStringToListOfObjects(String string) throws IOException {
        return objectMapper.readValue(string, new TypeReference<List<NameBasics>>() {
        });
    }
}
