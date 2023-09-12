package com.ticket.movie.service;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.ticket.movie.model.TicketDetails;
import com.ticket.movie.model.MovieTransaction;
import com.ticket.movie.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;



@RunWith(MockitoJUnitRunner.class)
public class MovieServiceImplTest {

    @InjectMocks
    private MovieServiceImpl movieService;
    @Mock
    private MovieRepository movieRepository;

    final MovieTransaction movieTransaction = mock(MovieTransaction.class);

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllTransactionsWithData() throws Exception {
        final List<MovieTransaction> movieTransactions = mock(List.class);
        when(movieRepository.findAll()).thenReturn(movieTransactions);
        final List<MovieTransaction> response = movieService.getAllMovieTransactions();
        assertNotNull(response);
    }

    @Test
    public void getAllTransactionsReturnsEmptyList() throws Exception {
        when(movieRepository.findAll()).thenReturn(null);
        final List<MovieTransaction> response = movieService.getAllMovieTransactions();
        assertNotNull(response);
        assertEquals(0,response.size());
    }

    @Test
    public void getTicketDetailsData() throws Exception {
        when(movieRepository.findById(10)).thenReturn(Optional.of(movieTransaction));
        final List<TicketDetails> response = movieService.getAllTicketsById(10);
        assertNotNull(response);
    }

    @Test
    public void getTicketDetailsForEmptyList() throws Exception {
        when(movieRepository.findById(10)).thenReturn(Optional.empty());
        final List<TicketDetails> response = movieService.getAllTicketsById(10);
        assertNotNull(response);
        assertEquals(0,response.size());
    }
  }
