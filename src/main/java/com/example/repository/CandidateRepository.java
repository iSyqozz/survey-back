package com.example.repository;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.model.Candidate;
import org.springframework.stereotype.Repository;


@Repository
@ComponentScan
public interface CandidateRepository extends MongoRepository<Candidate, String> {
    // You can add custom query methods here if needed
}
