package ru.itis.stockmarket.services;

import ru.itis.stockmarket.dtos.ContractRequestDto;
import ru.itis.stockmarket.dtos.ContractResponseDto;

import java.util.UUID;

/**
 * Created by IntelliJ IDEA
 * Date: 17.05.2022
 * Time: 6:55 PM
 *
 * @author lordvidex
 * Name: Овамойо Олувадамилола Эванс
 * <p>
 * Desc:
 */
public interface ContractService {
    ContractResponseDto findContractById(UUID id);
    ContractResponseDto createContract(ContractRequestDto dto);
    void deleteContractWithId(UUID id);
}
