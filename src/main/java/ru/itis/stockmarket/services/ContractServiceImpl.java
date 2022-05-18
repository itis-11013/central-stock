package ru.itis.stockmarket.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.itis.stockmarket.dtos.ContractRequestDto;
import ru.itis.stockmarket.dtos.ContractResponseDto;
import ru.itis.stockmarket.exceptions.CustomServerErrorException;
import ru.itis.stockmarket.exceptions.NotFoundException;
import ru.itis.stockmarket.mappers.ContractMapper;
import ru.itis.stockmarket.models.Contract;
import ru.itis.stockmarket.models.Organization;
import ru.itis.stockmarket.models.Product;
import ru.itis.stockmarket.repositories.ContractRepository;
import ru.itis.stockmarket.repositories.OrganizationRepository;
import ru.itis.stockmarket.repositories.ProductRepository;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA
 * Date: 17.05.2022
 * Time: 7:30 PM
 *
 * @author lordvidex
 * Name: Овамойо Олувадамилола Эванс
 * <p>
 * Desc:
 */
@Service
@RequiredArgsConstructor
@Transactional
public class ContractServiceImpl implements ContractService {
    private final ContractRepository contractRepository;
    private final ProductRepository productRepository;
    private final OrganizationRepository organizationRepository;
    private final ContractMapper contractMapper;

    @Override
    public ContractResponseDto findContractById(UUID id) {
        Contract contract = this.contractRepository
                .softFindById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Contract with id %s not found", id)));
        return this.contractMapper.toDto(contract);
    }

    @Override
    public ContractResponseDto createContract(ContractRequestDto dto) {
        Product product = this.productRepository.findById(dto.getProductid())
                .orElseThrow(() -> new NotFoundException(String.format("Product with id %s not found", dto.getProductid())));

        Organization buyer = this.organizationRepository.findById(dto.getBuyerid())
                .orElseThrow(() -> new NotFoundException(String.format("Organization with id %s is not registered on the stock market", dto.getBuyerid())));

        /* prevent organization from creating a contract with themselves */
        if (product.getSeller().getInnerId().equals(buyer.getInnerId())) {
            throw new CustomServerErrorException(HttpStatus.FORBIDDEN, "Organization cannot buy a product from itself");
        }

        /* freezes the amount buyer wants to buy or throw if he wants to buy more than seller has */
        if (product.getCount() < dto.getCount()) {
            throw new CustomServerErrorException(HttpStatus.BAD_REQUEST, String.format("Cannot buy more than %f from this seller", product.getCount()));
        } else {
            this.productRepository.freezeCountById(dto.getCount(), product.getInnerId());
        }
        Contract contract = Contract.builder()
                .buyer(buyer)
                .count(dto.getCount())
                .product(product)
                .build();
        return this.contractMapper.toDto(this.contractRepository.save(contract));
    }

    @Override
    public void deleteContractWithId(UUID id) {
        // throws if not found or already soft deleted
        ContractResponseDto contract = this.findContractById(id);

        // unfreeze the count on the product
        Optional<Product> product =
        this.productRepository
                .findById(contract.getProductId());
        product.ifPresent(value -> this.productRepository.unfreezeCountById(contract.getCount(), value.getInnerId()));

        // soft delete the contract
        this.contractRepository.softDeleteById(contract.getContractId());
    }

}
