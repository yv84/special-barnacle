package me.yv84.specialbarnacle.studiousspoon.web.config.security;


import lombok.RequiredArgsConstructor;
import me.yv84.specialbarnacle.studiousspoon.persistance.entity.User;
import me.yv84.specialbarnacle.studiousspoon.web.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Objects.isNull;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService
{
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String userName)
    {
        User user = userService.findUserByUsername(userName);
        if (isNull(user))
        {
            throw new UsernameNotFoundException("UserName " + userName + " not found");
        }
        return user;
    }


}
