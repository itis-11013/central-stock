package ru.itis.stockmarket.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.stockmarket.dtos.InnerIdResponseDto;

import javax.validation.Valid;

/**
 * Created by IntelliJ IDEA
 * Date: 17.05.2022
 * Time: 12:50 PM
 *
 * @author lordvidex
 * Name: Овамойо Олувадамилола Эванс
 * <p>
 * Desc:
 */
@RestController
@RequestMapping("/payment")
public class PaymentController {
    InnerIdResponseDto createPayment() {
        // TODO: complete controller impl.
        return InnerIdResponseDto
                .builder()
                .build();
    }
}
