package com.LibraryCom.OnlineLibrary.Services.userServices;

import com.LibraryCom.OnlineLibrary.Models.User;
import com.LibraryCom.OnlineLibrary.Models.enums.Role;
import com.LibraryCom.OnlineLibrary.Repositories.UserRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@Slf4j
@AllArgsConstructor
public class UserService {
    //Requred variables
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    public void createUser(User user){
        user.setActive(false);
<<<<<<< HEAD
        user.getRoleSet().add(Role.USER_ROLE);
=======
>>>>>>> b061dc22030ebb4867d59e66480a7d1405564a4f
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);

        log.info("-- New user created - id:{}, name: {}, email : {}",user.getId(),user.getUsername(),user.getEmail());
    }

    public User findUserByPrincipal(Principal principal){
        if(principal != null) return userRepo.findUserByEmail(principal.getName());
        return new User();
    }

}
