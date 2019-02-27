package me.yv84.specialbarnacle.studiousspoon.persistance.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

@Slf4j
@Entity
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "User",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "EMAIL"),
                @UniqueConstraint(columnNames = "USERNAME") })
@AttributeOverride(name = "id", column = @Column(name = "UserId"))
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User extends BaseEntity implements UserDetails {

    private static final long serialVersionUID = -5361594574898417624L;

    @Size(min = 5, max = 50)
    @Pattern(regexp = "^[a-z0-9]*$", message = "Only small letters and numbers allowed")
    @Column(name = "USERNAME", unique = true, nullable = false, length = 50)
    private String username;

    @Size(min = 6, max = 100)
    @Pattern(regexp=".+@.+\\..+", message="Please provide a valid email address")
    @Column(name = "EMAIL", unique = true, nullable = false, length = 100)
    private String email;

    @Size(min = 6, max = 50)
    @Column(name = "USERPASSWORD", nullable = false, length = 50)
    private String password;

    @Column(name = "DATECREATE", nullable = false)
    @CreationTimestamp
    private Date datecreate;

    @Column(name = "DATEUPDATE", nullable = false)
    @UpdateTimestamp
    private Date updateDateTime;

    @Column(name = "DATEBLOCK", nullable = true)
    private Date dateblock;

    @Column(name = "ISBLOCKED", nullable = false)
    private int isblocked = 0;

    @Size(max = 100)
    @Column(name = "ESP", unique = false, nullable = false, length = 100)
    private String esp;




    public User()
    {
        super();
    }

    transient private String salt = "";

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true; // Not Implemented
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true; // Not Implemented
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isEnabled() {
        return isblocked == 0;
    }


    @Override
    public String toString() {
        return org.apache.commons.lang3.builder.ReflectionToStringBuilder.toString(this);
    }


}
