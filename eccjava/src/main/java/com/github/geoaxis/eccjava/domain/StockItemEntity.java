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
@Table(name = "STOCK_ITEM")
public class StockItemEntity {

  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private int id;


  @NotBlank(message = "Quantity of stock")
  @Column(nullable = false)
  private int quantity;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="stock_id")
  private StockEntity stock;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="user_id")
  private UserEntity user;

}
