package ru.itis.stockmarket.models;

import lombok.*;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA
 * Date: 18.05.2022
 * Time: 12:59 PM
 *
 * @author lordvidex
 * Name: Овамойо Олувадамилола Эванс
 * <p>
 * Desc:
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "bank_account")
public class Account {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Country country;

    private double balance;

    @ManyToOne
    private Bank bank;

}
