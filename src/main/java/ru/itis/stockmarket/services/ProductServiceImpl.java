package ru.itis.stockmarket.services;

import org.springframework.stereotype.Service;
import ru.itis.stockmarket.dtos.ProductRequestDto;
import ru.itis.stockmarket.models.db.Product;
import ru.itis.stockmarket.repositories.ProductRepository;
import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getProducts(ProductRequestDto productForm) {
        return null;
    }
}
