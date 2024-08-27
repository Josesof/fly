package com.debuggeando_ideas.best_travel.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity(name="tour")
public class TourEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  @OneToMany(
    cascade = CascadeType.ALL,
    fetch = FetchType.EAGER,
    orphanRemoval = true,//si un objeto queda huerfano se remueve
    mappedBy = "tour"
  )
  private Set<ReservationEntity> reservations;

  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  @OneToMany(
    cascade = CascadeType.ALL,
    fetch = FetchType.EAGER,
    orphanRemoval = true,//si un objeto queda huerfano se remueve
    mappedBy = "tour"
  )
  private Set<TicketEntity> tickets;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="id_customer", nullable = true)
  private CustomerEntity customer;
}
