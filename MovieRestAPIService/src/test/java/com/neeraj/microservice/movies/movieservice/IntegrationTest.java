package com.neeraj.microservice.movies.movieservice;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neeraj.microservice.movies.movieservice.domain.NameBasicsTitleBasicsDto;
import com.neeraj.microservice.movies.movieservice.model.NameBasics;
import com.neeraj.microservice.movies.movieservice.model.TitleBasics;
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

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static com.neeraj.microservice.movies.movieservice.constants.APIConstants.SEARCH_ARTIST_BY_ARTISTID_WITHTITLES;
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

        MvcResult mvcResult = mockMVC.perform(
//      Changed the end point URL. Removed the searchString from Path variable and keeping in Request Parm.
//                get(SEARCH_ARTIST_BY_NAME, searchString)
                get(SEARCH_ARTIST_BY_NAME)
                        .param("searchString", searchString)
                        .param("size", maxResults)
                        .param("page", pageNumber)
                        .accept(MediaType.APPLICATION_JSON_UTF8)
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        List<NameBasics> response = objectMapper.readValue(contentAsString, new TypeReference<List<NameBasics>>() {
        });
//        List<NameBasics> response = objectMapper.readValue(contentAsString, NameBasicsList.class).getNameBasics();

//        Assert to validate the response size.
        assertThat(response.size()).isLessThanOrEqualTo(Integer.parseInt(maxResults));

//        Assert to check if the response received Has the search string or not.
        response.parallelStream()
                .map(NameBasics::getPrimaryName)
                .forEach(primaryName -> assertThat(primaryName.toUpperCase().contains(searchString.toUpperCase())));
    }

    @Test
    public void search_artist_by_ArtistUniqueID_shouldReturn_list_of_NameBasics_with_TitleBasics() throws Exception {

        String artistUniqueID = "nm1588970";
        String knownForTitle = "tt0000001,tt0057728";
        String maxResults = "100";
        String pageNumber = "2";

        MvcResult mvcResult = mockMVC.perform(
                get(SEARCH_ARTIST_BY_ARTISTID_WITHTITLES, artistUniqueID)
                        .param("maxResults", maxResults)
                        .param("pageNumber", pageNumber)
                        .accept(MediaType.APPLICATION_JSON_UTF8))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        List<NameBasicsTitleBasicsDto> response = objectMapper.readValue(contentAsString, new TypeReference<List<NameBasicsTitleBasicsDto>>() {
        });

//        Assert to validate the response size.
        assertThat(response.size()).isLessThanOrEqualTo(Integer.parseInt(maxResults));

//        Assert to check if the response received Has the search string or not.
        response.parallelStream()
                .forEach(nameBasicsTitleBasicsDto -> {

//                    Assert the Unique ID of the artist.
                    assertThat(nameBasicsTitleBasicsDto.getNconst()).isEqualTo(artistUniqueID);

                    Set<TitleBasics> knownForTitles = nameBasicsTitleBasicsDto.getKnownForTitles();

//                    Assert if the TitleBasics are not empty.
                    assertThat(knownForTitles).isNotEmpty();

//                    Assert if the TitleBasics Unique ID is same as hard coded Unique ID.
                    List<String> strings = Arrays.asList(knownForTitle.split(","));
                    knownForTitles.parallelStream()
                            .forEach(titleBasics -> {
                                assertThat(strings.contains(titleBasics.getTconst()));
                            });
                });
    }
}
