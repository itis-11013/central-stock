package ru.itis.stockmarket.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA
 * Date: 17.05.2022
 * Time: 6:57 PM
 *
 * @author lordvidex
 * Name: Овамойо Олувадамилола Эванс
 * <p>
 * Desc:
 */
@Data
public class ContractResponseDto {
    @JsonProperty("contractid")
    private UUID contractId;
    @JsonProperty("productid")
    private UUID productId;
    private double count;
    private OrganizationResponseDto buyer;
    private LocalDateTime createdAt;
}
