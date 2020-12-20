package org.steambuddy.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.steambuddy.app.config.BackendConfiguration;

@SpringBootApplication
@Import(value = { BackendConfiguration.class })
public class SteambuddyApplication 
{
    public static void main(String[] args) {
        SpringApplication.run(SteambuddyApplication.class, args);
    }
}
