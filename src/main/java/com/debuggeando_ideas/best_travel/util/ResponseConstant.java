package com.debuggeando_ideas.best_travel.util;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ResponseConstant {


  // ticket
  public static BigDecimal charger_price_percentage = BigDecimal.valueOf(0.25);

  //reservation
  public static BigDecimal charges_price_percentage = BigDecimal.valueOf(0.20);

  /**
   * Ticket
   */
  public static final String TICKET_URL = "/ticket";


  /**
   * Reservation
   */
  public static final String RESERVATION_URL = "/reservation";
}
