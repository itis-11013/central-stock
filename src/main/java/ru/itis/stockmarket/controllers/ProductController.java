package ru.itis.stockmarket.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.stockmarket.dtos.*;
import ru.itis.stockmarket.models.Product;
import ru.itis.stockmarket.services.ProductService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping("/product")
    ResponseEntity<InnerIdResponseDto> createProduct(@Valid @RequestBody ProductRequestDto product) {
        ProductResponseDto serviceProduct = productService.createProduct(product);

        InnerIdResponseDto controllerResponse = InnerIdResponseDto
                .builder()
                .status(Status.success)
                .description("Product created successfully")
                .innerid(serviceProduct.getInnerId())
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(controllerResponse);
    }

    @GetMapping("/productlist")
    GeneralMessage<PagedResponse<ProductResponseDto>> getProductList(@RequestParam String code,
                                                 Pageable pageable,
                                                 ProductFilterDto filter) {
       return new GeneralMessage<PagedResponse<ProductResponseDto>>()
               .toBuilder()
               .data(this.productService.getProducts(code, pageable, filter))
               .build();
    }
}
