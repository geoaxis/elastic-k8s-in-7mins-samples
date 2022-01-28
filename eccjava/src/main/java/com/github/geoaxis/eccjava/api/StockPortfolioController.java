package com.github.geoaxis.eccjava.api;

import com.github.geoaxis.eccjava.domain.UserRepository;
import com.github.geoaxis.eccjava.domain.StockItemRepository;
import com.github.geoaxis.eccjava.dtos.UserPostDTO;
import com.github.geoaxis.eccjava.service.StockPriceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
public class StockPortfolioController {

  private final UserRepository userRepository;
  private final StockItemRepository stockItemRepository;
  private final Mapper mapper;
  private final StockPriceService stockPriceService;

  public StockPortfolioController(UserRepository userRepository,
      StockItemRepository stockItemRepository,
      Mapper mapper,
      StockPriceService stockPriceService) {
    this.userRepository = userRepository;
    this.stockItemRepository = stockItemRepository;
    this.mapper = mapper;
    this.stockPriceService = stockPriceService;
  }

  @GetMapping("/user")
  public ResponseEntity<?> getAllUsers() {
    return ResponseEntity.ok(userRepository.findAll().stream().map(mapper::toGetDto).toList());
  }

  @GetMapping("/user/{id}")
  public ResponseEntity<?> getUser(@PathVariable int id, @RequestParam boolean stocks) {
    var user = mapper.toGetDto(userRepository.getById(id));
    if(stocks) {
      var prices = stockPriceService.getStockPrices();
      var items = stockItemRepository.findAllStockItemsForUserId(id);
      user.setStocks(items.stream().map(mapper::toUserStockDto)
              .peek(dto -> dto.setStockPrice(prices.getOrDefault(dto.getTicker(), 0)))
              .peek(dto -> dto.setTotalValue(dto.getStockPrice() * dto.getNumberOfStocks()))
          .toList());

    }
    return ResponseEntity.ok(user);
  }

  @PostMapping("/user")
  @Operation(summary = "Create user",
      description = "Creates a new user in database.")
  public ResponseEntity<?> createNewUser
      (
          @Valid
          @io.swagger.v3.oas.annotations.parameters.RequestBody(
              description = "User object that needs to be added to the store", required = true,
              content = @Content(
                  schema = @Schema(implementation = UserPostDTO.class))
          )
          @RequestBody UserPostDTO userPostDto
      ) {
    return new ResponseEntity<>(userRepository.save(mapper.fromDto(userPostDto)),
        HttpStatus.CREATED);
  }

}

