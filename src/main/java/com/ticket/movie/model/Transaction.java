package com.ticket.movie.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Getter
@Setter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer    transactionId;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private List<TicketDetail> ticketDetails;
    private Double totalCost;
}
