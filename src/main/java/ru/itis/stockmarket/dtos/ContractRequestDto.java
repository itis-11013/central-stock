package ru.itis.stockmarket.dtos;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA
 * Date: 17.05.2022
 * Time: 12:44 PM
 *
 * @author lordvidex
 * Name: Овамойо Олувадамилола Эванс
 * <p>
 * Desc:
 */
public class ContractRequestDto {
    @NotEmpty
    private UUID productid;
    @DecimalMin("0")
    private double count;
    @NotEmpty
    private UUID buyerid;

}
