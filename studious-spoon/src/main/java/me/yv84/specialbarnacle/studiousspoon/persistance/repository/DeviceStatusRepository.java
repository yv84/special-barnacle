package me.yv84.specialbarnacle.studiousspoon.persistance.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import me.yv84.specialbarnacle.studiousspoon.persistance.entity.Device;

import java.util.List;
import java.util.Optional;

public interface DeviceStatusRepository extends JpaRepository<Device, Integer> {
    
    @Query("select device from Device device left join device.deviceStatusList deviceStatus"
            + " where device = (SELECT device, MAX(status.created) FROM DeviceStatus status group by device)"
            + " and device.name IN (:id)")
    List<Device> findCurrentStatusByIds(@Param("id") List<Integer> id);
    
}
