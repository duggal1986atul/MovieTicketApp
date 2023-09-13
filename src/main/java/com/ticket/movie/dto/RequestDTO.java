package com.ticket.movie.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@Getter
public class RequestDTO {
    @NotNull
    private Integer transactionId;
    @NotEmpty
    private List<CustomerDTO> customers;
}
