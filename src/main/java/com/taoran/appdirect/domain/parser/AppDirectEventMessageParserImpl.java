package com.taoran.appdirect.domain.parser;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.taoran.appdirect.domain.model.SubscriptionEvent;

public class AppDirectEventMessageParserImpl implements AppDirectEventMessageParser {
  private final static Logger LOGGER = LoggerFactory.getLogger(AppDirectEventMessageParserImpl.class);

  
  @Override
  public SubscriptionEvent parseSubscriptionEvent(String message) {
    //For this demo, I'll skip the parsing
    //In the real case, it should be using a JAXB or JASON reader to parse the event object
    // Assert message is not null
    LOGGER.debug("Message received:{}", message);
    SubscriptionEvent event = new SubscriptionEvent();
    return event;
  }

}
