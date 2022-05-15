package ru.itis.stockmarket.models;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA
 * Date: 30.04.2022
 * Time: 2:14 PM
 *
 * @author lordvidex
 * Name: Овамойо Олувадамилола Эванс
 * <p>
 * Desc:
 */

@Entity
@Table(name = "product")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter @Setter
@Inheritance(strategy = InheritanceType.JOINED)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID sellerId;
    private String name;
    private double price;

    private double count;

    @ManyToOne
    @JoinColumn(name = "unit_id")
    private Unit unit;

    @ManyToOne
    @JoinColumn(name = "catalog_id")
    private ProductCatalog catalog;
}
