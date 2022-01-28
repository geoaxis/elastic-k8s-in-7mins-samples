package com.github.geoaxis.eccjava.domain;

import com.github.geoaxis.eccjava.domain.StockItemEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StockItemRepository extends JpaRepository<StockItemEntity, Integer> {

  @Query("SELECT si FROM StockItemEntity si WHERE si.user.id = :userId")
  List<StockItemEntity> findAllStockItemsForUserId(@Param("userId") int id);
}
