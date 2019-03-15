package me.yv84.specialbarnacle.studiousspoon.web.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.yv84.specialbarnacle.studiousspoon.persistance.entity.Device;
import me.yv84.specialbarnacle.studiousspoon.persistance.repository.DeviceRepository;
import me.yv84.specialbarnacle.studiousspoon.persistance.repository.DeviceStatusRepository;
import me.yv84.specialbarnacle.studiousspoon.web.payload.MessageRequest;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class DeviceStatusServiceImpl implements DeviceStatusService {
    
    @Autowired
    private DeviceStatusRepository deviceStatusRepository;
    
    @Autowired
    private DeviceService deviceService;
    
    @Override
    public Integer addStatus(MessageRequest message) {
        Optional<Device> device = deviceService.findById(message.getDeviceId());
        if (device.isPresent()) {
            
        }
        
        return -1;
    }



}
