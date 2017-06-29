package com.disney.studios.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.disney.studios.domain.Dog;

public interface DogRepository extends JpaRepository<Dog, Long> {

	@Query(nativeQuery = true, value = "select distinct d.id,d.breed,d.url,(select count(*) from vote v1 where v1.dog_id=d.id and v1.vote_type='UP') as upCount,(select count(*) from vote v1 where v1.dog_id=d.id and v1.vote_type='DOWN') as downCount from Dog d order by d.breed,4 desc")
	public Object[] findAllDogsGroupedByBreedSortedByUpCount();

	@Query(nativeQuery = true, value = "select distinct d.id,d.breed,d.url,(select count(*) from vote v1 where v1.dog_id=d.id and v1.vote_type='UP'),(select count(*) from vote v1 where v1.dog_id=d.id and v1.vote_type='DOWN') from Dog d where d.breed=? order by d.breed,4 desc")
	public Object[] findAllDogsInaBreedSortedByUpCount(String breed);

}
