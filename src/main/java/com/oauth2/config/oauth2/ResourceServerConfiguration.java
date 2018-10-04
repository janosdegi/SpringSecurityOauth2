package com.oauth2.config.oauth2;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;

/**
 * Created by Dégi János on 2018.03.15..
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    public ResourceServerConfiguration() {
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

//        http.authorizeRequests()
////                .antMatchers("/").permitAll()
//                .antMatchers("/user","/testurl").authenticated();
//                .anyRequest().authenticated();
//                .anyRequest().permitAll();
        http.authorizeRequests()
//                .requestMatcher(new OrRequestMatcher(
//                        new AntPathRequestMatcher("/user/registration"),
//                        new AntPathRequestMatcher("/oauth/protected/resource")
//                ))
                .antMatchers("/user/registration2").permitAll()
                .anyRequest().authenticated();

    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(AuthorizationServerConfiguration.RESOURCE_ID);
    }

}
