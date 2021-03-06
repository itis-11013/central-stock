package ru.itis.stockmarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.itis.stockmarket.models.Contract;

import java.util.Optional;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA
 * Date: 17.05.2022
 * Time: 6:35 PM
 *
 * @author lordvidex
 * Name: Овамойо Олувадамилола Эванс
 * <p>
 * Desc:
 */
public interface ContractRepository extends JpaRepository<Contract, UUID> {
    @Modifying
    @Query("UPDATE Contract c SET c.deleted = true WHERE c.innerId = ?1")
    void softDeleteById(UUID id);

    @Query("SELECT c FROM Contract c WHERE c.deleted = false AND c.innerId = :uuid")
    Optional<Contract> softFindById(UUID uuid);
}
