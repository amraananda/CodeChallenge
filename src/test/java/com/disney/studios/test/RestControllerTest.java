package com.disney.studios.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.disney.studios.Application;
import com.disney.studios.domain.Breed;
import com.disney.studios.domain.Vote;
import com.disney.studios.domain.VoteId;
import com.disney.studios.domain.VoteType;
import com.disney.studios.exception.VoteAlreadyExistsException;
import com.disney.studios.service.DogService;
import com.disney.studios.service.VoteService;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Application.class)
@WebAppConfiguration
@SpringBootTest
public class RestControllerTest {
		
		private MockMvc mockMvc;		

		@Mock
		private DogService dogService;

		@Mock
		private VoteService voteService;
	
		
		@Autowired
		private WebApplicationContext webApplicationContext;
		
		protected MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
				MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

		private HttpMessageConverter mappingJackson2HttpMessageConverter;
		
		@Autowired
		protected void setConverters(HttpMessageConverter<?>[] converters) {
			this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
					.filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().get();
			Assert.assertNotNull("the JSON message converter must not be null", this.mappingJackson2HttpMessageConverter);
		}
				
		
		@Test
		public void testFetchAllDogBreeds() throws Exception{
			Map<String, List<String>> dogBreedMap = new HashMap<>();
			dogBreedMap.put(Breed.YORKIE.name(), Arrays.asList("http://i.imgur.com/oSieVUO.png","http://i.imgur.com/qtXIL.png","http://i.imgur.com/qWLKy8a.jpg"));
			dogBreedMap.put(Breed.LABRADOR.name(), Arrays.asList("http://i.imgur.com/oAieVUO.png","http://i.imgur.com/otXIL.png","http://i.imgur.com/oWLKy8a.jpg"));				
			Mockito.when(dogService.findAllDogPicsGroupedByBreed()).thenReturn(dogBreedMap);
			ResultActions resultActions = this.mockMvc.perform(get("/dogs"));
			resultActions.andExpect(status().is(HttpStatus.OK.value()));
		}
		
		@Test
		public void testGetPicturesByBreed() throws Exception{
			List<String>pics = new ArrayList<>();
			pics.add("http://i.imgur.com/oSieVUO.png");
			pics.add("http://i.imgur.com/qtXIL.png");
			pics.add("http://i.imgur.com/qWLKy8a.jpg");
			Mockito.when(dogService.findAllDogPicsByBreedSortedByUpCount(Breed.YORKIE)).thenReturn(pics);
			ResultActions resultActions = this.mockMvc.perform(get("/dogs/YORKIE"));
			resultActions.andExpect(status().is(HttpStatus.OK.value()));
		}

		@Test
		public void testVoteForDog() throws Exception{
			Vote vote = new Vote();
			VoteId voteId = new VoteId();
			voteId.setDogId(1L);
			voteId.setUserId(1L);
			vote.setVoteId(voteId);
			vote.setVoteType(VoteType.UP);
			Mockito.doNothing().when(voteService).castVote(vote);
			ResultActions resultActions = this.mockMvc.perform(post("/votes").contentType(contentType).content(json(vote)));
			resultActions.andExpect(status().is(HttpStatus.CREATED.value()));
		}
		
		@Test
		public void testVoteForDogAgain() throws Exception{
			Vote vote = new Vote();
			VoteId voteId = new VoteId();
			voteId.setDogId(1L);
			voteId.setUserId(1L);
			vote.setVoteId(voteId);
			vote.setVoteType(VoteType.DOWN);
			Mockito.doThrow(new VoteAlreadyExistsException("u have already casted your vote for this dog with id ="+voteId.getDogId())).when(voteService).castVote(vote);
			ResultActions resultActions = this.mockMvc.perform(post("/votes").contentType(contentType).content(json(vote)));
			resultActions.andExpect(status().is(HttpStatus.NOT_MODIFIED.value()));
		}
		
		@SuppressWarnings("unchecked")
		protected String json(Object o) throws IOException {
			MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
			this.mappingJackson2HttpMessageConverter.write(o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
			return mockHttpOutputMessage.getBodyAsString();
		}
		
		@Before
		public void initMocks(){
			MockitoAnnotations.initMocks(this);
			this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
		}

}
