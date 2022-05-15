package ru.itis.stockmarket.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.itis.stockmarket.dtos.OrganizationResponseDto;
import ru.itis.stockmarket.dtos.ProductRequestDto;
import ru.itis.stockmarket.dtos.ProductResponseDto;
import ru.itis.stockmarket.models.Organization;
import ru.itis.stockmarket.models.Product;

import java.util.UUID;

@Mapper(componentModel = "spring", imports = {UUID.class})
public interface ProductMapper {

    void fromDto(ProductRequestDto dto, @MappingTarget Product org);
    
    ProductResponseDto toDto(Product org);
}
