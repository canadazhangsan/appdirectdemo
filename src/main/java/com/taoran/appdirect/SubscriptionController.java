package com.taoran.appdirect;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.taoran.appdirect.domain.model.AppDirectResponse;
import com.taoran.appdirect.domain.model.SubscriptionEvent;
import com.taoran.appdirect.domain.parser.AppDirectEventMessageParser;
import com.taoran.appdirect.util.HttpHelper;


@RestController
// We can use preAuthorize
//@PreAuthorize("hasRole('ROLE_USER')")
@RequestMapping(path = "/subscription") 
public class SubscriptionController {
  private final static Logger LOGGER = LoggerFactory.getLogger(SubscriptionController.class);
  private final static Boolean SUCCESS = true;
  private final static Boolean FAILED = false;
  @Inject
  private AppDirectEventMessageParser appDirectEventMessageParser;  
  @Inject
  private HttpHelper httpHelper;
  
  
  
  @GetMapping(path = "/create/notification")
  public AppDirectResponse addNewUserHandler(@RequestParam String eventUrl) {
    LOGGER.debug("eventUrl:{}", eventUrl);
    AppDirectResponse response = new AppDirectResponse();
    if (eventUrl != null) {
      //I got some 401 error when reading the SSL url here, might need to authorize the url
      String subscriptionEvent = httpHelper.readSSLUrl(eventUrl);
      //I assume all the orders are good, so I skip the validation part
      SubscriptionEvent event = appDirectEventMessageParser.parseSubscriptionEvent(subscriptionEvent);
      //Then verify these information, get the order, do the user subscription in app and return
      response.setSuccess(SUCCESS);
      response.setAccountidentifier(String.valueOf(Math.random()));
      response.setMessage("User subscripted");
    } else {
      response.setSuccess(FAILED);
      response.setMessage("Didn't get the event url!");
    }
    return response;
  }

}
