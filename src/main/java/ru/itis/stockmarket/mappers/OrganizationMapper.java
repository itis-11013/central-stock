package ru.itis.stockmarket.mappers;

import org.mapstruct.*;
import ru.itis.stockmarket.dtos.OrganizationRequestDto;
import ru.itis.stockmarket.dtos.OrganizationResponseDto;
import ru.itis.stockmarket.models.Organization;

import java.util.List;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA
 * Date: 14.05.2022
 * Time: 2:30 PM
 *
 * @author lordvidex
 * Name: Овамойо Олувадамилола Эванс
 * <p>
 * Desc:
 */

@Mapper(componentModel = "spring", imports = {UUID.class})
public interface OrganizationMapper {
    @Mapping(source = "country.code", target="countryCode")
    OrganizationResponseDto toDto(Organization org);

    List<OrganizationResponseDto> toDto(List<Organization> organizations);

    @Mapping(target = "country.code", source = "country")
    @Mapping(target = "innerId", expression = "java(UUID.randomUUID())")
    void fromDto(OrganizationRequestDto dto, @MappingTarget Organization org);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "country.code", source = "country")
    void updateFromDto(OrganizationRequestDto dto, @MappingTarget Organization org);
}