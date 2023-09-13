package com.ticket.movie.configuration;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;



@ConfigurationProperties("movie")
@Getter
@Setter
public class MovieConfiguration {
    private Double teenFare;
    private Double adultFare;
    private Double childrenFare;
    private Integer seniorAge;
    private Integer teenAge;
    private Double seniorDiscount;
    private Integer adultAge;
    private Double childrenDiscount;
  }
