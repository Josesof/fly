package com.debuggeando_ideas.best_travel.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity(name="customer")
public class CustomerEntity implements Serializable {

  @Id
  private String dni;
  @Column(length = 50)
  private String fullName;
  @Column(length = 20)
  private String creditCard;
  @Column(length = 12)
  private String phoneNumber;
  private Integer totalFlights;
  private Integer totalLodgings;
  private Integer totalTours;

 @ToString.Exclude
 @EqualsAndHashCode.Exclude
  @OneToMany(
    cascade = CascadeType.ALL,
    fetch = FetchType.EAGER,
    orphanRemoval = true,//si un objeto queda huerfano se remueve
    mappedBy = "customer"
  )
  private Set<TicketEntity> tickets;

  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  @OneToMany(
    cascade = CascadeType.ALL,
    fetch = FetchType.EAGER,
    orphanRemoval = true,//si un objeto queda huerfano se remueve
    mappedBy = "customer"
  )
  private Set<ReservationEntity> reservation;

  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  @OneToMany(
    cascade = CascadeType.ALL,
    fetch = FetchType.LAZY,
    orphanRemoval = true,//si un objeto queda huerfano se remueve
    mappedBy = "customer"
  )
  private Set<TourEntity> tour;
}
