package com.taoran.appdirect.domain.model;

import java.util.Locale;

import lombok.Data;

@Data
public class Creator {
  private Address address;
  private String email;
  private String firstName;
  private String language;
  private String lastName;
  private Locale locale;
  private String openId;
  private String uuid;
}
