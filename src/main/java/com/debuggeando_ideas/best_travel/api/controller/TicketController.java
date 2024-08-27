package com.debuggeando_ideas.best_travel.api.controller;

import com.debuggeando_ideas.best_travel.api.models.request.TicketRequest;
import com.debuggeando_ideas.best_travel.api.models.responses.TicketResponse;
import com.debuggeando_ideas.best_travel.infraestructure.abstract_services.ITicketService;
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
  value = ResponseConstant.TICKET_URL,
  produces = {
    MediaType.APPLICATION_JSON_VALUE })
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
  RequestMethod.PUT })
public class TicketController {

  private final ITicketService ticketService;

  @PostMapping
  public ResponseEntity<TicketResponse> saveTicket(@RequestBody TicketRequest request) {
    return ResponseEntity.ok(ticketService.create(request));
  }

  @GetMapping(path = "{id}")
  public ResponseEntity<TicketResponse> getTicketId(@PathVariable UUID id) {
    return ResponseEntity.ok(ticketService.read(id));
  }

  @PutMapping(path = "{id}")
  public ResponseEntity<TicketResponse> putTicket(@PathVariable UUID id, @RequestBody TicketRequest request) {
    return ResponseEntity.ok(this.ticketService.update(request, id));
  }
  @DeleteMapping(path = "{id}")
  public ResponseEntity<Void> deletedTicketId(@PathVariable UUID id) {
    this.ticketService.delete(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping
  public ResponseEntity<Map<String, BigDecimal>> getFlyPrice(@RequestParam Long flyId) {
    return ResponseEntity.ok(Collections.singletonMap("flyPrice", this.ticketService.findPrice(flyId)));
  }

}
