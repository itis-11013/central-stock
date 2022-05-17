package ru.itis.stockmarket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.itis.stockmarket.models.Product;

import java.util.UUID;


public interface ProductRepository extends JpaRepository<Product, UUID>, JpaSpecificationExecutor<Product> {
    boolean existsProductBySeller_InnerIdAndName(UUID seller_id, String name);
}
