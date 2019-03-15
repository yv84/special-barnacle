package me.yv84.specialbarnacle.studiousspoon.web.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import me.yv84.specialbarnacle.studiousspoon.persistance.entity.Device;
import me.yv84.specialbarnacle.studiousspoon.persistance.repository.DeviceRepository;
import me.yv84.specialbarnacle.studiousspoon.web.payload.MessageRequest;
import me.yv84.specialbarnacle.studiousspoon.web.service.DeviceService;
import me.yv84.specialbarnacle.studiousspoon.web.service.DeviceStatusService;

/**
 *
 */
@Slf4j
@RestController
@RequestMapping("/api/device")
public class ConsumerController {
    

    @Autowired
    private DeviceService deviceService;
    
    @Autowired
    private DeviceStatusService deviceStatusService;


    @RequestMapping(value = "/device", method = RequestMethod.POST)
    public ResponseEntity<?> getDevice(@RequestBody MessageRequest message) {
        try {
            List<Device> result = deviceService.findAll();
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return new ResponseEntity<>((Integer)null, HttpStatus.UNPROCESSABLE_ENTITY);
    }
    

    @RequestMapping(value = "/addstatus", method = RequestMethod.POST)
    public ResponseEntity<?> addStatus(@RequestBody MessageRequest message) {
        try {
            Integer result = deviceStatusService.addStatus(message);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return new ResponseEntity<>((Integer)null, HttpStatus.UNPROCESSABLE_ENTITY);
    }



}

