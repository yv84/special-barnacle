package me.yv84.specialbarnacle.studiousspoon.web.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
@RequestMapping("/api/ping")
public class PingController {


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

