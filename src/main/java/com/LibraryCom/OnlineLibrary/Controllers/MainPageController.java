package com.LibraryCom.OnlineLibrary.Controllers;

import com.LibraryCom.OnlineLibrary.Models.Genre;
import com.LibraryCom.OnlineLibrary.Services.BookService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;

@Controller
@AllArgsConstructor
public class MainPageController {

    // Required variables
    private static final Logger logger = LoggerFactory.getLogger(MainPageController.class);
    private final BookService bookService;

    //Main page controller
    @GetMapping("/")
    public String mainPage(Model model){
        attributes : {
            model.addAttribute("isAdmin", true);
            model.addAttribute("Genres", bookService.getAllGenres());
        }
        return "mainPage";
    }
    @PostMapping("/")
    public String mainPagePost(@RequestParam(name = "genre") String genre, Model model) {
        if (genre != null) {
            if (bookService.addNewGenre(genre)) {
                logger.info("--Новий жанр: {} створено успішно--",genre);
            } else {
                logger.error("--Створення нового жанру: {} провалилося",genre);
            }
        }
        return "redirect:/";
    }

    @GetMapping("/deleteFilter/{id}")
    public String deleteFilter(@PathVariable(name = "id") Long id){
        bookService.deleteGenre(id);
        return "redirect:/";
    }

    @GetMapping("/about")
    public String aboutPage() {
        return "aboutPage";
    }
}
