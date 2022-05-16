package ru.itis.stockmarket.services;

import org.springframework.data.domain.Page;
import ru.itis.stockmarket.dtos.ProductFilterDto;
import ru.itis.stockmarket.dtos.ProductRequestDto;
import ru.itis.stockmarket.dtos.ProductResponseDto;


public interface ProductService {
    ProductResponseDto createProduct(ProductRequestDto productDto);
    Page<ProductResponseDto> getProduct(String catalogCode, ProductFilterDto query);
}
