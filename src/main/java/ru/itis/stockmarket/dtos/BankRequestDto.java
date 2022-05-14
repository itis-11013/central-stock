package ru.itis.stockmarket.dtos;

import lombok.Data;

import javax.validation.constraints.*;

/**
 * Created by IntelliJ IDEA
 * Date: 30.04.2022
 * Time: 5:40 PM
 *
 * @author lordvidex
 * Name: Овамойо Олувадамилола Эванс
 * <p>
 * Desc:
 */
@Data
public class BankRequestDto {
    @NotEmpty
    private String name;
    @NotEmpty
    private String address;
    @NotEmpty
    @Size(min = 2, max = 2, message = "Length of country should be 2, country code as specified by ISO-3166-1")
    private String country;
    @NotEmpty
    private String url;
}
