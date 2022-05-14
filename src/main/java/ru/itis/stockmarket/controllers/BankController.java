package ru.itis.stockmarket.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.stockmarket.dtos.BankRequestDto;
import ru.itis.stockmarket.dtos.BankResponseDto;
import ru.itis.stockmarket.dtos.GeneralMessage;
import ru.itis.stockmarket.dtos.Status;
import ru.itis.stockmarket.services.BankService;

import javax.validation.Valid;
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

    @PostMapping
    ResponseEntity<BankResponseDto> createBank(@Valid @RequestBody BankRequestDto bank) {
        return ResponseEntity.ok(this.bankService.createBank(bank));
    }

    @GetMapping
    ResponseEntity<List<BankResponseDto>> getAllBanks() {
        return ResponseEntity.ok(this.bankService.getAllBanks());
    }

    @GetMapping("/{id}")
    ResponseEntity<BankResponseDto> getBankWithId(@PathVariable Long id) {
        return ResponseEntity.ok(this.bankService.getBankWithId(id));
    }

    @DeleteMapping("/{id}")
    GeneralMessage deleteBankWithId(@PathVariable Long id) {
        try {
            this.bankService.deleteBankWithId(id);
            return GeneralMessage.builder()
                    .description("Successfully deleted bank")
                    .data("OK")
                    .status(Status.success)
                    .build();
        } catch(Exception e) {
            return GeneralMessage.builder()
                    .description("An error occured while deleting bank")
                    .data("Error")
                    .status(Status.failure)
                    .build();
        }
    }

    @PatchMapping("/{id}")
    ResponseEntity<BankResponseDto> updateBankWithId(@PathVariable("id") Long id,
                                                     @RequestBody BankRequestDto bank) {
        return ResponseEntity.ok(this.bankService.updateBank(id, bank));
    }
}
