package com.github.geoaxis.eccjava.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPostDTO {

  @JsonProperty("firstName")
  private String firstName;

  @JsonProperty("lastName")
  private String lastName;
}
