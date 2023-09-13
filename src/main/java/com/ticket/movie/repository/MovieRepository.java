package com.ticket.movie.repository;

import com.ticket.movie.model.MovieTransaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends MongoRepository<MovieTransaction, Integer> {
}