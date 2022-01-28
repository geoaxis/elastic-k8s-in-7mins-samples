package com.github.geoaxis.eccjava.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
