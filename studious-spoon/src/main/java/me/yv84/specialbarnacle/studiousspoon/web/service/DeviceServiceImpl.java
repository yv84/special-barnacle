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

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService {
    
    @Autowired
    private DeviceRepository deviceRepository;
    
    @Override
    public List<Device> findAll() {
        return deviceRepository.findAll();
    }

    @Override
    public Optional<Device> findById(Integer id) {
        Optional<Device> device = deviceRepository.findById(id);
        return device;
    }

    @Override
    public Optional<Device> findByName(String name) {
        Optional<Device> device = deviceRepository.findByName(name);
        return device;
    }

    @Override
    public Integer addDevice(Device d) {
        Device device = new Device();
        device.setName(d.getName());
        device.setAddress(d.getAddress());
        device.setDescription(d.getDescription());
        return deviceRepository.save(device).getId();
    }

    @Override
    public void deleteDevice(Integer id) {
        deviceRepository.deleteById(id);
    }
    
    @Override
    public Integer updateDevice(Device d) {
        Device device = findById(d.getId()).get();
        device.setName(d.getName());
        device.setAddress(d.getAddress());
        device.setDescription(d.getDescription());
        return deviceRepository.save(device).getId();
    }
    

    @Override
    public Integer patchDevice(Device d) {
        Device device = findById(d.getId()).get();
        if (device == null) {
            return -1;
        }
        if (d.getName() != null) {
            device.setName(d.getName());
        }
        if (d.getAddress() != null) {
            device.setAddress(d.getAddress());
        }
        if (d.getDescription() != null) {
            device.setDescription(d.getDescription());
        }
        return deviceRepository.save(device).getId();
    }



}
