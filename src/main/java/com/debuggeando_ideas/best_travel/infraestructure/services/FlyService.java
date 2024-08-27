package com.debuggeando_ideas.best_travel.infraestructure.services;

import com.debuggeando_ideas.best_travel.api.models.responses.FlyResponse;
import com.debuggeando_ideas.best_travel.infraestructure.abstract_services.IFlyService;
import com.debuggeando_ideas.best_travel.util.SortType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Set;

@Transactional
@Service
@Slf4j
@AllArgsConstructor
public class FlyService implements IFlyService {
  @Override
  public Page<FlyResponse> realAll(Integer page, Integer size, SortType sortType) {
    return null;
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
}
