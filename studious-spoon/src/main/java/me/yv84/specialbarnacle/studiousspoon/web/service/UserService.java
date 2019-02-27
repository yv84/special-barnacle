package me.yv84.specialbarnacle.studiousspoon.web.service;

import me.yv84.specialbarnacle.studiousspoon.persistance.entity.User;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface UserService
{

    User findUserByUsername(String username);

    User findUserByEmail(String email);

    @PreAuthorize("hasAuthority('ROLE_Z') or hasAuthority('ROLE_R_управлениепользователями_пользователи_добавить')")
    List<User> findAllUsers();

    User saveUser(User user);

    User findUserByUsernameAndPassword(String username, String givenPassword);
    
    User getUserDetails();
    

}
