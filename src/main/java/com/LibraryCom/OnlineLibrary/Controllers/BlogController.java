package com.LibraryCom.OnlineLibrary.Controllers;

import com.LibraryCom.OnlineLibrary.Models.Posts;
import com.LibraryCom.OnlineLibrary.Models.User;
import com.LibraryCom.OnlineLibrary.Models.enums.Role;
import com.LibraryCom.OnlineLibrary.Repositories.PostsRepo;
import com.LibraryCom.OnlineLibrary.Services.PostService;
import com.LibraryCom.OnlineLibrary.Services.userServices.UserService;
import lombok.AllArgsConstructor;
import org.hibernate.mapping.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.channels.Pipe;
import java.security.Principal;

@Controller
@RequestMapping("/blog")
@AllArgsConstructor
public class BlogController {

    //Required variables
    private static final Logger logger = LoggerFactory.getLogger(BlogController.class);
    private final PostService postService;
    private final UserService userService;

    //Controllers
    @GetMapping("/")
    public String blogPage(Model model, Principal principal){
        User user = userService.findUserByPrincipal(principal);

        attributes :{
            model.addAttribute("Posts",postService.findAllPosts());
        }

        return "blogPage";
    }
    @GetMapping("/addBlog")
    public String addBlogPage(Principal principal,Model model){
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
        return "redirect:/blog/";
    }

    @GetMapping("/blogPreview/{id}")
    public String blogPageView(@PathVariable(name = "id") Long id, Model model,
                               Principal principal){

        //Finding post by id
        Posts post = postService.findPostById(id);
        if(post != null){
            model.addAttribute("post",post);
        }else{
            ///Something...
        }
        return "blogPageView";
    }

    @GetMapping("/PostDelete/{id}")
    public String deletePost(@PathVariable(name = "id") Long id){
        //Deleting post by id
        postService.deletePostById(id);
        return "redirect:/blog/";
    }
}
