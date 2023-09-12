package com.ticket.movie.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class CustomerDTO {

    private String name;
    @NotNull
    private Integer age;
}
