package com.debuggeando_ideas.best_travel.domain.repository;


import com.debuggeando_ideas.best_travel.domain.entities.ReservationEntity;
import com.debuggeando_ideas.best_travel.domain.entities.TicketEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

public interface ReservationRepository extends CrudRepository<ReservationEntity, String> {

  @Query("select f from  reservation f where f.id = :id")
  Optional<ReservationEntity> reservationById(UUID id);

  BigDecimal findPrice(Long hotelId);
}
