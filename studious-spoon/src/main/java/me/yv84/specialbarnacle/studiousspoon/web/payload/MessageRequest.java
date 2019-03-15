package me.yv84.specialbarnacle.studiousspoon.web.payload;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class MessageRequest {


    @NotBlank
    private Integer deviceId;
    
    @NotBlank
    private Integer status;

}
