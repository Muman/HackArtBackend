package com.hack.art.configuration;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * Created by ROLO on 28.11.2015.
 */
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer{

    public SecurityWebApplicationInitializer(){
        super(SpringSecurityConfig.class);
    }
}
