package com.taoran.appdirect.domain.model;

import lombok.Data;

/**
 * Define the response messages.
 * 
 * @author Ran
 *
 */

@Data
public class AppDirectResponse {
  private Boolean success;
  private String accountidentifier;
  private String errorCode;
  private String message;
}
