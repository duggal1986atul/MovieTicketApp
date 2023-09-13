package com.ticket.movie.configuration;
import org.springframework.boot.context.properties.ConfigurationProperties;



@ConfigurationProperties("movie")
public class MovieConfiguration {
    private Double teenFare;
    private Double adultFare;
    private Double childrenFare;
    private Integer seniorAge;
    private Integer teenAge;
    private Double seniorDiscount;
    private Integer adultAge;
    private Double childrenDiscount;

    public Double getTeenFare() {
        return teenFare;
    }

    public Double getAdultFare() {
        return adultFare;
    }

    public Double getChildrenFare() {
        return childrenFare;
    }

    public Integer getSeniorAge() {
        return seniorAge;
    }

    public Integer getTeenAge() {
        return teenAge;
    }

    public Double getSeniorDiscount() {
        return seniorDiscount;
    }

    public Integer getAdultAge() {
        return adultAge;
    }

    public Double getChildrenDiscount() {
        return childrenDiscount;
    }

    public void setTeenFare(Double teenFare) {
        this.teenFare = teenFare;
    }

    public void setAdultFare(Double adultFare) {
        this.adultFare = adultFare;
    }

    public void setChildrenFare(Double childrenFare) {
        this.childrenFare = childrenFare;
    }

    public void setSeniorAge(Integer seniorAge) {
        this.seniorAge = seniorAge;
    }

    public void setTeenAge(Integer teenAge) {
        this.teenAge = teenAge;
    }

    public void setSeniorDiscount(Double seniorDiscount) {
        this.seniorDiscount = seniorDiscount;
    }

    public void setAdultAge(Integer adultAge) {
        this.adultAge = adultAge;
    }

    public void setChildrenDiscount(Double childrenDiscount) {
        this.childrenDiscount = childrenDiscount;
    }
}
