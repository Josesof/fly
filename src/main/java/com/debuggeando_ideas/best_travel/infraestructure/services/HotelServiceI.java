package com.debuggeando_ideas.best_travel.infraestructure.services;

import com.debuggeando_ideas.best_travel.api.models.responses.HotelResponse;
import com.debuggeando_ideas.best_travel.domain.entities.HotelEntity;
import com.debuggeando_ideas.best_travel.domain.repository.HotelRepository;
import com.debuggeando_ideas.best_travel.infraestructure.abstract_services.IHotelServiceI;
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
import java.util.stream.Collectors;

@Transactional
@Service
@Slf4j
@AllArgsConstructor
public class HotelServiceI implements IHotelServiceI {

  private final HotelRepository hotelRepository;
  @Override
  public Set<HotelResponse> readByRating(Integer raiting) {
    return this.hotelRepository.findByRatingGreaterThan(raiting)
      .stream()
      .map(this::hotelResponse)
      .collect(Collectors.toSet());
  }

  @Override
  public Page<HotelResponse> realAll(Integer page, Integer size, SortType sortType) {

    PageRequest pageRequest = null;
    switch (sortType){
      case NONE -> pageRequest = PageRequest.of(page, size);
      case LOWER -> pageRequest = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).ascending());
      case UPPER -> pageRequest = PageRequest.of(page, size, Sort.by(FIELD_BY_SORT).descending());
    }
    return this.hotelRepository.findAll(pageRequest).map(this::hotelResponse);
  }

  @Override
  public Set<HotelResponse> readLessPrice(BigDecimal price) {
    return this.hotelRepository.findByPriceLessThan(price)
      .stream()
      .map(this::hotelResponse)
      .collect(Collectors.toSet());
  }

  @Override
  public Set<HotelResponse> readBetweenPrices(BigDecimal min, BigDecimal max) {
    return this.hotelRepository.findByPriceIsBetween(min, max)
      .stream()
      .map(this::hotelResponse)
      .collect(Collectors.toSet());
  }

  private HotelResponse hotelResponse(HotelEntity entity){
    HotelResponse response = new HotelResponse();
    BeanUtils.copyProperties(entity, response);
    return response;
  }
}
