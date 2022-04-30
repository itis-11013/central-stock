package ru.itis.stockmarket.models;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

/**
 * Created by IntelliJ IDEA
 * Date: 27.04.2022
 * Time: 11:59 AM
 *
 * @author lordvidex
 * Name: Овамойо Олувадамилола Эванс
 * <p>
 * Desc:
 */
@Entity
@Table(name = "bank")
@Getter
@Setter
@RequiredArgsConstructor
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bankGen")
    @SequenceGenerator(name = "bankGen", sequenceName = "bank_seq", allocationSize = 1)
    private Long id;

    private String name;
    private String address;

    private String url;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
}
