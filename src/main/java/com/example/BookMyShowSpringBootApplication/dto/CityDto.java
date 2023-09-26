package com.example.BookMyShowSpringBootApplication.dto;

import com.example.BookMyShowSpringBootApplication.entity.City;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CityDto {

    @NotBlank
    private String name;

    @NotBlank
    private String state;

    @NotBlank
    private String pinCode;

    public CityDto(City city) {
        this.name = city.getName();
        this.state = city.getState();
        this.pinCode = city.getPinCode();
    }

}
