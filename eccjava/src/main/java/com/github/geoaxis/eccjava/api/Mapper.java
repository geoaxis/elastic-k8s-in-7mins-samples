package com.github.geoaxis.eccjava.api;

import com.github.geoaxis.eccjava.domain.UserEntity;
import com.github.geoaxis.eccjava.domain.StockItemEntity;
import com.github.geoaxis.eccjava.dtos.UserGetDTO;
import com.github.geoaxis.eccjava.dtos.UserPostDTO;
import com.github.geoaxis.eccjava.dtos.UserStockDto;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@org.mapstruct.Mapper(componentModel = "spring")
public interface Mapper {
  Mapper MAPPER = Mappers.getMapper(Mapper.class);

  UserEntity fromDto(UserPostDTO dto);

  UserPostDTO toPostDto(UserEntity userEntity);

  UserEntity fromDto(UserGetDTO dto);

  UserGetDTO toGetDto(UserEntity userEntity);

  @Mapping(source = "quantity", target = "numberOfStocks")
  @Mapping(source = "stock.ticker", target = "ticker")
  UserStockDto toUserStockDto(StockItemEntity stockItemEntity);

  @InheritInverseConfiguration
  StockItemEntity fromDto(UserStockDto userStockDto);
}
