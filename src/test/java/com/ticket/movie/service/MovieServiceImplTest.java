package com.ticket.movie.service;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.ticket.movie.configuration.MovieConfiguration;
import com.ticket.movie.dto.CustomerDTO;
import com.ticket.movie.dto.RequestDTO;
import com.ticket.movie.model.TicketDetails;
import com.ticket.movie.model.MovieTransaction;
import com.ticket.movie.model.TicketType;
import com.ticket.movie.repository.MovieRepository;
import com.ticket.movie.utility.TicketProcessor;
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
    @Mock
    private TicketProcessor ticketProcessor;

    @Mock
    private MovieConfiguration movieConfiguration;

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

    @Test
    public void getTicketDetailsForEmptyTicketList() throws Exception {
        when(movieRepository.findById(10)).thenReturn(Optional.empty());

        final List<TicketDetails> response = movieService.getAllTicketsById(10);

        assertNotNull(response);
        assertEquals(0,response.size());
    }


    @Test
    public void getTicketDetailsForEmptyTicketDetails() throws Exception {
        when(movieRepository.findById(10)).thenReturn(Optional.of(getMovieTransactionEmptyList()));

        final List<TicketDetails> response = movieService.getAllTicketsById(10);

        assertNotNull(response);
        assertEquals(0,response.size());
    }
    @Test
    public void testProcessTicketIntegration() throws Exception {
        when(ticketProcessor.processTicket(dataSetupSeniorChild())).thenReturn(getMovieTransaction());
        when(movieRepository.save(any())).thenReturn(getMovieTransaction());

        final MovieTransaction movieTransaction = movieService.processTicketCosts(dataSetupSeniorChild());

        assertNotNull(movieTransaction);
        assertEquals(2,movieTransaction.getTicketDetails().size());
    }
    @Test
    public void testProcessTicketIntegrationReturnsEmpty() throws Exception {
        when(ticketProcessor.processTicket(dataSetupSeniorChild())).thenReturn(null);

        final MovieTransaction movieTransaction = movieService.processTicketCosts(dataSetupSeniorChild());

        assertNull(movieTransaction);
        verify(movieRepository, never()).save(any());
    }


    private RequestDTO dataSetupSeniorChild() {
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setTransactionId(1);
        CustomerDTO customerDTOSenior = new CustomerDTO();
        customerDTOSenior.setAge(70);
        customerDTOSenior.setName("JOHN");
        CustomerDTO customerDTOJunior = new CustomerDTO();
        customerDTOJunior.setName("Dorothy");
        customerDTOJunior.setAge(6);
        requestDTO.setCustomers(List.of(customerDTOJunior,customerDTOSenior));
        return requestDTO;

    }

    private MovieTransaction getMovieTransaction(){
        MovieTransaction movieTransaction = new MovieTransaction();
        movieTransaction.setTransactionId(1);
        movieTransaction.setTotalCost(27.5);
        TicketDetails details = new TicketDetails();
        details.setQuantity(2);
        details.setTicketType(TicketType.CHILDREN.name());
        details.setTotalCost(10.0);
        TicketDetails senior = new TicketDetails();
        senior.setTotalCost(17.5);
        senior.setQuantity(1);
        senior.setTicketType(TicketType.SENIOR.name());
        movieTransaction.setTicketDetails(List.of(senior,details));
        return movieTransaction;
    }
    private MovieTransaction getMovieTransactionEmptyList(){
        MovieTransaction movieTransaction = new MovieTransaction();
        movieTransaction.setTransactionId(1);
        movieTransaction.setTotalCost(27.5);
        return movieTransaction;
    }
  }
