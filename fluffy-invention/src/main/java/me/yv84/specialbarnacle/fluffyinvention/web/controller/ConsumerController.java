package me.yv84.specialbarnacle.fluffyinvention.web.controller;


import lombok.extern.slf4j.Slf4j;
import me.yv84.specialbarnacle.fluffyinvention.kafka.Producer;
import me.yv84.specialbarnacle.fluffyinvention.kafka.SampleMessage;
import me.yv84.specialbarnacle.fluffyinvention.web.payload.MessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 *
 */
@Slf4j
@RestController
@RequestMapping("/api/kafka")
public class ConsumerController {
	
	@Autowired
	Producer kafkaProducer;



    @PreAuthorize( "hasRole('ROLE_CLUSTERADMINS')" )
    @RequestMapping(value = "/addmessage", method = RequestMethod.POST)
    public ResponseEntity<?> addMessage(@RequestBody MessageRequest message) {
        try {
            kafkaProducer.send(new SampleMessage(0, message.getMessage()));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return new ResponseEntity<>((Integer)null, HttpStatus.UNPROCESSABLE_ENTITY);
    }


}

