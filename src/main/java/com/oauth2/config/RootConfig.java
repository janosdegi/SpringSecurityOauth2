package com.oauth2.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Dégi János on 2017.09.24..
 *
 * https://dzone.com/articles/spring-component-scan
 * https://stackoverflow.com/questions/16455348/autowired-dependency-injection-with-spring-security?rq=1
 *
 */
@Configuration
@ComponentScan(basePackages = {"com.oauth2.config","com.oauth2.security","com.oauth2.domain","com.oauth2.dao",
        "com.oauth2.service"}) //beens of root application context
public class RootConfig {


}
