package ru.itis.stockmarket.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.stockmarket.dtos.ContractRequestDto;
import ru.itis.stockmarket.dtos.GeneralMessage;

import javax.validation.Valid;

/**
 * Created by IntelliJ IDEA
 * Date: 17.05.2022
 * Time: 12:38 PM
 *
 * @author lordvidex
 * Name: Овамойо Олувадамилола Эванс
 * <p>
 * Desc:
 */
@RestController
@RequestMapping("/contract")
public class ContractController {
    public GeneralMessage<?> createContract(@Valid ContractRequestDto dto) {
        return null;
    }
}
