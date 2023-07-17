package com.LibraryCom.OnlineLibrary.Controllers;

import com.LibraryCom.OnlineLibrary.Models.Book;
import com.LibraryCom.OnlineLibrary.Services.BookService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.model.IModel;

import java.io.IOException;

@Controller
@Slf4j
@AllArgsConstructor
@RequestMapping("/book")
public class BookController {
    //Required variables
    private final BookService bookService;

    //Showing the addBookPage.html
    @GetMapping("/addNewBook")
    public String addBookPage(Model model){
        model.addAttribute("Genres", bookService.getAllGenres());
        return "addBookPage";
    }

    //Gets post request to add new book
    @PostMapping("/addBook")
    public String addBookRequest(Book book,
                                 @RequestParam(name = "files",required = false) MultipartFile[] files,
                                 @RequestParam(name = "genresList",required = false) String[] genres) throws IOException {

        bookService.addNewBook(book,files,genres);

        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String viewBook(@PathVariable(name = "id") Long id,Model model){
        Book book = bookService.getBookById(id);

        //Add atribute to the page
        model.addAttribute("book",book);

        return "bookViewPage";
    }

}
