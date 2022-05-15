package ru.itis.stockmarket.services;

import org.springframework.stereotype.Service;
import ru.itis.stockmarket.dtos.ProductRequestDto;
import ru.itis.stockmarket.dtos.ProductResponseDto;
import ru.itis.stockmarket.exceptions.AlreadyExistsException;
import ru.itis.stockmarket.exceptions.NotFoundException;
import ru.itis.stockmarket.mappers.ProductMapper;
import ru.itis.stockmarket.models.Product;
import ru.itis.stockmarket.models.ProductCatalog;
import ru.itis.stockmarket.models.Unit;
import ru.itis.stockmarket.repositories.OrganizationRepository;
import ru.itis.stockmarket.repositories.ProductCatalogRepository;
import ru.itis.stockmarket.repositories.ProductRepository;
import ru.itis.stockmarket.repositories.UnitRepository;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class ProductServiceImpl implements ProductService<ProductRequestDto, ProductResponseDto> {

    private final ProductRepository productRepository;
    private final ProductCatalogRepository catalogRepository;
    private final OrganizationRepository organizationRepository;
    private final UnitRepository unitRepository;
    private final ProductMapper productMapper;

    ProductServiceImpl(ProductRepository productRepository, ProductCatalogRepository catalogRepository, OrganizationRepository organizationRepository, UnitRepository unitRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.catalogRepository = catalogRepository;
        this.organizationRepository = organizationRepository;
        this.unitRepository = unitRepository;
        this.productMapper = productMapper;
    }

    @Override
    public ProductResponseDto createProduct(ProductRequestDto productDto) {
        //check product catalog
        ProductCatalog productCat = catalogRepository
                .findProductCatalogByCode(productDto.getCode())
                .orElseThrow(() ->
                        new NotFoundException(String.format("Product Catalog with code %s not found", productDto.getCode()))
                );
        //check if product exist!
        boolean exists = productRepository.existsProductBySellerIdAndName(productDto.getSellerId(), productDto.getName());
        if (exists) {
            throw new AlreadyExistsException(String
                    .format("Product with seller id %s and name %s already exists",
                            productDto.getSellerId(), productDto.getName()));
        }
        //check if organization exist!
        organizationRepository.findByInnerId(productDto.getSellerId())
                .orElseThrow(() ->
                        new NotFoundException(String.format("Can not fund organization with id %s", productDto.getSellerId()))
                );
        //get unit
        Unit unit = unitRepository
                .findUnitByCode(productDto.getUnit())
                .orElseThrow(() ->
                        new NotFoundException(String.format("Unit with code %s not found", productDto.getUnit()))
                );
        // save the product data
        Product product = Product.builder()
                .catalog(productCat)
                .unit(unit)
                .build();
        productMapper.fromDto(productDto,product);
        productRepository.save(product);
        return productMapper.toDto(product);
    }

    @Override
    public List<ProductResponseDto> getProducts(ProductRequestDto productForm) {
        return null;
    }
}
