package ru.itis.stockmarket.models.db;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

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
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@DynamicUpdate
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bankGen")
    @SequenceGenerator(name = "bankGen", sequenceName = "bank_seq", allocationSize = 1)
    private Long id;

    private UUID innerId;
    private String name;
    private String address;

    private String url;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
}
