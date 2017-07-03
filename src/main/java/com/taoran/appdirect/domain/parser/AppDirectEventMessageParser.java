package com.taoran.appdirect.domain.parser;

import com.taoran.appdirect.domain.model.SubscriptionEvent;

/**
 * Define parsers for AppDirect API event messages.
 * 
 * @author Ran
 *
 */
public interface AppDirectEventMessageParser {

  /**
   * Parse event of subscriptions.
   * @param message the message from event url.
   * @return the {@link SubscriptionEvent}.
   */
  public SubscriptionEvent parseSubscriptionEvent(String message);
}
