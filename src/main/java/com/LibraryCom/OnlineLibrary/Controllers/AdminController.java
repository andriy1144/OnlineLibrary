package com.LibraryCom.OnlineLibrary.Controllers;

import com.LibraryCom.OnlineLibrary.Models.User;
import com.LibraryCom.OnlineLibrary.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

@Controller
public class AdminController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/adminPanel")
    public String adminPage(Model model){
        List<User> userList = userRepo.findAll();

        model.addAttribute("Users", userList);

        return "adminPage";
    }
}
