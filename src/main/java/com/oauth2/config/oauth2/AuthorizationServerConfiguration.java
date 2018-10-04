package com.oauth2.config.oauth2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

/**
 * Created by Dégi János on 2018.03.14..
 *
 * http://websystique.com/spring-security/secure-spring-rest-api-using-oauth2/
 *
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    public static final String RESOURCE_ID = "rest_api";

    // 1 authenticationManager
    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthorizationServerConfiguration() {
        super();
    }

    // beans

//    public TokenStore tokenStore() {
//        return new InMemoryTokenStore();
//    };

    @Autowired
	private DataSource dataSource;

    public JdbcTokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }






    // config

    public void configure(final AuthorizationServerSecurityConfigurer securityConfigurer) {
        securityConfigurer.checkTokenAccess("isAuthenticated()");
    }

    public void configure(final AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.tokenStore(tokenStore()).authenticationManager(authenticationManager);
    }

    public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {

        clients.jdbc(dataSource);

//        clients.inMemory()
//                .withClient("live-test")
//                .secret("A65DG9322K")
//                .authorizedGrantTypes("password")
//                .scopes("webapp")
//                .autoApprove("webapp")
//                .refreshTokenValiditySeconds(600)
//                .accessTokenValiditySeconds(600);
    }
}
