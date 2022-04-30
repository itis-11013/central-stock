package ru.itis.stockmarket.models;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

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
@Data
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bankGen")
    @SequenceGenerator(name = "bankGen", sequenceName = "bank_seq", allocationSize = 1)
    private Long id;
    private String name;
    private String address;

    @ManyToOne
    private Country country;
}
