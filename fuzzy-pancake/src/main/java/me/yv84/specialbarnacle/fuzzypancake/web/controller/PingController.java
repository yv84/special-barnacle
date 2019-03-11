package me.yv84.specialbarnacle.fuzzypancake.web.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


    @PreAuthorize("hasRole('ROLE_CLUSTERADMINS')" )
    @GetMapping("/ping-auth")
    public ResponseEntity<?> pingAuth() {
        return ResponseEntity.ok("pong");
    }

}

