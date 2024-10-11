package com.debuggeando_ideas.best_travel.infraestructure.services;

import com.debuggeando_ideas.best_travel.api.models.request.ReservationRequest;
import com.debuggeando_ideas.best_travel.api.models.responses.HotelResponse;
import com.debuggeando_ideas.best_travel.api.models.responses.ReservationResponse;
import com.debuggeando_ideas.best_travel.domain.entities.ReservationEntity;
import com.debuggeando_ideas.best_travel.domain.repository.*;
import com.debuggeando_ideas.best_travel.infraestructure.abstract_services.IReservationService;
import com.debuggeando_ideas.best_travel.util.ResponseConstant;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Transactional
@Service
@Slf4j
@AllArgsConstructor
public class ReservationService implements IReservationService {

  private final HotelRepository hotelRepository;
  private final CustomerRepository customerRepository;
  private final ReservationRepository reservationRepository;

  @Override
  public ReservationResponse create(ReservationRequest request) {

    var hotel = hotelRepository.findById(request.getIdHotel()).orElseThrow();
    var customer = customerRepository.findById(request.getIdClient()).orElseThrow();

    var reservationToPersist = ReservationEntity.builder()
      .id(UUID.randomUUID())
      .hotel(hotel)
      .customer(customer)
      .totalDays(request.getTotalDays())
      .dataTimeReservation(LocalDateTime.now())
      .dateStart(LocalDate.now())
      .dateEnd(LocalDate.now().plusDays(request.getTotalDays()))
      .price(hotel.getPrice().add(hotel.getPrice().multiply(ResponseConstant.charges_price_percentage)))
      .build();

    var reservationPersisted = reservationRepository.save(reservationToPersist);


    return this.entityToResponse(reservationPersisted);
  }



  @Override
  public ReservationResponse read(UUID uuid) {
    var reservationFromDB = reservationRepository.reservationById(uuid).orElseThrow();
    return this.entityToResponse(reservationFromDB);
  }

  @Override
  public ReservationResponse update(ReservationRequest request, UUID uuid) {

    var hotel = hotelRepository.findById(request.getIdHotel()).orElseThrow();
    var reservationToUpdate = this.reservationRepository.reservationById(uuid).orElseThrow();

      reservationToUpdate.setHotel(hotel);
      reservationToUpdate.setTotalDays(request.getTotalDays());
      reservationToUpdate.setDataTimeReservation(LocalDateTime.now());
      reservationToUpdate.setDateStart(LocalDate.now());
      reservationToUpdate.setDateEnd(LocalDate.now().plusDays(request.getTotalDays()));
      reservationToUpdate.setPrice(hotel.getPrice().add(hotel.getPrice().multiply(ResponseConstant.charges_price_percentage)));

       this.reservationRepository.save(reservationToUpdate);

      log.info("Reservation updated with id {}", reservationToUpdate.getId());

    return this.entityToResponse(reservationToUpdate);
  }

  @Override
  public void delete(UUID uuid) {
    var reservationFromDB = reservationRepository.reservationById(uuid).orElseThrow();
    this.reservationRepository.delete(reservationFromDB);
  }

  private ReservationResponse entityToResponse(ReservationEntity reservation) {

    var response = new ReservationResponse();
    BeanUtils.copyProperties(reservation, response);

    var hotelResponse = new HotelResponse();

    BeanUtils.copyProperties(reservation.getHotel(), hotelResponse);
    response.setHotel(hotelResponse);
    return response;
  }

  @Override
  public BigDecimal findPrice(Long hotelId) {
    var hotel = this.hotelRepository.findById(hotelId).orElseThrow();
    return hotel.getPrice().add(hotel.getPrice().multiply(ResponseConstant.charger_price_percentage));
  }
}
