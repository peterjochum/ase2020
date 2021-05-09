package org.steambuddy.app;

import java.util.HashSet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SpringBootWebSecurityConfiguration;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.steambuddy.api.dto.GroupDTO;
import org.steambuddy.api.dto.UserDTO;
import org.steambuddy.app.config.BackendConfiguration;
import org.steambuddy.app.config.SecurityConfig;
import org.steambuddy.app.config.WebConfiguration;
import org.steambuddy.app.service.GameService;
import org.steambuddy.app.service.GameServiceImpl;

@SpringBootApplication
@ComponentScan(basePackages = {"org.steambuddy"})
//@EnableAutoConfiguration(exclude = {ObjectPostProcessor.class,SecurityFilterAutoConfiguration.class, SpringBootWebSecurityConfiguration.class})
@EnableJpaRepositories//("org.steambuddy")
@EnableWebSecurity
@Import(value = { BackendConfiguration.class, WebConfiguration.class, SecurityConfig.class })
public class SteambuddyApplication 
{
    public static void main(String[] args) {
       SpringApplication.run(SteambuddyApplication.class, args);
       
    }
}
