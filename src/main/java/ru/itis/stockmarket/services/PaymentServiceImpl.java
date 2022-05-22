package ru.itis.stockmarket.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.itis.stockmarket.dtos.InnerIdResponseDto;
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
    public InnerIdResponseDto payment(UUID contractId) {
        Contract contract = contractRepository.findById(contractId)
                .orElseThrow(() -> new NotFoundException(String.format("Contract with id %s not found", contractId)));
        if (contract.getBuyer().getCountry() == contract.getProduct().getSeller().getCountry()) {
            // from same country
            throw new CustomServerErrorException(HttpStatus.BAD_REQUEST,
                    "Buyer and seller from same country!");
        }
        // product
        Product product = contract.getProduct();
        // seller Country
        Country sellerCountry = contract.getProduct().getSeller().getCountry();
        // Organizations
        Organization buyerOrg = contract.getBuyer();
        Organization sellerOrg = product.getSeller();
        // banks
        Bank buyerBank = bankRepository.findBankByCountry_Code(buyerOrg.getCountry().getCode());
        Bank sellerBank = bankRepository.findBankByCountry_Code(sellerOrg.getCountry().getCode());
        // Accounts
        Account buyerAccount = _getAccountByCountry(sellerCountry, buyerBank);
        Account sellerAccount = _getAccountByCountry(sellerCountry, sellerBank);
        // Balances
        double buyerBalance = buyerAccount.getBalance();
        double sellerBalance = sellerAccount.getBalance();
        // Product Price
        double productPrice = product.getPrice() * product.getCount();
        // check if the money is enough
        if (buyerBalance < productPrice) {
            throw new CustomServerErrorException(HttpStatus.BAD_REQUEST,
                    String.format("Balance %s not enough!!", buyerBalance));
        }
        // money transfer
        sellerAccount.setBalance(sellerBalance + productPrice);
        buyerAccount.setBalance(buyerBalance - productPrice);
        bankRepository.save(buyerBank);
        bankRepository.save(sellerBank);
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
