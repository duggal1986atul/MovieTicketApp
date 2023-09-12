package com.ticket.movie.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class TicketDetails {
    private Double cost;
    @Id
    private Integer transactionId;

    private Integer quantity;

    @Enumerated(EnumType.ORDINAL)
    private TicketType ticketType;


    @ManyToOne(fetch = FetchType.LAZY)
    private MovieTransaction movieTransaction;
}
