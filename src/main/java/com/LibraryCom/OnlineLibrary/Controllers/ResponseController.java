package com.LibraryCom.OnlineLibrary.Controllers;

import com.LibraryCom.OnlineLibrary.Models.User;
import com.LibraryCom.OnlineLibrary.Services.userServices.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class ResponseController {

    //Rquired Variables
    private final UserService userService;

    //Mappings

    //Library responses
    @GetMapping("/libraryRespones")
    public String libraryResponse(Model model, Principal principal){
        User user = userService.findUserByPrincipal(principal);

        model.addAttribute("user",user);

        return "libraryResponsesPage";
    }
}
