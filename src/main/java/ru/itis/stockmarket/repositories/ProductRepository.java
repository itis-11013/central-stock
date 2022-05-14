package ru.itis.stockmarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.stockmarket.models.Product;


public interface ProductRepository extends JpaRepository<Product, Long> {

}
