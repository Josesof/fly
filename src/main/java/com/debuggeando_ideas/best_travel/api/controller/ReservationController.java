package com.debuggeando_ideas.best_travel.api.controller;

import com.debuggeando_ideas.best_travel.api.models.request.ReservationRequest;
import com.debuggeando_ideas.best_travel.api.models.request.TicketRequest;
import com.debuggeando_ideas.best_travel.api.models.responses.ReservationResponse;
import com.debuggeando_ideas.best_travel.api.models.responses.TicketResponse;
import com.debuggeando_ideas.best_travel.infraestructure.abstract_services.IReservationService;
import com.debuggeando_ideas.best_travel.util.ResponseConstant;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@RestController
@AllArgsConstructor
@RequestMapping(
  value = ResponseConstant.RESERVATION_URL,
  produces = {
    MediaType.APPLICATION_JSON_VALUE })
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
  RequestMethod.PUT })
public class ReservationController {

  private final IReservationService reservationService;

  @PostMapping
  public ResponseEntity<ReservationResponse> saveReservation(@RequestBody ReservationRequest request) {
    return ResponseEntity.ok(reservationService.create(request));
  }

  @GetMapping(path = "{id}")
  public ResponseEntity<ReservationResponse> getReservationId(@PathVariable UUID id) {
    return ResponseEntity.ok(reservationService.read(id));
  }

  @PutMapping(path = "{id}")
  public ResponseEntity<ReservationResponse> putReservation(@PathVariable UUID id, @RequestBody ReservationRequest request) {
    return ResponseEntity.ok(this.reservationService.update(request, id));
  }
  @DeleteMapping(path = "{id}")
  public ResponseEntity<Void> deletedReservationId(@PathVariable UUID id) {
    this.reservationService.delete(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping
  public ResponseEntity<Map<String, BigDecimal>> getFlyPrice(@RequestParam Long reservationId) {
    return ResponseEntity.ok(Collections.singletonMap("reservationIdPrice", this.reservationService.findPrice(reservationId)));
  }
}
