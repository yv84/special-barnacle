package me.yv84.specialbarnacle.studiousspoon.web.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class InitConfig {

    @Bean
    public CommandLineRunner init() {

        return new CommandLineRunner() {
            @Override
            public void run(String... strings) throws Exception {
                System.out.println("The project started");
            }
        };
    }

}
