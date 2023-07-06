package com.LibraryCom.OnlineLibrary.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/blog")
public class BlogController {

    //Required variables
    private static final Logger logger = LoggerFactory.getLogger(BlogController.class);

    //Controllers
    @GetMapping("/")
    public String blogPage(Model model){

        attributes :{
            model.addAttribute("isAdmin",true);
        }

        return "blogPage";
    }
    @GetMapping("/addBlog")
    public String addBlogPage(){
        return "addBlogPage";
    }
}
