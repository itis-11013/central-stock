package ru.itis.stockmarket.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.stockmarket.dtos.*;
import ru.itis.stockmarket.models.Product;
import ru.itis.stockmarket.services.ProductService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProductController {

    private final ProductService<ProductRequestDto, ProductResponseDto> productService;

    public ProductController(ProductService<ProductRequestDto, ProductResponseDto> productService) {
        this.productService = productService;
    }


    @PostMapping("/product")
    ResponseEntity<InnerIdResponseDto> createProduct(@Valid @RequestBody ProductRequestDto product) {
        ProductResponseDto serviceProduct = productService.createProduct(product);
        InnerIdResponseDto controllerResponse;

        controllerResponse = InnerIdResponseDto
                .builder()
                .innerid(serviceProduct.getInnerId())
                .status(Status.success)
                .description("Product created successfully")
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(controllerResponse);
    }

    @PostMapping(path = "/productlist")
    ResponseEntity<List<Product>> getProductList(ProductRequestDto product) {
        return null;
    }

}
