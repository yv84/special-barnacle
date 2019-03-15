package me.yv84.specialbarnacle.studiousspoon.persistance.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import me.yv84.specialbarnacle.studiousspoon.persistance.entity.Device;

import java.util.List;
import java.util.Optional;

public interface DeviceRepository extends JpaRepository<Device, Integer> {
    
    //@Query("select device from Device device where device.name = :name")
    //Optional<Device> findByName(@Param("name") String name);
    
    Optional<Device> findByName(@Param("name") String name);
    
}
