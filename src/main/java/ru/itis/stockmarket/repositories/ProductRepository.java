package ru.itis.stockmarket.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.stockmarket.models.Product;
import java.util.Optional;
import java.util.UUID;


public interface ProductRepository extends JpaRepository<Product, UUID> {
    boolean existsProductBySellerIdAndName(Long seller_id, String name);
    Pageable<Product> findAllByCatalog_CodeOrderBy

}
