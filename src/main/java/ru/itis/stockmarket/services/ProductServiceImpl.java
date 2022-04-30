package ru.itis.stockmarket.services;

import org.springframework.stereotype.Service;
import ru.itis.stockmarket.dtos.ProductForm;
import ru.itis.stockmarket.models.Product;
import ru.itis.stockmarket.repositories.ProductRepository;
import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getProducts(ProductForm productForm) {
        return null;
    }
}
