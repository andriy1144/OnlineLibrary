package com.LibraryCom.OnlineLibrary.Services.userServices;

import com.LibraryCom.OnlineLibrary.FunctionalClasses.TokensType;
import com.LibraryCom.OnlineLibrary.Models.Book;
import com.LibraryCom.OnlineLibrary.Models.Token;
import com.LibraryCom.OnlineLibrary.Models.User;
import com.LibraryCom.OnlineLibrary.Models.enums.Role;
import com.LibraryCom.OnlineLibrary.Repositories.TokenRepo;
import com.LibraryCom.OnlineLibrary.Repositories.UserRepo;
import lombok.AllArgsConstructor;
import lombok.experimental.Tolerate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Set;

@Service
@Slf4j
@AllArgsConstructor
public class UserService {

    //Requred variables
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepo tokenRepo;

    public boolean createUser(User user){
        //Check if Such user already exists
        User checkUser = userRepo.findUserByEmail(user.getEmail());
        if(checkUser != null){
            log.warn("--Error creating user: Already exists --");
            return false;
        }

        user.setActive(false);


        //Just to chekc if it the first user
        if(userRepo.findAll().size() == 0){
            //So it will be the ADMIN
            user.getRoleSet().add(Role.ADMIN_ROLE);
        }else{
            user.getRoleSet().add(Role.USER_ROLE);
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);

        //Creating token
        Token token = new Token(user, TokensType.CONFIRM_ACCOUNT);

        tokenRepo.save(token);
        log.info("--New token created : {} for user with id : {}",token.getToken(),user.getId());

        log.info("-- New user created - id:{}, email : {}",user.getId(),user.getEmail());
        return true;
    }

    public User findUserByPrincipal(Principal principal){
        if(principal != null) return userRepo.findUserByEmail(principal.getName());
        return new User();
    }

    public Set<Book> getTakenBooks(Principal principal){
        User user = findUserByPrincipal(principal);
        return user.getTakenBooks();
    }

    //-----------------USER ACTIVATION PART----------------
    public Token getTokenByUser(User user,TokensType type){
        Token token =  tokenRepo.findTokenByUserAndTokensType(user,type);
        System.out.println(token);
        return token;
    }
    public Token getTokenByToken(String token,TokensType type){
        return tokenRepo.findTokenByTokenAndTokensType(token,type);
    }

    public void deleteTokenAfterActivation(Token token){
        tokenRepo.delete(token);
        log.info("--User has been succesfully authorised--");
    }
}
