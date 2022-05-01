package ru.itis.stockmarket.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.stockmarket.models.Bank;
import ru.itis.stockmarket.models.Country;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA
 * Date: 29.04.2022
 * Time: 10:01 PM
 *
 * @author lordvidex
 * Name: Овамойо Олувадамилола Эванс
 * <p>
 * Desc:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankResponseDto {
    private Long id;
    private String name;
    private String address;
    private String url;
    @JsonProperty(value = "country_code")
    private String countryCode;

    public static BankResponseDto from(Bank bank) {
        return BankResponseDto.builder()
                .id(bank.getId())
                .name(bank.getName())
                .address(bank.getAddress())
                .url(bank.getUrl())
                .countryCode(
                        Optional.ofNullable(bank.getCountry())
                                .map(Country::getCode)
                                .orElse(null)
                )
                .build();
    }

    public static List<BankResponseDto> from(List<Bank> banks) {
        return banks
                .stream()
                .map(BankResponseDto::from)
                .collect(Collectors.toList());
    }
}
