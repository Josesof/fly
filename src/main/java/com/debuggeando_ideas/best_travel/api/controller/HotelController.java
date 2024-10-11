package com.debuggeando_ideas.best_travel.api.controller;


import com.debuggeando_ideas.best_travel.api.models.responses.HotelResponse;
import com.debuggeando_ideas.best_travel.infraestructure.services.HotelServiceI;
import com.debuggeando_ideas.best_travel.util.ResponseConstant;
import com.debuggeando_ideas.best_travel.util.SortType;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

@RestController
@AllArgsConstructor
@RequestMapping(
  value = ResponseConstant.HOTEL_URL,
  produces = {
    MediaType.APPLICATION_JSON_VALUE })
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
  RequestMethod.PUT })
public class HotelController {

  private final HotelServiceI hotelService;

  @GetMapping
  public ResponseEntity<Page<HotelResponse>> getAll(
    @RequestParam Integer page,
    @RequestParam Integer size,
    @RequestHeader(required = false) SortType sortType){
    if(Objects.isNull(sortType)) sortType = SortType.NONE;
    var response = this.hotelService.realAll(page, size, sortType);
    return response.isEmpty()? ResponseEntity.noContent().build():ResponseEntity.ok(response);
  }

  @GetMapping(path = "les_price")
  public ResponseEntity<Set<HotelResponse>> getLesPrice(@RequestParam BigDecimal price){
    var response = this.hotelService.readLessPrice(price);
    return response.isEmpty()? ResponseEntity.noContent().build():ResponseEntity.ok(response);
  }

  @GetMapping(path = "between_price")
  public ResponseEntity<Set<HotelResponse>> getBetweenPrice(
    @RequestParam BigDecimal min,
    @RequestParam BigDecimal max){
    var response = this.hotelService.readBetweenPrices(min, max);
    return response.isEmpty()? ResponseEntity.noContent().build():ResponseEntity.ok(response);
  }

  @GetMapping(path = "rating")
  public ResponseEntity<Set<HotelResponse>> getByRating(@RequestParam Integer rating){
    if(rating > 4) rating=4;
    if(rating < 1) rating=1;
    var response = this.hotelService.readByRating(rating);
    return response.isEmpty()? ResponseEntity.noContent().build():ResponseEntity.ok(response);
  }
}
