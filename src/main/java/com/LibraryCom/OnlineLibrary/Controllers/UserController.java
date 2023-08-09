package com.LibraryCom.OnlineLibrary.Controllers;

import com.LibraryCom.OnlineLibrary.Models.User;
import com.LibraryCom.OnlineLibrary.Repositories.UserRepo;
import com.LibraryCom.OnlineLibrary.Services.userServices.UserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class UserController {
    //Required variables
    private final UserService userService;

    @GetMapping("/registration")
    public String registrationPage(Principal principal, Model model){
        User user = userService.findUserByPrincipal(principal);

        model.addAttribute("user",user);
        return "registration";
    }
    //Post registration
    @PostMapping("/registration")
    public String registrationPost(User user,Model model){

        if(!userService.createUser(user)){
            model.addAttribute("errorMessage","Користувач із такою " +
                    "електроною скринькою вже існує");
            model.addAttribute("classError","alert alert-danger");
            return "registration";
        }

        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage(Model model,Principal principal){
        return "login";
    }

    //User homePage
    @GetMapping("/homepage")
    public String UserHomePage(Model model,Principal principal){
        return "homePage";
    }
}
