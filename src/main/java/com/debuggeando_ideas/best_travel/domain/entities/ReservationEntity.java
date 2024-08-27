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
@Entity(name="reservation")
public class ReservationEntity implements Serializable {

  @Id
  private UUID id;

  @Column(name="date_reservation")
  private LocalDateTime dataTimeReservation;
  private LocalDate dateStart;
  private LocalDate dateEnd;
  private Integer totalDays;
  private BigDecimal price;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="hotel_id")
  private HotelEntity hotel;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="tour_id", nullable = true)
  private TourEntity tour;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="customer_id", nullable = true)
  private CustomerEntity customer;
}
