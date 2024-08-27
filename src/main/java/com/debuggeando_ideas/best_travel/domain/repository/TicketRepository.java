package com.debuggeando_ideas.best_travel.domain.repository;


import com.debuggeando_ideas.best_travel.domain.entities.HotelEntity;
import com.debuggeando_ideas.best_travel.domain.entities.TicketEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface TicketRepository extends CrudRepository<TicketEntity, String> {

  @Query("select f from  ticket f where f.id = :id")
  Optional<TicketEntity> ticketById(UUID id);
}
