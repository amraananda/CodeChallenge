package com.disney.studios.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.disney.studios.dao.VoteRepository;
import com.disney.studios.domain.Vote;
import com.disney.studios.exception.VoteAlreadyExistsException;

@Service
public class VoteService {

	@Autowired
	private VoteRepository voteRepository;

	public void castVote(Vote vote) throws Exception{
		Vote existingVote = voteRepository.findOne(vote.getVoteId());
		if(existingVote != null){
			throw new VoteAlreadyExistsException("You have already casted your vote for this dog with id = "+vote.getVoteId().getDogId());
		}else{
			voteRepository.save(vote);
		}
	}
}
