package ru.itis.stockmarket.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.groups.Default;
import java.util.UUID;


@Data
@NoArgsConstructor
public class ProductRequestDto {
    @NotEmpty(groups = {Default.class, OnCreate.class})
    private String code;
    @NotEmpty(groups = {Default.class, OnCreate.class})
    private String name;
    @JsonProperty(value = "sellerid")
    private UUID sellerId;
    @DecimalMin(value = "1.0" ,groups = {Default.class, OnCreate.class})
    private double count;
    @NotEmpty(groups = {Default.class, OnCreate.class})
    private String unit;
    @DecimalMin(value = "1.0" , groups = {Default.class, OnCreate.class})
    private double price;
}
