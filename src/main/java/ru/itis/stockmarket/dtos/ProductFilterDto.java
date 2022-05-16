package ru.itis.stockmarket.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * Created by IntelliJ IDEA
 * Date: 16.05.2022
 * Time: 3:04 AM
 *
 * @author lordvidex
 * Name: Овамойо Олувадамилола Эванс
 * <p>
 * Desc:
 */

@Data
public class ProductFilterDto {
    private int count;
    private String country;
    private int page = 1;
}
