package me.yv84.specialbarnacle.studiousspoon.persistance.repository;


import me.yv84.specialbarnacle.studiousspoon.persistance.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>
{
    User findUserByUsername(String username);
    
    User findUserByEmail(String email);

    User findUserByUsernameAndPassword(String username, String givenPassword);
    
}
