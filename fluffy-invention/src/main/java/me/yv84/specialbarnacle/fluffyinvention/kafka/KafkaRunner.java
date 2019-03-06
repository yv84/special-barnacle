package me.yv84.specialbarnacle.fluffyinvention.kafka;



import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import lombok.extern.slf4j.Slf4j;
import me.yv84.specialbarnacle.fluffyinvention.kafka.Producer;
import me.yv84.specialbarnacle.fluffyinvention.kafka.SampleMessage;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import lombok.extern.slf4j.Slf4j;


import me.yv84.specialbarnacle.fluffyinvention.kafka.Producer;
import me.yv84.specialbarnacle.fluffyinvention.kafka.SampleMessage;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import java.util.HashMap;
import java.util.Map;


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