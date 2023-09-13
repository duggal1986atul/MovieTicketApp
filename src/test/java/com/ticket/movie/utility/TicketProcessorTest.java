package com.ticket.movie.utility;

import com.ticket.movie.configuration.MovieConfiguration;
import com.ticket.movie.dto.CustomerDTO;
import com.ticket.movie.dto.RequestDTO;
import com.ticket.movie.model.MovieTransaction;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class TicketProcessorTest {

    @InjectMocks
    private TicketProcessor ticketProcessor;
    @Mock
    private MovieConfiguration movieConfiguration;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);

    }


    @Test
    public void processTicketTest()throws  Exception {
        when(movieConfiguration.getSeniorAge()).thenReturn(55);
        when(movieConfiguration.getTeenAge()).thenReturn(11);
        when(movieConfiguration.getChildrenFare()).thenReturn(5.0);
        when(movieConfiguration.getChildrenDiscount()).thenReturn(25.0);
        when(movieConfiguration.getSeniorDiscount()).thenReturn(30.0);
        when(movieConfiguration.getAdultFare()).thenReturn(25.0);
        when(movieConfiguration.getAdultAge()).thenReturn(18);

        MovieTransaction transaction = ticketProcessor.processTicket(dataSetupSeniorChild());

        assertNotNull(transaction);
        assertEquals(2,transaction.getTicketDetails().size());
    }

    @Test
    public void processTicketChildren()throws  Exception {
        when(movieConfiguration.getSeniorAge()).thenReturn(55);
        when(movieConfiguration.getTeenAge()).thenReturn(11);
        when(movieConfiguration.getChildrenFare()).thenReturn(5.0);
        when(movieConfiguration.getChildrenDiscount()).thenReturn(25.0);
        when(movieConfiguration.getAdultAge()).thenReturn(18);

        MovieTransaction transaction = ticketProcessor.processTicket(dataSetupChildren());

        assertNotNull(transaction);
        assertEquals(1,transaction.getTicketDetails().size());
    }

    @Test
    public void processTicketTestForAdult()throws  Exception {
        when(movieConfiguration.getSeniorAge()).thenReturn(55);
        when(movieConfiguration.getTeenAge()).thenReturn(11);
        when(movieConfiguration.getAdultFare()).thenReturn(25.0);
        when(movieConfiguration.getAdultAge()).thenReturn(18);

        MovieTransaction transaction = ticketProcessor.processTicket(dataSetupForAdult());

        assertNotNull(transaction);
        assertEquals(1,transaction.getTicketDetails().size());
    }

    @Test
    public void processTicketTestForTeen()throws  Exception {
        when(movieConfiguration.getSeniorAge()).thenReturn(55);
        when(movieConfiguration.getTeenAge()).thenReturn(11);
        when(movieConfiguration.getAdultAge()).thenReturn(18);

        MovieTransaction transaction = ticketProcessor.processTicket(dataSetupForTeen());

        assertNotNull(transaction);
        assertEquals(1,transaction.getTicketDetails().size());
    }
    @Test
    public void processTicketTestForTeenAndAdult()throws  Exception {
        when(movieConfiguration.getSeniorAge()).thenReturn(55);
        when(movieConfiguration.getTeenAge()).thenReturn(11);
        when(movieConfiguration.getAdultAge()).thenReturn(18);
        when(movieConfiguration.getTeenFare()).thenReturn(12.0);
        when(movieConfiguration.getAdultFare()).thenReturn(25.0);

        MovieTransaction transaction = ticketProcessor.processTicket(dataSetupForTeenAndAdult());

        assertNotNull(transaction);
        assertEquals(2,transaction.getTicketDetails().size());
    }

    @Test
    public void processTicketTestForSeniorAndAdult()throws  Exception {
        when(movieConfiguration.getSeniorAge()).thenReturn(55);
        when(movieConfiguration.getTeenAge()).thenReturn(11);
        when(movieConfiguration.getAdultAge()).thenReturn(18);
        when(movieConfiguration.getAdultFare()).thenReturn(25.0);

        MovieTransaction transaction = ticketProcessor.processTicket(dataSetupForSeniorAndAdult());

        assertNotNull(transaction);
        assertEquals(2,transaction.getTicketDetails().size());
    }

    @Test
    public void processTicketTestForEmptyCustomerList()throws  Exception {
        MovieTransaction transaction = ticketProcessor.processTicket(emptyCustomerList());

        assertNotNull(transaction);
        assertNotNull(transaction.getTotalCost());
        assertNotNull(transaction.getTransactionId());
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

    private RequestDTO emptyCustomerList() {
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setTransactionId(1);
        return requestDTO;
    }

    private RequestDTO dataSetupChildren() {
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setTransactionId(1);
        CustomerDTO customerDTOJuniorOne = new CustomerDTO();
        customerDTOJuniorOne.setName("Dorothy");
        customerDTOJuniorOne.setAge(6);
        CustomerDTO customerDTOJunior = new CustomerDTO();
        customerDTOJunior.setName("Lisa");
        customerDTOJunior.setAge(8);
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName("Leila");
        customerDTO.setAge(8);
        requestDTO.setCustomers(List.of(customerDTO,customerDTOJunior,customerDTOJuniorOne));
        return requestDTO;

    }
    private RequestDTO dataSetupForAdult() {
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setTransactionId(1);
        CustomerDTO customerDTOSenior = new CustomerDTO();
        customerDTOSenior.setAge(40);
        customerDTOSenior.setName("JOHN");
        requestDTO.setCustomers(List.of(customerDTOSenior));
        return requestDTO;

    }
    private RequestDTO dataSetupForTeen() {
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setTransactionId(1);
        CustomerDTO customerDTOTeen = new CustomerDTO();
        customerDTOTeen.setAge(13);
        customerDTOTeen.setName("JOHN");
        requestDTO.setCustomers(List.of(customerDTOTeen));
        return requestDTO;

    }

    private RequestDTO dataSetupForTeenAndAdult() {
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setTransactionId(1);
        CustomerDTO customerDTOTeen = new CustomerDTO();
        customerDTOTeen.setAge(13);
        customerDTOTeen.setName("JOHN");
        CustomerDTO customerDTOAdult= new CustomerDTO();
        customerDTOAdult.setAge(29);
        customerDTOAdult.setName("JOHN");
        requestDTO.setCustomers(List.of(customerDTOTeen,customerDTOAdult));
        return requestDTO;
    }

    private RequestDTO dataSetupForSeniorAndAdult() {
        RequestDTO requestDTO = new RequestDTO();
        requestDTO.setTransactionId(1);
        CustomerDTO customerSenior = new CustomerDTO();
        customerSenior.setAge(73);
        customerSenior.setName("JOHN");
        CustomerDTO customerDTOAdult= new CustomerDTO();
        customerDTOAdult.setAge(29);
        customerDTOAdult.setName("JOHN");
        requestDTO.setCustomers(List.of(customerSenior,customerDTOAdult));
        return requestDTO;
    }
 }
