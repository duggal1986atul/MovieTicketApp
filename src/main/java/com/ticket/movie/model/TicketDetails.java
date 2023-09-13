package com.ticket.movie.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketDetails {

    private Double totalCost;
    private Integer quantity;

    private String ticketType;

}
