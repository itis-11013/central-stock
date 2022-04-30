package ru.itis.stockmarket.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ProductRequestDto {
   private String code;
   private String count;
}
