package com.github.geoaxis.eccjava.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserStockDto {

  @JsonProperty("ticker")
  private String ticker;

  @JsonProperty("numberOfStocks")
  private int numberOfStocks;

  @JsonProperty("stockPrice")
  private int stockPrice;

  @JsonProperty("totalValue")
  private int totalValue;
}
