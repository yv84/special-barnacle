package me.yv84.specialbarnacle.studiousspoon.web.service;

import me.yv84.specialbarnacle.studiousspoon.persistance.entity.User;
import me.yv84.specialbarnacle.studiousspoon.persistance.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService
{

    private final UserRepository userRepository;


    @Override
    public User findUserByUsername(String username)
    {
        return userRepository.findUserByUsername(username);
    }

    @Override
    public User findUserByEmail(String email)
    {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User saveUser(User user)
    {
        return userRepository.save(user);
    }

    @Override
    public User findUserByUsernameAndPassword(String username, String givenPassword)
    {
        return userRepository.findUserByUsernameAndPassword(username, givenPassword);
    }

	@Override
	public User getUserDetails() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User userDetails = null;
        if (principal instanceof me.yv84.specialbarnacle.studiousspoon.persistance.entity.User) {
        	userDetails = (User) principal;
        }
        return userDetails;
	}
}
