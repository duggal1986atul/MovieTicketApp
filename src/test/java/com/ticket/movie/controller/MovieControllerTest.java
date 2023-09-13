package com.ticket.movie.controller;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ticket.movie.dto.RequestDTO;
import com.ticket.movie.model.MovieTransaction;
import com.ticket.movie.model.TicketDetails;
import com.ticket.movie.model.TicketType;
import com.ticket.movie.service.MovieService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;



@WebMvcTest(MovieController.class)
public class MovieControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private MovieService movieService;

    private List<MovieTransaction> transactionList;
    private List<TicketDetails> ticketDetailsList;

    private TicketDetails ticket;

    private MovieTransaction movieTransaction;

    @BeforeEach
    public void setup() {
        transactionList = Lists.newArrayList();
        ticketDetailsList = Lists.newArrayList();
        ticket= new TicketDetails();
        ticket.setTicketType(TicketType.CHILDREN);
        ticket.setQuantity(5);
        ticket.setTransactionId(10);
        ticket.setCost(30.0);
        ticketDetailsList.add(ticket);
        movieTransaction = new MovieTransaction();
        movieTransaction.setTransactionId(10);
        movieTransaction.setTicketDetails(ticketDetailsList);
        movieTransaction.setTotalCost(40.0);
        transactionList.add(movieTransaction);
    }

    @Test
    public void testGetAllMovieTickets() throws Exception {
        final Integer transactionId = 10;
        given(movieService.getAllTicketsById(transactionId)).willReturn(ticketDetailsList);
        mvc.perform(get("/transactions/{transactionId}/tickets",10).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void testGetAllMovieTransactions() throws Exception {
        given(movieService.getAllMovieTransactions()).willReturn(transactionList);
        mvc.perform(get("/transactions").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));
    }
    @Test
    public void testGetAllMovieTicketsBadRequest() throws Exception {
        final Integer transactionId = 10;
        given(movieService.getAllTicketsById(transactionId)).willReturn(ticketDetailsList);
        mvc.perform(get("/transactions/null/tickets").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }


    @Test
    public void testPostAllMovieTicketsBadRequest() throws Exception {
        String body = objectMapper.writeValueAsString(setInvalidRequestBody());
        mvc.perform(post("/addTransactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(body))
                //Then
                .andExpect(status().isBadRequest());
    }

    private RequestDTO setInvalidRequestBody()
    {
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setTransactionId(10);
        requestDTO.setCustomers(Collections.EMPTY_LIST);
        return  requestDTO;
    }
  }
