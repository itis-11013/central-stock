package ru.itis.stockmarket.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.itis.stockmarket.dtos.InnerIdResponseDto;
import ru.itis.stockmarket.exceptions.BadRequestException;
import ru.itis.stockmarket.exceptions.CustomServerErrorException;
import ru.itis.stockmarket.exceptions.NotFoundException;
import ru.itis.stockmarket.models.*;
import ru.itis.stockmarket.repositories.BankRepository;
import ru.itis.stockmarket.repositories.ContractRepository;
import ru.itis.stockmarket.repositories.ProductRepository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final ContractRepository contractRepository;
    private final BankRepository bankRepository;
    private final ProductRepository productRepository;

    @Override
    public InnerIdResponseDto makePaymentForContract(UUID contractId) {
        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new NotFoundException(String.format("Contract with id %s not found", contractId)));

        /* check if payment has been made  - idempotency check */
        if (contract.getPaymentDate() != null) {
            throw new BadRequestException("Payment has already been made for this contract");
        }

        // product
        Product product = contract.getProduct();
        // Organizations
        Organization buyerOrg = contract.getBuyer();
        Organization sellerOrg = product.getSeller();
        // Countries
        Country sellerCountry = sellerOrg.getCountry();
        Country buyerCountry = buyerOrg.getCountry();

        if (buyerCountry != sellerCountry) {
            // banks
            Bank buyerBank = bankRepository.findBankByCountry_Code(buyerCountry.getCode())
                    .orElseThrow(() -> new NotFoundException(String.format("Bank with country code %s not found", buyerCountry.getCode())));

            Bank sellerBank = bankRepository.findBankByCountry_Code(sellerCountry.getCode())
                    .orElseThrow(() -> new NotFoundException(String.format("Bank with country code %s not found", buyerCountry.getCode())));

            // Accounts for the national banks
            Account buyerAccount = _getAccountByCountry(sellerCountry, buyerBank);
            Account sellerAccount = _getAccountByCountry(sellerCountry, sellerBank);

            // Balances for the national banks
            double buyerBalance = buyerAccount.getBalance();
            double sellerBalance = sellerAccount.getBalance();

            // Contract amount
            double contractAmount = product.getPrice() * contract.getCount();

            // check if the money is enough
            if (buyerBalance < contractAmount) {
                throw new BadRequestException(
                        String.format("'%s' Balance for Bank of '%s' not enough!!", sellerCountry.getCode(), buyerCountry.getCode()));
            }
            // money transfer
            sellerAccount.setBalance(sellerBalance + contractAmount);
            buyerAccount.setBalance(buyerBalance - contractAmount);
            bankRepository.save(buyerBank);
            bankRepository.save(sellerBank);
            // TODO: notify bank about money transfer and update delivery date
        }
        // product count
        product.setFrozenCount(product.getFrozenCount() - contract.getCount());
        productRepository.save(product);
        // set payment date
        contract.setPaymentDate(new Date());
        contractRepository.save(contract);
        return InnerIdResponseDto
                .builder()
                .innerid(contract.getInnerId())
                .build();
    }

    private Account _getAccountByCountry(Country country, Bank buyerBanks) {
        for (Account a : buyerBanks.getAccounts()) {
            if (a.getCountry() == country)
                return a;
        }
        throw new NotFoundException(String.format("Can not fund account for country %s ", country.getName()));
    }
}
