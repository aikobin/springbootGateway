package com.bin.gateway;

import com.bin.gateway.filter.AccessFilter;
import com.bin.gateway.filter.ErrorFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.bin.gateway.filter.LogFilter;
import com.bin.gateway.filter.PreRequestLogFilter;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @author Thibaud Leprêtre
 */
@SpringBootApplication
@EnableZuulProxy
@EnableDiscoveryClient
public class ApiGatewayApplication {

    public static void main(String[] args) {
    	// Properties systemProps = System.getProperties();  
   	     //请将项目目录下的truststore放入c盘(写相对路径时会出错，只能写成系统绝对路径)
        // systemProps.put( "javax.net.ssl.trustStore", "C:\\truststore.jks");    
        //systemProps.put( "javax.net.ssl.trustStorePassword", "123456");  
      // System.setProperties(systemProps);  
         
        SpringApplication.run(ApiGatewayApplication.class, args);
        
    }

    
/*    @Bean
    public PreRequestLogFilter preRequestLogFilter() {
    	return new PreRequestLogFilter();
    }*/
    @Bean
    public LogFilter logFilter(){
    	return new LogFilter();
    }

    @Bean
    public ErrorFilter errorFilter(){return new ErrorFilter();}
   /* @Bean
    public AccessFilter accessFilter(){
        return new AccessFilter();
    }*/
    
    
  /*  @Bean
    UserInfoRestTemplateCustomizer userInfoRestTemplateCustomizer(SpringClientFactory springClientFactory) {
        return template -> {
            AccessTokenProviderChain accessTokenProviderChain = Stream
                    .of(
                            new AuthorizationCodeAccessTokenProvider(),
                            new ImplicitAccessTokenProvider(),
                            new ResourceOwnerPasswordAccessTokenProvider(),
                            new ClientCredentialsAccessTokenProvider())
                    .peek(tp -> tp.setRequestFactory(new RibbonClientHttpRequestFactory(springClientFactory)))
                    .collect(Collectors.collectingAndThen(Collectors.toList(), AccessTokenProviderChain::new));
            template.setAccessTokenProvider(accessTokenProviderChain);
        };
    }*/

}
