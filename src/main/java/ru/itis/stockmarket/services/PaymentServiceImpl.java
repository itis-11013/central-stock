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
        // seller Country
        Country sellerCountry = contract.getProduct().getSeller().getCountry();
        // banks
        Bank buyerBank = contract.getBuyer().getBank();
        Bank sellerBank = contract.getProduct().getSeller().getBank();
        // Accounts
        Account buyerAccount = _getAccountByCountry(sellerCountry, buyerBank);
        Account sellerAccount = _getAccountByCountry(sellerCountry, sellerBank);
        // Balances
        double buyerBalance = buyerAccount.getBalance();
        double sellerBalance = sellerAccount.getBalance();
        // Product Price
        double productPrice = contract.getProduct().getPrice();
        // check if the money is enough
        if (buyerBalance < productPrice) {
            throw new CustomServerErrorException(HttpStatus.BAD_REQUEST,
                    String.format("Balance %s not enough!!", buyerBalance));
        }
        // check if count
        if (contract.getProduct().getCount() < contract.getCount()){
            throw new CustomServerErrorException(HttpStatus.BAD_REQUEST,
                    "Count product not enough!!");
        }
        // money transfer
        sellerAccount.setBalance(sellerBalance + productPrice);
        buyerAccount.setBalance(buyerBalance - productPrice);
        bankRepository.save(buyerBank);
        bankRepository.save(sellerBank);
        // product count
        Product product = contract.getProduct();
        product.setCount(product.getCount() - contract.getCount());
        productRepository.save(product);
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
