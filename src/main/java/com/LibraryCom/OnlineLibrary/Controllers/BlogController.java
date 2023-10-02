package com.LibraryCom.OnlineLibrary.Controllers;

import com.LibraryCom.OnlineLibrary.Models.Posts;
import com.LibraryCom.OnlineLibrary.Services.PostService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@Controller
@RequestMapping("/blog")
@AllArgsConstructor
public class BlogController {

    //Required variables
    private static final Logger logger = LoggerFactory.getLogger(BlogController.class);
    private final PostService postService;
    //Controllers
    @GetMapping("/")
    public String blogPage(Model model, Principal principal){
        attributes :{
            model.addAttribute("Posts",postService.findAllPosts());
        }

        return "blogPage";
    }
    @GetMapping("/addBlog")
    public String addBlogPage(Principal principal,
                              Model model){
        return "addBlogPage";
    }

    @PostMapping("/savePost")
    public String postAddBlogPage(Posts posts,
                                  @RequestParam(name = "files") MultipartFile[] files,
                                  Model model){

        try {
            postService.savePost(posts, files);
        }catch(Exception e){
            logger.error("--Error saving post: Error : {} --",e);

            model.addAttribute("errorMessage","Помилка збереження посту!");
            return "ErrorPage";
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
            model.addAttribute("errorMessage","Такої сторінки не існує!");
            return "ErrorPage";
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
