package com.disney.studios.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
<<<<<<< HEAD
=======
import javax.persistence.Transient;
>>>>>>> 283ef9010e09306886ed9f6c4ebd086bf1ee58d5

@Entity
public class Dog implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	private String url;

	@Enumerated(EnumType.STRING)
	private Breed breed;
	
<<<<<<< HEAD
=======
	@Transient
	private Long upCount;
	
	@Transient
	private Long downCount;

>>>>>>> 283ef9010e09306886ed9f6c4ebd086bf1ee58d5
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Breed getBreed() {
		return breed;
	}

	public void setBreed(Breed breed) {
		this.breed = breed;
	}
<<<<<<< HEAD
=======

	public Long getUpCount() {
		return upCount;
	}

	public void setUpCount(Long upCount) {
		this.upCount = upCount;
	}

	public Long getDownCount() {
		return downCount;
	}

	public void setDownCount(Long downCount) {
		this.downCount = downCount;
	}
>>>>>>> 283ef9010e09306886ed9f6c4ebd086bf1ee58d5
	

}
