package me.yv84.specialbarnacle.fluffyinvention.web.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import me.yv84.specialbarnacle.fluffyinvention.kafka.Producer;
import me.yv84.specialbarnacle.fluffyinvention.kafka.SampleMessage;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

/**
 *
 */
@Slf4j
@RestController
@RequestMapping("/api/ping")
public class PingController {
	
	//@Autowired
	//Producer kafkaProducer;


    @GetMapping("/ping")
    public ResponseEntity<?> ping() {
        return ResponseEntity.ok("pong");
    }
	

    @GetMapping("/ping2")
    public ResponseEntity<?> ping2() {
    	for(int i = 1; i < 20; i++) {
			//log.warn("A simple test message");
    		//kafkaProducer.send(new SampleMessage(i, "A simple test message"));
		}
        return ResponseEntity.ok("pong");
    }


    @PreAuthorize("hasRole('ROLE_CLUSTERADMINS')" )
    @GetMapping("/ping-auth")
    public ResponseEntity<?> pingAuth() {
        return ResponseEntity.ok("pong");
    }

}

