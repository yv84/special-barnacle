package me.v84.specialbarnacle.fluffyinvention;

import lombok.extern.slf4j.Slf4j;
import me.v84.specialbarnacle.fluffyinvention.kafka.Producer;
import me.v84.specialbarnacle.fluffyinvention.kafka.SampleMessage;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class FluffyInventionApplication {

	public static void main(String[] args) {
		SpringApplication.run(FluffyInventionApplication.class, args);
	}

	@Bean
	public ApplicationRunner runner(Producer producer) {
		log.warn("FluffyInventionApplication runner");
		return (args) -> {
			for(int i = 1; i < 20; i++) {
				log.warn("A simple test message");
				producer.send(new SampleMessage(i, "A simple test message"));
			}
		};
	}
}
