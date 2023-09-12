package com.ticket.movie.service;

import com.ticket.movie.model.TicketDetail;
import com.ticket.movie.model.Transaction;
import exception.MovieServiceException;

import java.util.List;

public interface MovieService {
    public List<Transaction> getAllTickets() throws MovieServiceException;
    public List<TicketDetail>getTicketsById(final Integer transactionId) throws MovieServiceException;

}
