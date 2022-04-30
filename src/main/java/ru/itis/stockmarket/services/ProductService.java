package ru.itis.stockmarket.services;

import ru.itis.stockmarket.dtos.ProductForm;
import ru.itis.stockmarket.models.Product;
import java.util.List;


public interface ProductService {
    List<Product> getProducts(ProductForm productForm);
}
