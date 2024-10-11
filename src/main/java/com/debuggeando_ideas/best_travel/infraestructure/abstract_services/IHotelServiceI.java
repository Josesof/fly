package com.debuggeando_ideas.best_travel.infraestructure.abstract_services;

import com.debuggeando_ideas.best_travel.api.models.responses.HotelResponse;

import java.util.Set;

public interface IHotelServiceI extends ICatalogService<HotelResponse> {

  Set<HotelResponse> readByRating(Integer raiting);
}
