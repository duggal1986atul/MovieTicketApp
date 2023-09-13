package com.ticket.movie.service;

import com.ticket.movie.dto.RequestDTO;
import com.ticket.movie.model.MovieTransaction;
import com.ticket.movie.model.TicketDetails;
import com.ticket.movie.repository.MovieRepository;
import com.ticket.movie.utility.TicketProcessor;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@Transactional
public class MovieServiceImpl implements MovieService{

    private final MovieRepository movieRepository;


    private final TicketProcessor ticketProcessor;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, TicketProcessor ticketProcessor) {
        this.movieRepository = movieRepository;
        this.ticketProcessor = ticketProcessor;
    }

    @Override
    public List<MovieTransaction> getAllMovieTransactions() {
        log.info("MovieServiceImpl.getAllTickets");

        List<MovieTransaction> movieTransactions = movieRepository.findAll();
        if(ObjectUtils.isEmpty(movieTransactions)) {
            return Collections.emptyList();
        }
        return movieTransactions;
    }

    @Override
    public List<TicketDetails> getAllTicketsById(final Integer transactionId) {
        log.info("getTicketsById {}", transactionId);
        final Optional<MovieTransaction> optionalTransaction= movieRepository.findById(transactionId);

        if (ObjectUtils.isEmpty(optionalTransaction) ||!(optionalTransaction.isPresent() || (ObjectUtils.isEmpty(optionalTransaction.get()
                .getTicketDetails())))) {
            return Collections.emptyList();
        }
        return optionalTransaction.get()
                .getTicketDetails();

    }

    @Override
    public MovieTransaction processTicketCosts(@NotNull RequestDTO requestDTO) {
        log.info("MovieServiceImpl.processTicketCosts");
        MovieTransaction movieTransaction = ticketProcessor.processTicket(requestDTO);
        if (ObjectUtils.isEmpty(movieTransaction)){
            return null;
        }
        else {
                return movieRepository.save(movieTransaction);
        }

    }
}
