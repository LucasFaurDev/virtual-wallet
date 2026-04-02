package com.Lucas.virtual_wallet_server.configuration;

import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;

@Configuration
public class WebSecurityConfig {

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (WebSecurity web) -> web.ignoring()
                .requestMatchers(
                        "/",
                        "/index.html",
                        "/**/*.js",
                        "/**/*.css",
                        "/**/*.png",
                        "/**/*.jpg",
                        "/**/*.ico",
                        "/assets/**"
                );
    }
}
