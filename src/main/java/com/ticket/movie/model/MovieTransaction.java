package com.ticket.movie.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "MovieCosting")
public class MovieTransaction {


    @Id
    private Integer transactionId;
    private List<TicketDetails> ticketDetails = new ArrayList<>();
    private Double totalCost;
}
