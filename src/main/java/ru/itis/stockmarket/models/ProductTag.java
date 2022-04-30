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
@Table(name = "product_tag")
public class ProductTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    @ManyToMany()
    @JoinTable(
            name = "product_tag_products", joinColumns = {@JoinColumn(name = "tag_id")},
            inverseJoinColumns = {@JoinColumn(name = "product_id")}
    )
    private List<Product> products;
}
