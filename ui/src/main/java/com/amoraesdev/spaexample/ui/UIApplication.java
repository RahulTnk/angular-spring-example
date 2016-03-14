package com.amoraesdev.spaexample.ui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.security.oauth2.sso.EnableOAuth2Sso;

/**
 * Main UI Application class
 * @author Alessandro Moraes alessandro(at)amoraesdev.com
 */
@SpringBootApplication
@EnableOAuth2Sso
@EnableZuulProxy
public class UIApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(UIApplication.class, args);
    }
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }

    private static Class<UIApplication> applicationClass = UIApplication.class;
}
