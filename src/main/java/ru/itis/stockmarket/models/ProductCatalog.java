package ru.itis.stockmarket.models;

import javax.persistence.*;
import java.util.List;

/**
 * Created by IntelliJ IDEA
 * Date: 30.04.2022
 * Time: 2:58 PM
 *
 * @author lordvidex
 * Name: Овамойо Олувадамилола Эванс
 * <p>
 * Desc:
 */

@Entity
@Table(name = "product_catalog")
public class ProductCatalog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private String name;

    @OneToMany(mappedBy = "catalog")
    private List<Product> products;
}
