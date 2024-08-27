package com.debuggeando_ideas.best_travel.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity(name="ticket")
public class TicketEntity implements Serializable {

  @Id
  private UUID id;
  private LocalDateTime departureDate;
  private LocalDateTime arriveDate;
  private LocalDate purchaseDate;
  private BigDecimal price;

  //Much's tickets pueden pertenecer a un vuelo
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "fly_id")//cual es la columna de union fly_id
  private FlyEntity fly;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="tour_id", nullable = true)
  private TourEntity tour;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="customer_id", nullable = true)
  private CustomerEntity customer;

}
