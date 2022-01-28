package com.github.geoaxis.eccjava.domain;

import com.github.geoaxis.eccjava.domain.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SotckRepository extends JpaRepository<StockEntity, Integer> {

}
