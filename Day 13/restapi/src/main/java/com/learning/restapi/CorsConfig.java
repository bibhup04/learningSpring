package com.learning.restapi;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

@Configuration
public class CorsConfig
//  extends WebMvcConfigurerAdapter
 {

//     @Override
//     public void addCorsMappings(CorsRegistry registry) {
//         registry.addMapping("/api/**")
//             .allowedOrigins("http://localhost:4200")
//             .allowedMethods("GET", "POST", "PUT", "DELETE");
//     }
    
 }
