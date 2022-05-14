package ru.itis.stockmarket.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.stockmarket.dtos.ProductRequestDto;
import ru.itis.stockmarket.models.db.Product;
import ru.itis.stockmarket.services.ProductService;

import java.util.List;

@RestController
@RequestMapping(path = "/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(path = "/list")
    ResponseEntity<List<Product>> getProductList(ProductRequestDto product) {
        return null;
    }

}
