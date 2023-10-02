package com.LibraryCom.OnlineLibrary.Controllers;

import com.LibraryCom.OnlineLibrary.FunctionalClasses.TokensType;
import com.LibraryCom.OnlineLibrary.Models.Token;
import com.LibraryCom.OnlineLibrary.Models.User;
import com.LibraryCom.OnlineLibrary.Repositories.UserRepo;
import com.LibraryCom.OnlineLibrary.Services.MailSenderService.MailSenderService;
import com.LibraryCom.OnlineLibrary.Services.userServices.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class UserController {
    //Required variables
    private final UserService userService;
    private final MailSenderService mailSenderService;
    private final UserRepo userRepo;

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
        model.addAttribute("books", userService.getTakenBooks(principal));
        return "homePage";
    }


    //-------------ACTIVATION SYSTEM SECTION-----------------------
    @GetMapping("/sendActivationsMessage")
    public String sendActivationMessage(Model model,Principal principal){
       User user = userService.findUserByPrincipal(principal);

        //Sending message
        //Getting token
        String token = "http://localhost:8080/confirmAccount?token="+ userService.getTokenByUser(user,TokensType.CONFIRM_ACCOUNT).getToken();


        String text = "Будь-ласка перейдіть по цьому посиланню для активації вашого облікового запису " +
                "на OnlineLibrary.com \n + " +
                "Посилання: " + token;

        //Запускаємо потік , щоб користувач не чекав на сайті запуску email
        Runnable sendingThread = () -> mailSenderService.sendMailMessage(user.getEmail(),text);
        Thread t = new Thread(sendingThread,"SendingMail");
        t.start();

        //Set attribute
        model.addAttribute("userEmail",user.getEmail());

        return "activationPage";
    }

    @GetMapping("/confirmAccount")
    public String confirmAccount(@RequestParam(name = "token") String token){
        Token userToken = userService.getTokenByToken(token, TokensType.CONFIRM_ACCOUNT);
        if(userToken != null){
            User user = userToken.getUser();
            //Set true and save user
            user.setActive(true);
            userRepo.save(user);

            //Delete Token
            userService.deleteTokenAfterActivation(userToken);
        }else{
            //... Something
        }

        return "redirect:/homepage";
    }
}
