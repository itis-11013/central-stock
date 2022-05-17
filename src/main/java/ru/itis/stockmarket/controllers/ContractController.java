package ru.itis.stockmarket.controllers;

import org.springframework.web.bind.annotation.*;
import ru.itis.stockmarket.dtos.ContractRequestDto;
import ru.itis.stockmarket.dtos.ContractResponseDto;
import ru.itis.stockmarket.dtos.GeneralMessage;
import ru.itis.stockmarket.dtos.InnerIdResponseDto;
import ru.itis.stockmarket.services.ContractService;

import javax.validation.Valid;
import java.util.UUID;

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
    private final ContractService contractService;

    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }

    @PostMapping
    public GeneralMessage<ContractResponseDto> createContract(@Valid @RequestBody ContractRequestDto dto) {
        return new GeneralMessage<ContractResponseDto>().toBuilder()
                .data(this.contractService.createContract(dto))
                .description("Contract successfully created")
                .build();
    }

    @GetMapping("/{id}")
    public GeneralMessage<ContractResponseDto> findContractById(@PathVariable UUID id) {
        return new GeneralMessage<ContractResponseDto>().toBuilder()
                .data(this.contractService.findContractById(id))
                .build();
    }

    @DeleteMapping("/{id}")
    public InnerIdResponseDto deleteContractWithId(@PathVariable UUID id) {
        this.contractService.deleteContractWithId(id);
        return InnerIdResponseDto.builder()
                .innerid(id)
                .description("Contract successfully deleted")
                .build();
    }
}
