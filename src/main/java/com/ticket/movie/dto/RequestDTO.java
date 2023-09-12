package com.ticket.movie.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Getter
public class RequestDTO {
    private Long id;
    private List<CustomerDTO> customers;
}
