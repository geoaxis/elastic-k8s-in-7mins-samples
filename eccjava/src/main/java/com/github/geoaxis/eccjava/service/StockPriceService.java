package com.github.geoaxis.eccjava.service;

import java.util.HashMap;
import java.util.Map;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class StockPriceService {

  @Value("${STOCK_SERVICE_URL:http://localhost:3001/stocks}")
  private String stockPriceServiceUrl;

  private RestTemplate restTemplate;

  public StockPriceService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @SneakyThrows
  @Cacheable("stocks")
  public Map<String, Integer> getStockPrices() {

    ParameterizedTypeReference<HashMap<String, Integer>> responseType =
        new ParameterizedTypeReference<HashMap<String, Integer>>() {};

    RequestEntity<Void> request = RequestEntity.get(stockPriceServiceUrl)
        .accept(MediaType.APPLICATION_JSON).build();

    return restTemplate.exchange(request, responseType).getBody();
  }
}
