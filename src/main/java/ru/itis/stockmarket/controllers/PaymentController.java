package ru.itis.stockmarket.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.stockmarket.dtos.GeneralMessage;
import ru.itis.stockmarket.dtos.InnerIdResponseDto;
import ru.itis.stockmarket.dtos.Status;
import ru.itis.stockmarket.services.PaymentService;

import javax.validation.Valid;
import java.util.UUID;

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

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/{id}")
    ResponseEntity<InnerIdResponseDto> createPayment(@PathVariable UUID id) {
        InnerIdResponseDto serviceResponse = paymentService.payment(id);

        InnerIdResponseDto controllerResponse = InnerIdResponseDto
                .builder()
                .innerid(serviceResponse.getInnerid())
                .status(Status.success)
                .description("Organization created successfully")
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(controllerResponse);
    }
}
