package com.oauth2.service;

import org.springframework.security.access.annotation.Secured;

/**
 * Created by Dégi János on 2018.02.27..
 */
public class RunAsService implements IRunAsService {

    @Secured("ROLE_RUN_AS_REPORTER")
    public String runAsServiceTest() {
        return "runAsServiceTest";
    }

}
