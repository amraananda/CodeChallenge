package com.disney.studios.domain;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Entity
public class Vote {

	@Id
	private VoteId voteId;
	@Enumerated(EnumType.STRING)
	private VoteType voteType;

	public VoteId getVoteId() {
		return voteId;
	}

	public void setVoteId(VoteId voteId) {
		this.voteId = voteId;
	}

	public VoteType getVoteType() {
		return voteType;
	}

	public void setVoteType(VoteType voteType) {
		this.voteType = voteType;
	}

}
