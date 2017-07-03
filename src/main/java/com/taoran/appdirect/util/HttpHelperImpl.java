package com.taoran.appdirect.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

import javax.net.ssl.HttpsURLConnection;

public class HttpHelperImpl implements HttpHelper {

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
      URL getUrl = new URL(url);
      urlConn = isSSL? (HttpsURLConnection)getUrl.openConnection() : (HttpURLConnection)getUrl.openConnection();
      if (urlConn != null)
        urlConn.setReadTimeout(60 * 1000);
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
