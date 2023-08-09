package com.LibraryCom.OnlineLibrary.Configurations.aop;

import com.LibraryCom.OnlineLibrary.Models.User;
import com.LibraryCom.OnlineLibrary.Models.enums.Role;
import com.LibraryCom.OnlineLibrary.Services.userServices.UserService;
import lombok.AllArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.security.Principal;

@Aspect
@Component
@AllArgsConstructor
public class ControllersAop {
    private final UserService userService;

    @Pointcut("@annotation(org.springframework.web.bind.annotation.GetMapping)")
    private void getMappingMethods() {}



    @Before("getMappingMethods() && args(.. ,model, principal)")
    public void beforeGetMappingAdvice(JoinPoint joinPoint, Model model, Principal principal) {
        User user = userService.findUserByPrincipal(principal);
        if (user != null) {
            System.out.println(user.getEmail());
            model.addAttribute("user", user);
            model.addAttribute("isAdmin", user.getRoleSet().contains(Role.ADMIN_ROLE));
        }
    }
}