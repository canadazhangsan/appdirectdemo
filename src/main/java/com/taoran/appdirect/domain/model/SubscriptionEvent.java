package com.taoran.appdirect.domain.model;

import lombok.Data;

@Data
public class SubscriptionEvent {
  private String type;
  private Marketplace marketplace;
  private Creator creator;
  private Payload payload;
  private String returnUrl;
}
