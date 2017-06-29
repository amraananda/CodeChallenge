package com.disney.studios.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.disney.studios.domain.Vote;
import com.disney.studios.domain.VoteId;

public interface VoteRepository extends JpaRepository<Vote, VoteId>{

}
