package com.debuggeando_ideas.best_travel.domain.entities;

import com.debuggeando_ideas.best_travel.util.AeroLine;
import jakarta.persistence.*;
import lombok.*;


import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity(name="fly")
public class FlyEntity implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Double originLat;

  private Double originLgn;

  private Double destinLat;

  private Double destinLgn;

  @Column(length = 20)
  private String originName;

  @Column(length = 20)
  private String destinName;

  private BigDecimal price;

  @Enumerated(EnumType.STRING)
  private AeroLine aeroLine;

  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  //un vuelo puede terner muchos tickets
  @OneToMany(
    cascade = CascadeType.ALL,
    fetch = FetchType.EAGER,
    orphanRemoval = true,//si un objeto queda huerfano se remueve
    mappedBy = "fly"
  )
  private Set<TicketEntity> tickets;

}
