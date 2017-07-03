package com.taoran.appdirect.util;

/**
 * Define helper class to read or send Http responses/requests.
 * 
 * @author Ran
 *
 */
public interface HttpHelper {
  
  /**
   * Read from a given HTTP url.
   * @param url the url
   * @return the return message
   */
  public String readUrl(String url);
  
  /**
   * Read from a give HTTPS url.
   * @param url the url
   * @return the return message
   */
  public String readSSLUrl(String url);
}
