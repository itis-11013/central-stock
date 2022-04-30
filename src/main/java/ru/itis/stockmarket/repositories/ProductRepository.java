package ru.itis.stockmarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.stockmarket.models.ProductTag;


public interface ProductRepository extends JpaRepository<ProductTag, Long> {

}
