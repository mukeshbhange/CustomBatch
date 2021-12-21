package com.bl.candidate.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bl.candidate.model.Candidate;



@Repository
public interface CandidateRepository extends MongoRepository<Candidate,Long>{
}