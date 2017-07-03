package com.taoran.appdirect;

import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth.common.signature.SharedConsumerSecretImpl;
import org.springframework.security.oauth.provider.BaseConsumerDetails;
import org.springframework.security.oauth.provider.ConsumerDetails;
import org.springframework.security.oauth.provider.ConsumerDetailsService;
import org.springframework.security.oauth.provider.DefaultAuthenticationHandler;
import org.springframework.security.oauth.provider.InMemoryConsumerDetailsService;
import org.springframework.security.oauth.provider.OAuthAuthenticationHandler;
import org.springframework.security.oauth.provider.OAuthProcessingFilterEntryPoint;
import org.springframework.security.oauth.provider.filter.UnauthenticatedRequestTokenProcessingFilter;
import org.springframework.security.oauth.provider.verifier.OAuthVerifierServices;
import org.springframework.security.oauth.provider.verifier.RandomValueVerifierServices;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.taoran.appdirect.authentication.OAuthProcessingFilterEntryPointImpl;
import com.taoran.appdirect.authentication.PreauthenticatedRequestTokenProcessingFilter;
import com.taoran.appdirect.domain.parser.AppDirectEventMessageParser;
import com.taoran.appdirect.domain.parser.AppDirectEventMessageParserImpl;
import com.taoran.appdirect.util.HttpHelper;
import com.taoran.appdirect.util.HttpHelperImpl;

@Configuration
@ImportResource("classpath*:spring/applicationContext-*.xml")
public class MainConfig {

  @Bean
  public ConsumerDetails consumerDetails() {
    BaseConsumerDetails consumerDetails = new BaseConsumerDetails();
    consumerDetails.setConsumerKey("mytestapp-168340");
    consumerDetails.setConsumerName("RanTaoTest");
    consumerDetails.setSignatureSecret(new SharedConsumerSecretImpl("odfsEhGBu9iWgTt1"));
    consumerDetails.setAuthorities(Lists.newArrayList(new SimpleGrantedAuthority("ROLE_USER")));
    consumerDetails.setRequiredToObtainAuthenticatedToken(true);
    return consumerDetails; 
  }
  
  @Bean
  public ConsumerDetailsService consumerDetailsService() {
    InMemoryConsumerDetailsService consumerDetailsService = new InMemoryConsumerDetailsService();
    ConsumerDetails consumerDetails = consumerDetails();
    Map<String, ConsumerDetails> consumerDetailsMap = Maps.newHashMap();
    consumerDetailsMap.put(consumerDetails.getConsumerKey(), consumerDetails);
    consumerDetailsService.setConsumerDetailsStore(consumerDetailsMap);
    return consumerDetailsService;
  }
  
  @Bean
  public OAuthAuthenticationHandler DefaultAuthenticationHandler() {
    return new DefaultAuthenticationHandler();
  }
  
  @Bean
  public OAuthProcessingFilterEntryPoint authenticationEntryPoint() {
    return new OAuthProcessingFilterEntryPointImpl();
  }
  
  @Bean
  public UnauthenticatedRequestTokenProcessingFilter authProviderProcessingFilter() {
    UnauthenticatedRequestTokenProcessingFilter filter = new PreauthenticatedRequestTokenProcessingFilter();
    filter.setRequire10a(false);
    filter.setFilterProcessesUrl("/subscription/request_token");
    filter.setAuthenticationEntryPoint(authenticationEntryPoint());
    filter.setConsumerDetailsService(consumerDetailsService());
    return filter;
  }
  
  @Bean
  public OAuthVerifierServices authVerifierServices() {
    return new RandomValueVerifierServices();
  }
  
  @Bean
  public HttpHelper httpHelper() {
    return new HttpHelperImpl();
  }
  
  @Bean
  public AppDirectEventMessageParser appDirectEventMessageParser() {
    return new AppDirectEventMessageParserImpl();
  }
  
}
