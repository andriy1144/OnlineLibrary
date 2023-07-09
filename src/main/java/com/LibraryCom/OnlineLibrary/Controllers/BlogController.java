package com.LibraryCom.OnlineLibrary.Controllers;

import com.LibraryCom.OnlineLibrary.Models.Posts;
import com.LibraryCom.OnlineLibrary.Repositories.PostsRepo;
import com.LibraryCom.OnlineLibrary.Services.PostService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/blog")
@AllArgsConstructor
public class BlogController {

    //Required variables
    private static final Logger logger = LoggerFactory.getLogger(BlogController.class);
    private final PostService postService;
    private final PostsRepo postsRepo;


    //Controllers
    @GetMapping("/")
    public String blogPage(Model model){

        attributes :{
            model.addAttribute("isAdmin",true);
            model.addAttribute("Posts",postsRepo.findAll());
        }

        return "blogPage";
    }
    @GetMapping("/addBlog")
    public String addBlogPage(){
        return "addBlogPage";
    }

    @PostMapping("/savePost")
    public String postAddBlogPage(Posts posts, @RequestParam(name = "files") MultipartFile[] files){
        try {
            postService.savePost(posts, files);
        }catch(Exception e){
            logger.error("--Error saving post: Error : {} --",e);
            //Something back
        }
        return "redirect:/";
    }
}
