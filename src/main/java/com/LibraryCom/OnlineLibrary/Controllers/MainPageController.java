package com.LibraryCom.OnlineLibrary.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPageController {

    // Required variables
    private static final Logger logger = LoggerFactory.getLogger(MainPageController.class);

    //Main page controller
    @GetMapping("/")
    public String mainPage(Model model){
        attributes : {
            model.addAttribute("isAdmin", true);
        }
        return "mainPage";
    }

    @GetMapping("/about")
    public String aboutPage() {
        return "aboutPage";
    }
}
