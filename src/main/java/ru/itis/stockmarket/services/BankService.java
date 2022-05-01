package ru.itis.stockmarket.services;

import ru.itis.stockmarket.dtos.BankRequestDto;
import ru.itis.stockmarket.dtos.BankResponseDto;
import java.util.List;

/**
 * Created by IntelliJ IDEA
 * Date: 27.04.2022
 * Time: 10:59 AM
 *
 * @author lordvidex
 * Name: Овамойо Олувадамилола Эванс
 * <p>
 * Desc:
 */

public interface BankService {
    BankResponseDto createBank(BankRequestDto bank);

    BankResponseDto updateBank(Long id, BankRequestDto bank);

    BankResponseDto getBankWithId(Long id);

    List<BankResponseDto> getAllBanks();

    void deleteBankWithId(Long id);

}
