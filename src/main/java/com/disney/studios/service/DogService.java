package com.disney.studios.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.disney.studios.dao.DogRepository;
import com.disney.studios.domain.Breed;
import com.disney.studios.vo.DogVO;

@Service
public class DogService {
	
	@Autowired
	private DogRepository dogRepository;
	
	public Map<String,List<String>>findAllDogPicsGroupedByBreed() throws Exception{
		Object[]result =  dogRepository.findAllDogsGroupedByBreedSortedByUpCount();
		List<DogVO>dogvos = new ArrayList<>();
		for(Object o:  result){
			Object[]o1 = ((Object[])o);
			DogVO dv = new DogVO(((BigInteger)o1[0]).longValue(),((String)o1[1]),((String)o1[2]),((BigInteger)o1[3]).longValue(),((BigInteger)o1[4]).longValue());
			dogvos.add(dv);
		}
		Map<String,List<String>>bMap = dogvos.stream().collect(Collectors.groupingBy(DogVO::getBreed,Collectors.mapping(DogVO::getUrl, Collectors.toList())));
		
		return bMap;
	}
	
	public List<String>findAllDogPicsByBreedSortedByUpCount(Breed breed)throws Exception{
		Object[]result =  dogRepository.findAllDogsInaBreedSortedByUpCount(breed.name());
		List<String>urls = new ArrayList<>();
		for(Object o:  result){
			System.out.println(o);
			Object[]o1 = ((Object[])o);
			urls.add(((String)o1[2]));
		}
		return urls;
	}
	
	
	
}
