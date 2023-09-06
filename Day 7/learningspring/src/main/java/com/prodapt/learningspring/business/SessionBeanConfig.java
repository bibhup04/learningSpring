package com.prodapt.learningspring.business;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

import com.prodapt.learningspring.business.LoggedInUser;

@Configuration
public class SessionBeanConfig {
    @Bean
    @Scope(
        value = WebApplicationContext.SCOPE_SESSION, 
        proxyMode = ScopedProxyMode.TARGET_CLASS
    )
    public LoggedInUser loggedInUser() {
        return new LoggedInUser();
    }
}

