package ru.itis.stockmarket.services;

import ru.itis.stockmarket.dtos.BankDto;
import ru.itis.stockmarket.models.Bank;

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
    Bank createBank(Bank bank);

    List<Bank> getAllBanks();
}
