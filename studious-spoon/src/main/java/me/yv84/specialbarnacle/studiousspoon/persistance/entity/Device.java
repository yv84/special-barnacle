package me.yv84.specialbarnacle.studiousspoon.persistance.entity;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


@Entity
@Data
@Getter
@Setter
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Device extends BaseEntity {

    private static final long serialVersionUID = 617194060366284560L;

    @Column
    private String name;

    @Column
    private String address;
    
    @Column
    private String description;
    

    @JsonBackReference(value="device_status")
    @OneToMany(mappedBy = "device", fetch=FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    private List<DeviceStatus> deviceStatusList = new ArrayList<>();
    
    public void addDevice(DeviceStatus deviceStatus) {
        deviceStatusList.add(deviceStatus);
        deviceStatus.setDevice(this);
    }
    
    public void removeDevice(DeviceStatus deviceStatus) {
        deviceStatusList.remove(deviceStatus);
        deviceStatus.setDevice(null);
    }
    

}
