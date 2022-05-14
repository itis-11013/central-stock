package ru.itis.stockmarket.services;

import ru.itis.stockmarket.dtos.ProductRequestDto;
import ru.itis.stockmarket.models.Product;
import java.util.List;


public interface ProductService {
    List<Product> getProducts(ProductRequestDto productForm);
}
