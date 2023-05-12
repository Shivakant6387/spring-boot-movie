package com.example.movies;

import com.example.movies.controller.MoviesController;
import com.example.movies.dto.MoviesDTO;
import com.example.movies.entity.Movies;
import com.example.movies.entity.Ratings;
import com.example.movies.repository.MoviesRepository;
import com.example.movies.service.MoviesService;
import com.example.movies.service.RatingsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.*;

import static org.apache.logging.log4j.ThreadContext.get;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MoviesController.class)
class MoviesApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private RatingsService ratingsService;
	@MockBean
	private MoviesService moviesService;
	@Test
	void contextLoads() {
	}
	@Test
	void getAllRatings() throws Exception {
		List<Ratings> ratingsList = new ArrayList<>();
		Ratings rating1 = new Ratings();
		rating1.setTconst("tt0000001");
		rating1.setNumVotes(1);
		rating1.setAverageRating(4.5);
		ratingsList.add(rating1);
		Ratings rating2 = new Ratings();
		rating2.setTconst("tt0000002");
		rating2.setNumVotes(2);
		rating2.setAverageRating(3.5);
		ratingsList.add(rating2);
		// Set up the mocked service to return the list of Ratings
		Mockito.when(ratingsService.getAllRatings()).thenReturn(ratingsList);
		// Send a GET request to the API endpoint
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/longest-duration-ratings"))
				.andExpect(status().isOk())
				.andReturn();
		// Assert that the response contains the expected JSON data
		ObjectMapper objectMapper = new ObjectMapper();
		String expectedJson = objectMapper.writeValueAsString(ratingsList);
		String actualJson = result.getResponse().getContentAsString();
		Assert.assertEquals(expectedJson, actualJson);
	}

	@Test
	public void testGetLongestDurationMovies() throws Exception {
		// Create a list of Movies objects
		List<Movies> moviesList = new ArrayList<>();
		Movies movie1 = new Movies();
		movie1.setTconst("tt0000002");
		movie1.setTitleType("Movie");
		movie1.setRuntimeMinutes(120);
		movie1.setPrimaryTitle("Carmencita");
		movie1.setGenres("action");
		moviesList.add(movie1);
		Movies movie2 = new Movies();
		movie2.setTconst("tt0000002");
		movie2.setTitleType("Movie");
		movie2.setRuntimeMinutes(120);
		movie2.setPrimaryTitle("Carmencita");
		movie2.setGenres("action");
		moviesList.add(movie2);
		// Set up the mocked service to return the list of Movies
		Mockito.when(moviesService.findTop10ByOrderByRuntimeMinutesDesc()).thenReturn(moviesList);
		// Send a GET request to the API endpoint
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/longest-duration-movies"))
				.andExpect(status().isOk())
				.andReturn();
		// Assert that the response contains the expected JSON data
		ObjectMapper objectMapper = new ObjectMapper();
		String expectedJson = objectMapper.writeValueAsString(moviesList);
		String actualJson = result.getResponse().getContentAsString();
		Assert.assertEquals(expectedJson, actualJson);
	}

	@Test
	public void testCreateMovies() throws Exception {
		// Create a Movies object to be posted
		Movies movie = new Movies();
		movie.setTconst("tt0000005");
		movie.setTitleType("Movie");
		movie.setRuntimeMinutes(120);
		movie.setPrimaryTitle("Carmencita");
		movie.setGenres("action");
		movie.setRuntimeMinutes(120);
		// Set up the mocked service to return a success message
		Mockito.when(moviesService.createMovies(movie)).thenReturn("Movie created successfully");
		// Send a POST request to the API endpoint with the Movies object as the request body
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/new-movie")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(movie)))
				.andExpect(status().isOk())
				.andReturn();
		// Assert that the response contains the expected message
		String expectedMessage = "Movie created successfully";
		String actualMessage = result.getResponse().getContentAsString();
		Assert.assertEquals(expectedMessage, actualMessage);
	}

	@Test
	public void testUpdateRuntimeMinutes() throws Exception {
		// Create a Movies object to be updated
		Movies movie = new Movies();
		movie.setTconst("tt0000005");
		movie.setTitleType("Movie");
		movie.setRuntimeMinutes(120);
		movie.setPrimaryTitle("Carmencita");
		movie.setGenres("action");
		movie.setRuntimeMinutes(120);
		movie.setRuntimeMinutes(120);
		// Set up the mocked service to return a success message
		Mockito.when(moviesService.updateRuntimeMinutes(movie)).thenReturn("Runtime minutes updated successfully");
		// Send a POST request to the API endpoint with the Movies object as the request body
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/update-runtime-minutes")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(movie)))
				.andExpect(status().isOk())
				.andReturn();
		// Assert that the response contains the expected message
		String expectedMessage = "Runtime minutes updated successfully";
		String actualMessage = result.getResponse().getContentAsString();
		Assert.assertEquals(expectedMessage, actualMessage);
	}

	@Test
	public void testGetTopRatedMovies() throws Exception {
		// Create a list of MoviesDTO objects to be returned by the service
		List<MoviesDTO> moviesDTOList = new ArrayList<>();
		MoviesDTO movie1 = new MoviesDTO();
		movie1.setPrimaryTitle("Miss Jerry");
		movie1.setTconst("tt00000021");
		movie1.setAverageRating(8.0);
		movie1.setGenres("Action");
		MoviesDTO movie2 = new MoviesDTO();
		movie2.setPrimaryTitle("Miss Jerry");
		movie2.setTconst("tt00000021");
		movie2.setAverageRating(8.0);
		movie2.setGenres("Action");
		movie2.setAverageRating(7.5);
		moviesDTOList.add(movie1);
		moviesDTOList.add(movie2);
		// Set up the mocked service to return the list of MoviesDTO objects
		Mockito.when(moviesService.getTopRatedMovies()).thenReturn(moviesDTOList);
		// Send a GET request to the API endpoint
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/top-rated-movies"))
				.andExpect(status().isOk())
				.andReturn();
		// Assert that the response contains the expected list of MoviesDTO objects
		String expectedJson = new ObjectMapper().writeValueAsString(moviesDTOList);
		String actualJson = result.getResponse().getContentAsString();
		JSONAssert.assertEquals(expectedJson, actualJson, false);
	}

	@Test
	public void testGetMovie() throws Exception {
		String tconst = "tt1234567";
		Movies movie = new Movies();
		movie.setTconst(tconst);
		movie.setTitleType("Movie");
		movie.setGenres("action");
		movie.setPrimaryTitle("un bon bock");
		movie.setRuntimeMinutes(32);
		Optional<Movies> optionalMovie = Optional.of(movie);

		given(moviesService.getMovie(tconst)).willReturn(optionalMovie);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/{tconst}", tconst))
				.andExpect(status().isOk())
				.andReturn().getResponse();
	}
}
