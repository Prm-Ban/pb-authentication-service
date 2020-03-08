package com.sunwell.authentication.config;

import javax.sql.DataSource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

//@Profile("production")
@Configuration
@EnableAuthorizationServer
//@Order(0)
public class AuthServerConfig  extends AuthorizationServerConfigurerAdapter {
	
	@Autowired
	DataSource datasource;
	
	@Autowired
	private Environment env;
	
//	@Autowired
//    private UserApprovalHandler userApprovalHandler;
  
    @Autowired
    @Qualifier("authenticationManagerBean")
    private AuthenticationManager authenticationManager;
 
    @Override
    public void configure(
      AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
    	
    	System.out.println("!!!!!! CON !!!!!!!: " + env.getProperty("spring.datasource.url"));
    	
        oauthServer
          .tokenKeyAccess("permitAll()")
//          .checkTokenAccess("permitAll()");
          .checkTokenAccess("isAuthenticated()");
    }
 
    @Override
    public void configure(ClientDetailsServiceConfigurer clients)
      throws Exception {
    	System.out.println("!!!!!!!!!!!!!!!!!!!! C O N F I G  CALLED");;
        clients
        	.jdbc(datasource);
//        	.passwordEncoder(NoOpPasswordEncoder.getInstance())
//          .withClient("sampleClientId")
//          .authorizedGrantTypes("implicit")
//          .scopes("read")
//          .autoApprove(true)
//          .and()
//          .withClient("clientIdPassword")
//          .secret("{noop}secret")
//          .authorizedGrantTypes(
//            "password","authorization_code", "refresh_token")
//          .scopes("foo", "read", "write")
//          .accessTokenValiditySeconds(3600) // 1 hour
//          .refreshTokenValiditySeconds(2592000); // 30 days
          
//    	clients.inMemory()
//        .withClient("sampleClientId")
//        .authorizedGrantTypes("implicit")
//        .scopes("read", "write", "foo", "bar")
//        .autoApprove(false).accessTokenValiditySeconds(3600)
//
//        .and()
//        .withClient("fooClientIdPassword")
//        .secret("{noop}secret")
//        .authorizedGrantTypes("password", "authorization_code", "refresh_token")
//        .scopes("foo", "read", "write")
//        .accessTokenValiditySeconds(3600) // 1 hour
//        .refreshTokenValiditySeconds(2592000) // 30 days
//
//        .and()
//        .withClient("barClientIdPassword")
//        .secret("{noop}secret")
//        .authorizedGrantTypes("password", "authorization_code", "refresh_token")
//        .scopes("bar", "read", "write")
//        .accessTokenValiditySeconds(3600) // 1 hour
//        .refreshTokenValiditySeconds(2592000) // 30 days
	  ;
        
    }
 
    @Override
    public void configure(
      AuthorizationServerEndpointsConfigurer endpoints)
      throws Exception {
    	
    	System.out.println("IS AUTHEN MANAGER NULL = " + (authenticationManager == null ? "null" : "not null"));
        endpoints
          .tokenStore(tokenStore())
//          .userApprovalHandler(userApprovalHandler)
          .authenticationManager(authenticationManager);
    }
 
    @Bean
    public TokenStore tokenStore() {
        return new JdbcTokenStore(datasource);
    }
}
