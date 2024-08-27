package com.debuggeando_ideas.best_travel.infraestructure.services;

import com.debuggeando_ideas.best_travel.api.models.request.TicketRequest;
import com.debuggeando_ideas.best_travel.api.models.responses.FlyResponse;
import com.debuggeando_ideas.best_travel.api.models.responses.TicketResponse;
import com.debuggeando_ideas.best_travel.domain.entities.TicketEntity;
import com.debuggeando_ideas.best_travel.domain.repository.CustomerRepository;
import com.debuggeando_ideas.best_travel.domain.repository.FlyRepository;
import com.debuggeando_ideas.best_travel.domain.repository.TicketRepository;
import com.debuggeando_ideas.best_travel.infraestructure.abstract_services.ITicketService;
import com.debuggeando_ideas.best_travel.util.BestTravelUtil;
import com.debuggeando_ideas.best_travel.util.ResponseConstant;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Transactional
@Service
@Slf4j
@AllArgsConstructor
public class TicketService implements ITicketService {

  private final FlyRepository flyRepository;
  private final CustomerRepository customerRepository;
  private final TicketRepository ticketRepository;

  @Override
  public TicketResponse create(TicketRequest request) {

    var fly = flyRepository.findById(request.getIdFly()).orElseThrow();
    var customer = customerRepository.findById(request.getIdClient()).orElseThrow();

    var ticketToPersist = TicketEntity.builder()
      .id(UUID.randomUUID())
      .fly(fly)
      .customer(customer)
      .price(fly.getPrice().add(fly.getPrice().multiply(ResponseConstant.charger_price_percentage)))
      .purchaseDate(LocalDate.now())
      .arriveDate(BestTravelUtil.getRandomSoon())
      .departureDate(BestTravelUtil.getRandomLatter())
      .build();

    var ticketPersisted = this.ticketRepository.save(ticketToPersist);

    log.info("Ticket saved with id: {}", ticketPersisted.getId());

    return this.entityToResponse(ticketPersisted);
  }

  @Override
  public TicketResponse read(UUID uuid) {
    var ticketFromDB = findIdTicket(uuid);
    return this.entityToResponse(ticketFromDB);
  }

  @Override
  public TicketResponse update(TicketRequest request, UUID uuid) {
    var ticketToUpdate = findIdTicket(uuid);
    var fly = flyRepository.findById(request.getIdFly()).orElseThrow();

    ticketToUpdate.setFly(fly);
    ticketToUpdate.setPrice(fly.getPrice().add(fly.getPrice().multiply(ResponseConstant.charger_price_percentage)));
    ticketToUpdate.setDepartureDate(BestTravelUtil.getRandomSoon());
    ticketToUpdate.setArriveDate(BestTravelUtil.getRandomLatter());

    var ticketUpdated = this.ticketRepository.save(ticketToUpdate);

    log.info("Ticket updated with id {}", ticketUpdated.getId());

    return this.entityToResponse(ticketUpdated);
  }

  @Override
  public void delete(UUID uuid) {

    var ticketToDeleted = findIdTicket(uuid);
    this.ticketRepository.delete(ticketToDeleted);

  }

  @Override
  public BigDecimal findPrice(Long flyId) {
    var fly = this.flyRepository.findById(flyId).orElseThrow();
    return fly.getPrice().add(fly.getPrice().multiply(ResponseConstant.charger_price_percentage));
  }


  private TicketResponse entityToResponse(TicketEntity entity) {

    var response = new TicketResponse();
    BeanUtils.copyProperties(entity, response);
    var flyResponse = new FlyResponse();
    BeanUtils.copyProperties(entity.getFly(), flyResponse);
    response.setFly(flyResponse);
    return response;
  }

  private TicketEntity findIdTicket(UUID uuid) {
    return ticketRepository.ticketById(uuid).orElseThrow();
  }


}
