package ru.itis.stockmarket.services;

import ru.itis.stockmarket.dtos.ProductRequestDto;
import ru.itis.stockmarket.models.Product;
import java.util.List;


public interface ProductService<RequestDto, ResponseDto> {
    ResponseDto createProduct(RequestDto productDto);
    List<ResponseDto> getProducts(ProductRequestDto productForm);
}
