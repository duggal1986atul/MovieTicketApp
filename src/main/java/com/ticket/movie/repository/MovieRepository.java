package com.ticket.movie.repository;

import com.ticket.movie.model.MovieTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<MovieTransaction, Integer> {
}
