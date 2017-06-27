package com.disney.studios.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.disney.studios.domain.Vote;
import com.disney.studios.service.VoteService;

@RestController
@RequestMapping("votes")
public class VoteController {

	@Autowired
	private VoteService voteService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<HttpStatus> castVote(@RequestBody Vote vote) throws Exception {
		voteService.castVote(vote);
		return new ResponseEntity<HttpStatus>(HttpStatus.CREATED);
	}
}
