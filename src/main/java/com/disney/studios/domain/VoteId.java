package com.disney.studios.domain;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class VoteId implements Serializable{

	private static final long serialVersionUID = 1L;
	private Long dogId;
	private Long userId;

	public Long getDogId() {
		return dogId;
	}

	public void setDogId(Long dogId) {
		this.dogId = dogId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
