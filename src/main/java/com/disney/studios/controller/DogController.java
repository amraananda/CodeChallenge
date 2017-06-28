package com.disney.studios.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.disney.studios.domain.Breed;
import com.disney.studios.service.DogService;

@RestController
@RequestMapping("dogs")
public class DogController {
	
	@Autowired
	private DogService dogService;
	
	@RequestMapping(method=RequestMethod.GET)
	public Map<String,List<String>>getAllDogPicsGroupedByBreed() throws Exception{
		return dogService.findAllDogPicsGroupedByBreed();
	}
	
	@RequestMapping(value="/{breed}",method=RequestMethod.GET)
	public List<String>getAllDogPicsByBreed(@PathVariable("breed")Breed breed) throws Exception{
		return dogService.findAllDogPicsByBreedSortedByUpCount(breed);
	}



}
