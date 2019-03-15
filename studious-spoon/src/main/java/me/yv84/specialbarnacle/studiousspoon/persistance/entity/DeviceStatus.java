package me.yv84.specialbarnacle.studiousspoon.persistance.entity;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Data
@Getter
@Setter
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

public class DeviceStatus extends BaseEntity {

    private static final long serialVersionUID = 8133821234949391962L;

    @Column
    private Integer status;
    
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.ALL})
    private Device device;
}
