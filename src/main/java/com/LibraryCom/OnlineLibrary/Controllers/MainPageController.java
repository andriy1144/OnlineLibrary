package com.LibraryCom.OnlineLibrary.Controllers;

import com.LibraryCom.OnlineLibrary.Models.Book;
import com.LibraryCom.OnlineLibrary.Models.Genre;
import com.LibraryCom.OnlineLibrary.Models.User;
import com.LibraryCom.OnlineLibrary.Models.enums.Role;
import com.LibraryCom.OnlineLibrary.Services.BookService;
import com.LibraryCom.OnlineLibrary.Services.ResponseService;
import com.LibraryCom.OnlineLibrary.Services.userServices.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@Controller
@AllArgsConstructor
public class MainPageController {

    // Required variables
    private static final Logger logger = LoggerFactory.getLogger(MainPageController.class);
    private final BookService bookService;
    private final UserService userService;
    private final ResponseService responseService;

    //Main page controller
    @GetMapping("/")
    public String mainPage(Model model, Principal principal){

        attributes : {
            model.addAttribute("Genres", bookService.getAllGenres());
            model.addAttribute("Books",bookService.getAllBooks());
        }
        return "mainPage";
    }

    @GetMapping("/about")
    public String aboutPage(Model model, Principal principal) {
        model.addAttribute("generalRate",responseService.calculateGeneralRate());
        if(responseService.getAllResponces().size() == 0) model.addAttribute("responcesNumber","Відгуків немає");
        else model.addAttribute(responseService.getAllResponces().size());

        return "aboutPage";
    }
    /*
                        GENRE SECTION
    */
    @PostMapping("/addNewGenre")
    public String mainPagePost(@RequestParam(name = "genre") String genre, Model model) {
        if (!genre.equals("")) {
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
    /*
                       END GENRE SECTION
    */

    /*
                       SEARCH SYSTEM
    */

    @PostMapping("/search")
    public String searchSystem(@RequestParam(name = "title") String title,
                               @RequestParam(name = "genreCheck",required = false) String[] genres,
                               Model model,Principal principal){

        User user = userService.findUserByPrincipal(principal);


        List<Book> listOfBooks = bookService.seacrhBook(title.toLowerCase(),genres);

        model.addAttribute("user",user);
        model.addAttribute("isAdmin", user.getRoleSet().contains(Role.ADMIN_ROLE));
        model.addAttribute("Genres", bookService.getAllGenres());
        model.addAttribute("Books",listOfBooks);

        return "mainPage";
    }
}
