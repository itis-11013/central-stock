package ru.itis.stockmarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.stockmarket.models.Product;
import java.util.Optional;
import java.util.UUID;


public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsProductBySellerIdAndName(UUID id, String name);

}
