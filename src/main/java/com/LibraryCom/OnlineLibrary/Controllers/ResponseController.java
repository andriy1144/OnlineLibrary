package com.LibraryCom.OnlineLibrary.Controllers;

import com.LibraryCom.OnlineLibrary.Models.ResponcesEntities.LibraryResponse;
import com.LibraryCom.OnlineLibrary.Models.User;
import com.LibraryCom.OnlineLibrary.Services.ResponseService;
import com.LibraryCom.OnlineLibrary.Services.userServices.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class ResponseController {

    //Rquired Variables
    private final UserService userService;
    private final ResponseService responseService;

    //Mappings

    //Library responses
    @GetMapping("/libraryRespones")
    public String libraryResponse(Model model, Principal principal){
        model.addAttribute("Responces", responseService.getAllResponces());
        return "libraryResponsesPage";
    }

    @PostMapping("/addLibraryResponse")
    public String saveResponce(LibraryResponse libraryResponse, Model model, Principal principal){
        User user = userService.findUserByPrincipal(principal);

        if(responseService.saveResponse(libraryResponse,user)){
            model.addAttribute("Message","Ваш відгку був успішно надісланий!");
            model.addAttribute("class","alert alert-success");
        }else{
            model.addAttribute("Message","Ваш відгук не був надісланий оскільки ви вже надсилали один!");
            model.addAttribute("class","alert alert-danger");
        }

        model.addAttribute("Responces", responseService.getAllResponces());
        model.addAttribute("user",user);

        return "libraryResponsesPage";
    }
}
