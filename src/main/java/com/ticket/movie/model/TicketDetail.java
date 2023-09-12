package com.ticket.movie.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class TicketDetail {
    private BigDecimal cost;
    @Id
    private Integer transactionId;

    private Integer quantity;

    @Enumerated(EnumType.ORDINAL)
    private TicketType ticketType;


    @ManyToOne(fetch = FetchType.LAZY)
    private Transaction transaction;
}
