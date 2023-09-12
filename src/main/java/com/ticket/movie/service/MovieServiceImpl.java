package com.ticket.movie.service;

import com.ticket.movie.model.TicketDetail;
import com.ticket.movie.model.Transaction;
import com.ticket.movie.repository.MovieRepository;
import exception.MovieServiceException;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;


@Slf4j
@Service
public class MovieServiceImpl implements MovieService{

    private final MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Transaction> getAllTickets() throws MovieServiceException {
        log.info("MovieServiceImpl.getAllTickets");

        List<Transaction> transactions = movieRepository.findAll();
        if(ObjectUtils.isEmpty(transactions)) {
            log.error("transactions listing not found");
            throw new MovieServiceException("transactions listing not found");
        }
        return transactions;
    }

    @Override
    public List<TicketDetail> getTicketsById(final Integer transactionId) throws MovieServiceException {
        if (ObjectUtils.isEmpty(transactionId)) {
            log.error("invalid transactionId");
            throw new MovieServiceException("invalid transactionId");
        }
        log.info("getTicketsById {}", transactionId);
        final Optional<Transaction> optionalTransaction= movieRepository.findById(transactionId);

        if (!(optionalTransaction.isPresent() || (ObjectUtils.isEmpty(optionalTransaction.get()
                .getTicketDetails())))) {
            throw new MovieServiceException("Ticket Listings not Found");
        }
        return optionalTransaction.get()
                .getTicketDetails();

    }
}
