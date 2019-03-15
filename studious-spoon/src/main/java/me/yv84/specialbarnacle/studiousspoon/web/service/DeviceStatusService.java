package me.yv84.specialbarnacle.studiousspoon.web.service;



import java.util.List;
import java.util.Optional;

import me.yv84.specialbarnacle.studiousspoon.persistance.entity.Device;
import me.yv84.specialbarnacle.studiousspoon.web.payload.MessageRequest;

public interface DeviceStatusService {
    
    Integer addStatus(MessageRequest message);
    
}
