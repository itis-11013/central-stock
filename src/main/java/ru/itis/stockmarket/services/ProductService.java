package ru.itis.stockmarket.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.itis.stockmarket.dtos.PagedResponse;
import ru.itis.stockmarket.dtos.ProductFilterDto;
import ru.itis.stockmarket.dtos.ProductRequestDto;
import ru.itis.stockmarket.dtos.ProductResponseDto;
import ru.itis.stockmarket.models.Product;


public interface ProductService {
    ProductResponseDto createProduct(ProductRequestDto productDto);
    PagedResponse<ProductResponseDto> getProducts(ProductFilterDto dto);
}
