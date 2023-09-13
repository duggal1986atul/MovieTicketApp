package com.ticket.movie.utility;

import com.ticket.movie.configuration.MovieConfiguration;
import com.ticket.movie.dto.CustomerDTO;
import com.ticket.movie.dto.RequestDTO;
import com.ticket.movie.model.MovieTransaction;
import com.ticket.movie.model.TicketDetails;
import com.ticket.movie.model.TicketType;
import com.ticket.movie.repository.MovieRepository;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
@Slf4j
@Transactional
public class TicketProcessor {

    private final MovieConfiguration configuration;


    @Autowired
    public TicketProcessor(MovieConfiguration configuration) {
        this.configuration = configuration;
    }

    public MovieTransaction processTicket(@NotNull final RequestDTO requestDTO){
        MovieTransaction movieTransaction= new MovieTransaction();
        List<TicketDetails>ticketDetails = new ArrayList<>();
        movieTransaction.setTransactionId(requestDTO.getTransactionId());
        //condition for adult tickets

        List<CustomerDTO>adultEntries = filter(c -> c.getAge() >=configuration.getAdultAge() && c.getAge()<configuration.getSeniorAge(),requestDTO.getCustomers());
        if(!ObjectUtils.isEmpty(adultEntries) && adultEntries.size()>0 ) {
            log.info("inside condition for adult ticket");
            TicketDetails adult =  processTicket(adultEntries,TicketType.ADULT);
            ticketDetails.add(adult);

        }


        List<CustomerDTO>seniors = filter(c -> c.getAge() >=configuration.getSeniorAge(),requestDTO.getCustomers());


        if(!ObjectUtils.isEmpty(seniors) && seniors.size()>0 ) {
            log.info("inside condition for senior ticket");
            TicketDetails senior =  processTicket(seniors,TicketType.SENIOR);
            ticketDetails.add(senior);


        }

        //condition for teenage

        List<CustomerDTO>teenAgeList = filter(c -> c.getAge() >configuration.getTeenAge() && c.getAge() <configuration.getAdultAge(),requestDTO.getCustomers());
        if(!ObjectUtils.isEmpty(teenAgeList) && teenAgeList.size()>0 ) {
            log.info("inside condition for teenage ticket");
            TicketDetails teen =  processTicket(teenAgeList,TicketType.TEEN);
            ticketDetails.add(teen);
        }

        List<CustomerDTO>children = filter(c -> c.getAge() <configuration.getTeenAge(),requestDTO.getCustomers());
        if(!ObjectUtils.isEmpty(children) && children.size()>0 ) {
            log.info("inside condition for children ticket");
            TicketDetails child =  processTicket(children,TicketType.CHILDREN);
            ticketDetails.add(child);

        }
        log.info("ticketDetails size {}",ticketDetails.size());
        Double sum = ticketDetails.stream()
                .mapToDouble(TicketDetails::getTotalCost)
                .sum();
        movieTransaction.setTicketDetails(ticketDetails);
        movieTransaction.setTotalCost(sum);
        return movieTransaction;
    }


    private TicketDetails processTicket(List<CustomerDTO> customerDTOS,TicketType ticketType){
        TicketDetails entity= new TicketDetails();
        Integer qty = customerDTOS.size();
        entity.setQuantity(qty);
        entity.setTicketType(ticketType.name());

        switch(ticketType) {
            case ADULT -> {
                entity.setTotalCost(qty*configuration.getAdultFare());
            }
            case TEEN -> {
                entity.setTotalCost(qty*configuration.getTeenFare());

            }
            case SENIOR -> {
                Double seniorFare = configuration.getAdultFare()-(configuration.getAdultFare()*(configuration.getSeniorDiscount()/100));
                entity.setTotalCost(seniorFare);

            }

            case CHILDREN -> {
                Double discountedFare = configuration.getChildrenFare()-(configuration.getChildrenFare()*(configuration.getChildrenDiscount()/100));
                if(customerDTOS.size()>=3)
                {
                    entity.setTotalCost(qty * discountedFare);
                }
                else {
                    entity.setTotalCost(qty * configuration.getChildrenFare());
                }

            }
        }
        return entity;
    }

    private List<CustomerDTO> filter(Predicate<CustomerDTO>predicate,List<CustomerDTO>customerDTOList)
    {
        return customerDTOList
                .stream()
                .filter(predicate).collect(Collectors.toList());
    }


}


