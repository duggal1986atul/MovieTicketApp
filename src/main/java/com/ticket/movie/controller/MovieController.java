package com.ticket.movie.controller;


import com.ticket.movie.dto.RequestDTO;
import com.ticket.movie.model.TicketDetails;
import com.ticket.movie.model.MovieTransaction;
import com.ticket.movie.service.MovieService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping(path = "/transactions", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MovieTransaction>> getAllMovieTransactions() {
        log.info("MovieController.getAllTickets");
        return new ResponseEntity<>(movieService.getAllMovieTransactions(), HttpStatus.OK);
    }

    @PostMapping(path = "/addTransactions",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MovieTransaction> postMovieTransactions(@Valid @RequestBody RequestDTO requestDTO)  {
        log.info("MovieController.postMovieTransactions");
        MovieTransaction transaction = movieService.processTicketCosts(requestDTO);
        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }

    @GetMapping(path = "/transactions/{transactionId}/tickets")
    public ResponseEntity<List<TicketDetails>> getAllTicketsById(@PathVariable final Integer transactionId) {
        log.info("MovieController.getAllTicketsById");
        return new ResponseEntity<>(movieService.getAllTicketsById(transactionId), HttpStatus.OK);
    }
  }
