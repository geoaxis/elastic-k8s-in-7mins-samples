package com.github.geoaxis.eccjava.domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
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
