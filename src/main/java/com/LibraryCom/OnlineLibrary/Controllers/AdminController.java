package com.LibraryCom.OnlineLibrary.Controllers;

import com.LibraryCom.OnlineLibrary.Models.User;
import com.LibraryCom.OnlineLibrary.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/panel")
    public String adminPage(Model model, @RequestParam(name = "inputToValidate", required = false) Long phoneNumber){
        List<User> userList = userRepo.findAll();

        if(phoneNumber != null){
            List<User> usersWithNumber = userList.stream().filter((user) -> user.getPhoneNumber().equals(phoneNumber)).toList();
            model.addAttribute("Users",usersWithNumber);
        }else{
            model.addAttribute("Users", userList);
        }

        return "adminPage";
    }
}
