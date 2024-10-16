package com.debuggeando_ideas.best_travel.api.controller;

import com.debuggeando_ideas.best_travel.api.models.responses.FlyResponse;
import com.debuggeando_ideas.best_travel.infraestructure.abstract_services.IFlyServiceI;
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
  value = ResponseConstant.FLY_URL,
  produces = {
    MediaType.APPLICATION_JSON_VALUE })
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
  RequestMethod.PUT })
public class FlyController {

  private final IFlyServiceI flyService;

  @GetMapping
  public ResponseEntity<Page<FlyResponse>> getAll(
    @RequestParam Integer page,
    @RequestParam Integer size,
    @RequestHeader(required = false)SortType sortType){
    if(Objects.isNull(sortType)) sortType = SortType.NONE;
    var response = this.flyService.realAll(page, size, sortType);
    return response.isEmpty()? ResponseEntity.noContent().build():ResponseEntity.ok(response);
  }

  @GetMapping(path = "les_price")
  public ResponseEntity<Set<FlyResponse>> getLesPrice(@RequestParam BigDecimal price){
    var response = this.flyService.readLessPrice(price);
    return response.isEmpty()? ResponseEntity.noContent().build():ResponseEntity.ok(response);
  }

  @GetMapping(path = "between_price")
  public ResponseEntity<Set<FlyResponse>> getBetweenPrice(
    @RequestParam BigDecimal min,
    @RequestParam BigDecimal max){
    var response = this.flyService.readBetweenPrices(min, max);
    return response.isEmpty()? ResponseEntity.noContent().build():ResponseEntity.ok(response);
  }

  @GetMapping(path = "origin_destiny")
  public ResponseEntity<Set<FlyResponse>> getOriginDestiny(
    @RequestParam String origin,
    @RequestParam String destiny){
    var response = this.flyService.readByOriginDestiny(origin, destiny);
    return response.isEmpty()? ResponseEntity.noContent().build():ResponseEntity.ok(response);
  }

}
