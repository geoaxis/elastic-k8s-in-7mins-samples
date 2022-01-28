package com.github.geoaxis.eccjava.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserGetDTO {

  @JsonProperty("id")
  private int id;

  @JsonProperty("firstName")
  private String firstName;

  @JsonProperty("lastName")
  private String lastName;

  @JsonProperty("stocks")
  private List<UserStockDto> stocks;
}
