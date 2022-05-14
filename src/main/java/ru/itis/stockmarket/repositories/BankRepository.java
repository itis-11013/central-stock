package ru.itis.stockmarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.stockmarket.models.db.Bank;

/**
 * Created by IntelliJ IDEA
 * Date: 27.04.2022
 * Time: 11:58 AM
 *
 * @author lordvidex
 * Name: Овамойо Олувадамилола Эванс
 * <p>
 * Desc:
 */
public interface BankRepository extends JpaRepository<Bank, Long> {
}
