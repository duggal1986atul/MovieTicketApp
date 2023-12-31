package com.ticket.movie.service;

import com.ticket.movie.dto.RequestDTO;
import com.ticket.movie.model.TicketDetails;
import com.ticket.movie.model.MovieTransaction;

import java.util.List;

public interface MovieService {
    public List<MovieTransaction> getAllMovieTransactions();
    public List<TicketDetails> getAllTicketsById(final Integer transactionId);
    public MovieTransaction processTicketCosts(final RequestDTO requestDTO);

}
