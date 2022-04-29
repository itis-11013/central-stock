package ru.itis.stockmarket.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.stockmarket.dtos.GeneralMessage;
import ru.itis.stockmarket.dtos.Status;
import ru.itis.stockmarket.models.Bank;
import ru.itis.stockmarket.services.BankService;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 * Date: 27.04.2022
 * Time: 10:51 AM
 *
 * @author lordvidex
 * Name: Овамойо Олувадамилола Эванс
 * <p>
 * Desc:
 */


@RestController
@RequestMapping(path = "/bank")
public class BankController {
    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @PostMapping()
    ResponseEntity<Bank> createBank(@RequestBody Bank bank) {
        return ResponseEntity.ok(this.bankService.createBank(bank));
    }

    @GetMapping()
    ResponseEntity<List<Bank>> getAllBanks() {
        return ResponseEntity.ok(this.bankService.getAllBanks());
    }
}
