package me.yv84.specialbarnacle.fluffyinvention.kafka;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Slf4j
@Configuration
public class KafkaRunner {



	@Bean
	public ApplicationRunner runner(Producer producer) {
		//log.warn("FluffyInventionApplication runner");
		return (args) -> {
			for(int i = 1; i < 20; i++) {
				//log.warn("A simple test message");
				producer.send(new SampleMessage(i, "A simple test message" + Integer.toString(i)));
			}
		};
	}

}