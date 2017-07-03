package com.taoran.appdirect.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth.provider.OAuthProcessingFilterEntryPoint;

/**
 * Copied from internet for demo, this is useless for now.
 * 
 * @author Ran
 *
 */
public class OAuthProcessingFilterEntryPointImpl extends OAuthProcessingFilterEntryPoint {
  
  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException failure) throws IOException, ServletException {
    response.setCharacterEncoding("UTF-8");
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    response.setContentType(MediaType.APPLICATION_JSON.getType());
    response.getWriter().println( "{\"Unauthorized\":\"" + failure + "\"}" );
  }
}
