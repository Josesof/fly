package com.debuggeando_ideas.best_travel.api.controller;

import com.debuggeando_ideas.best_travel.api.models.responses.FlyResponse;
import com.debuggeando_ideas.best_travel.infraestructure.abstract_services.IFlyService;
import com.debuggeando_ideas.best_travel.util.ResponseConstant;
import com.debuggeando_ideas.best_travel.util.SortType;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@AllArgsConstructor
@RequestMapping(
  value = ResponseConstant.FLY_URL,
  produces = {
    MediaType.APPLICATION_JSON_VALUE })
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
  RequestMethod.PUT })
public class FlyController {

  private final IFlyService flyService;

  @GetMapping
  public ResponseEntity<Page<FlyResponse>> getAll(
    @RequestParam Integer page,
    @RequestParam Integer size,
    @RequestHeader(required = false)SortType sortType){
    if(Objects.isNull(sortType)) sortType = SortType.NONE;
    var response = this.flyService.realAll(page, size, sortType);
    return response.isEmpty()? ResponseEntity.noContent().build():ResponseEntity.ok(response);
  }


}
