package ru.itis.stockmarket.services;

import org.springframework.stereotype.Service;
import ru.itis.stockmarket.dtos.ProductRequestDto;
import ru.itis.stockmarket.dtos.ProductResponseDto;
import ru.itis.stockmarket.exceptions.NotFoundException;
import ru.itis.stockmarket.models.Product;
import ru.itis.stockmarket.models.ProductCatalog;
import ru.itis.stockmarket.repositories.OrganizationRepository;
import ru.itis.stockmarket.repositories.ProductCatalogRepository;
import ru.itis.stockmarket.repositories.ProductRepository;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class ProductServiceImpl implements ProductService<ProductRequestDto, ProductResponseDto> {

    private final ProductRepository productRepository;
    private final ProductCatalogRepository catalogRepository;
    private final OrganizationRepository organizationRepository;

    ProductServiceImpl(ProductRepository productRepository, ProductCatalogRepository catalogRepository, OrganizationRepository organizationRepository) {
        this.productRepository = productRepository;
        this.catalogRepository = catalogRepository;
        this.organizationRepository = organizationRepository;
    }

    @Override
    public ProductResponseDto createProduct(ProductRequestDto productDto) {
        ProductCatalog productCat = catalogRepository
                .findProductCatalogByCode(productDto.getCode())
                .orElseThrow(() ->
                        new NotFoundException(String.format("Product Catalog with code %s not found", productDto.getCode()))
                );

        boolean exists = productRepository.existsProductBySellerIdAndName(productDto.getSellerId(), productDto.getName());

        if (exists) {
            throw new NotFoundException(String
                    .format("Product with seller id %s and name %s already exists",
                            productDto.getSellerId(), productDto.getName()));
        }

        organizationRepository.findByInnerId(productDto.getSellerId())
                .orElseThrow(() ->
                        new NotFoundException(String.format("Can not fund organization with id %s", productDto.getSellerId()))
                );

        productRepository.save(Product.builder()
                .catalog(productCat)
                .name(productDto.getName())
                .build()
        );

        return ProductResponseDto.builder()
                .innerId(productDto.getSellerId()).build();
    }

    @Override
    public List<ProductResponseDto> getProducts(ProductRequestDto productForm) {
        return null;
    }
}
