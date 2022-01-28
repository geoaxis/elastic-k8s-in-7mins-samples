package com.github.geoaxis.eccjava.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "STOCK")
public class StockEntity {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private int id;

  @NotBlank(message = "Stock ticker is mandatory")
  @Column(nullable = false)
  private String ticker;

}
