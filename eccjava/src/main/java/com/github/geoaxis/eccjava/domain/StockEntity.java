package com.github.geoaxis.eccjava.domain;


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
@Table(name = "STOCK")
public class StockEntity {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private int id;

  @NotBlank(message = "Stock ticker is mandatory")
  @Column(nullable = false)
  private String ticker;

}
