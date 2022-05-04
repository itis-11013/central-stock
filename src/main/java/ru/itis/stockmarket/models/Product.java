package ru.itis.stockmarket.models;

import javax.persistence.*;
import java.util.List;

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
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private double price;

    private double count;

    @ManyToOne
    @JoinColumn(name = "unit_id")
    private Unit unit;

    @ManyToMany(mappedBy = "products")
    private List<ProductTag> tags;
}
