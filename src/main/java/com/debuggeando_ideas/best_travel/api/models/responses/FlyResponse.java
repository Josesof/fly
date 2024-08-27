package com.debuggeando_ideas.best_travel.api.models.responses;

import com.debuggeando_ideas.best_travel.util.AeroLine;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FlyResponse implements Serializable {

  private Long id;
  private Double originLat;
  private Double originLgn;
  private Double destinLat;
  private Double destinLgn;
  private String originName;
  private String destinName;
  private BigDecimal price;
  private AeroLine aeroLine;
}
