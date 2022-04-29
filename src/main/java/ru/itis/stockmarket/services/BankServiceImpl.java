package ru.itis.stockmarket.services;

import org.springframework.stereotype.Service;
import ru.itis.stockmarket.dtos.BankDto;
import ru.itis.stockmarket.models.Bank;
import ru.itis.stockmarket.repositories.BankRepository;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 * Date: 29.04.2022
 * Time: 10:02 PM
 *
 * @author lordvidex
 * Name: Овамойо Олувадамилола Эванс
 * <p>
 * Desc:
 */
@Service
public class BankServiceImpl implements BankService {

    private BankRepository bankRepository;

    BankServiceImpl(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }
    @Override
    public Bank createBank(Bank bank) {
        return this.bankRepository.save(bank);
    }

    @Override
    public List<Bank> getAllBanks() {
        return this.bankRepository.findAll();
    }
}
