package com.github.geoaxis.eccjava.domain;

import java.util.List;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "STOCK_USER")
public class UserEntity {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private int id;

  @NotBlank(message = "firstName is mandatory")
  @Column(nullable = false)
  private String firstName;

  @NotBlank(message = "lastName is mandatory")
  @Column(nullable = false)
  private String lastName;


  @OneToMany(mappedBy= "user")
  private List<StockItemEntity> stockItems;


}
