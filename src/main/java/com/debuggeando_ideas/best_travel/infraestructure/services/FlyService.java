package com.debuggeando_ideas.best_travel.infraestructure.services;

import com.debuggeando_ideas.best_travel.api.models.responses.FlyResponse;
import com.debuggeando_ideas.best_travel.domain.entities.FlyEntity;
import com.debuggeando_ideas.best_travel.domain.repository.FlyRepository;
import com.debuggeando_ideas.best_travel.infraestructure.abstract_services.IFlyService;
import com.debuggeando_ideas.best_travel.util.SortType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Set;

@Transactional
@Service
@Slf4j
@AllArgsConstructor
public class FlyService implements IFlyService {

  private final FlyRepository flyRepository;
  @Override
  public Page<FlyResponse> realAll(Integer page, Integer size, SortType sortType) {
    PageRequest pageRequest = null;
    switch (sortType){
      case NONE -> pageRequest = PageRequest.of(page, size);
      case LOWER -> pageRequest = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
      case UPPER -> pageRequest = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
    }
    return this.flyRepository.findAll(pageRequest).map(this::entityTResponse);
  }

  @Override
  public Set<FlyResponse> readLessPrice(BigDecimal price) {
    return null;
  }

  @Override
  public Set<FlyResponse> readBetweenPrices(BigDecimal min, BigDecimal max) {
    return null;
  }

  @Override
  public Set<FlyResponse> readByOriginDestiny(String origen, String destiny) {
    return null;
  }

  private FlyResponse entityTResponse(FlyEntity entity){
    FlyResponse response = new FlyResponse();
    BeanUtils.copyProperties(entity, response);
    return response;
  }

}
