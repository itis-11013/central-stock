package ru.itis.stockmarket.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.itis.stockmarket.dtos.BankRequestDto;
import ru.itis.stockmarket.dtos.BankResponseDto;
import ru.itis.stockmarket.exceptions.NotFoundException;
import ru.itis.stockmarket.mappers.BankMapper;
import ru.itis.stockmarket.models.Bank;
import ru.itis.stockmarket.models.Country;
import ru.itis.stockmarket.repositories.BankRepository;
import ru.itis.stockmarket.repositories.CountryRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static ru.itis.stockmarket.dtos.BankResponseDto.from;

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
@Transactional
public class BankServiceImpl implements BankService {

    private final BankRepository bankRepository;

    private final BankMapper mapper;

    private final CountryRepository countryRepository;

    BankServiceImpl(BankRepository bankRepository,
                    CountryRepository countryRepository,
                    BankMapper mapper
                    ) {
        this.bankRepository = bankRepository;
        this.countryRepository = countryRepository;
        this.mapper = mapper;
    }

    @Override
    public BankResponseDto createOrganization(BankRequestDto bankDto) {
        // get country instance or create if it doesn't exist
        Country country = this.countryRepository
                .findCountryByCode(bankDto.getCountry())
                .or(() -> {
                    Country c = new Country();
                    c.setCode(bankDto.getCountry().toLowerCase()); // lombok cannot set from superClass
                    this.countryRepository.save(c);
                    return Optional.of(c);
                }).get();

        Bank bank = Bank.builder()
                .name(bankDto.getName())
                .address(bankDto.getAddress())
                .country(country)
                .url(bankDto.getUrl())
//                .innerId(UUID.randomUUID())
                .build();
        this.bankRepository.save(bank);
        return from(bank);
    }

    @Override
    public BankResponseDto updateOrganization(UUID id, BankRequestDto bankDto) {
        Bank fetchedBank = _getBankWithId(id);
        this.mapper.updateBankFromDto(bankDto, fetchedBank);
        if (fetchedBank.getCountry() != null) {
            this.countryRepository.save(fetchedBank.getCountry());
        }
        // save
        return from(this.bankRepository.save(fetchedBank));
    }

    @Override
    public BankResponseDto getOrganizationWithId(UUID id) {
        return from(_getBankWithId(id));
    }

    private Bank _getBankWithId(UUID id) {
        return this.bankRepository
                .findByInnerId(id)
                .orElseThrow(() -> new NotFoundException(String.format("Bank with id %s not found", id))
                );
    }

    @Override
    public List<BankResponseDto> getAllOrganizations() {
        return from(this.bankRepository.findAll());
    }

    @Override
    public UUID deleteOrganizationWithId(UUID id) {
        _getBankWithId(id); // throws if bank does not exist
        try {
            this.bankRepository.deleteByInnerId(id);
            return id;
        } catch (Exception e) {
            return null;
        }
    }
}
