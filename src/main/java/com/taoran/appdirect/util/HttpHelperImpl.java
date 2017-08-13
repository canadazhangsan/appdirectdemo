package com.taoran.appdirect.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

import javax.inject.Inject;
import javax.net.ssl.HttpsURLConnection;

import org.springframework.security.oauth.provider.ConsumerDetails;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;

public class HttpHelperImpl implements HttpHelper {

  
  @Inject
  public ConsumerDetails consumerDetails;
  
  @Override
  public String readUrl(String url) {
    return readUrl(url, false);
  }

  @Override
  public String readSSLUrl(String url) {
    return readUrl(url, true);
  }
  
  private String readUrl(String url, boolean isSSL) {
    StringBuilder sb = new StringBuilder();
    URLConnection urlConn = null;
    InputStreamReader in = null;
    try {
      OAuthConsumer consumer = new DefaultOAuthConsumer(consumerDetails.getConsumerKey(), "odfsEhGBu9iWgTt1");
      URL getUrl = new URL(url);
      urlConn = isSSL? (HttpsURLConnection)getUrl.openConnection() : (HttpURLConnection)getUrl.openConnection();
      if (urlConn != null) {
        urlConn.setReadTimeout(60 * 1000);
        consumer.sign(urlConn);
      }
      if (urlConn != null && urlConn.getInputStream() != null) {
        in = new InputStreamReader(urlConn.getInputStream(), Charset.defaultCharset());
        BufferedReader bufferedReader = new BufferedReader(in);
        if (bufferedReader != null) {
          int cp;
          while ((cp = bufferedReader.read()) != -1) {
            sb.append((char) cp);
          }
          bufferedReader.close();
        }
      }
      in.close();
    } catch (Exception e) {
      throw new RuntimeException("Exception while calling URL:" + url, e);
    }
    return sb.toString();
  }

}
