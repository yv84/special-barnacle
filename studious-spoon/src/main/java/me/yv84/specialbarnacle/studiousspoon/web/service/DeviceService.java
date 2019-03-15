package me.yv84.specialbarnacle.studiousspoon.web.service;



import java.util.List;
import java.util.Optional;

import me.yv84.specialbarnacle.studiousspoon.persistance.entity.Device;

public interface DeviceService {

    List<Device> findAll();

    Optional<Device> findById(Integer id);

    Optional<Device> findByName(String name);

    Integer addDevice(Device d);

    void deleteDevice(Integer id);

    Integer updateDevice(Device d);

    Integer patchDevice(Device d);
    
}
