package com.github.geoaxis.eccjava.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SotckRepository extends JpaRepository<StockEntity, Integer> {

}
