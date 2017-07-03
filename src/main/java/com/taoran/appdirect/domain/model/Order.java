package com.taoran.appdirect.domain.model;

import java.util.List;

import lombok.Data;

@Data
public class Order {
  private String editionCode;
  private String pricingDuration;
  private List<Item> items;
}
