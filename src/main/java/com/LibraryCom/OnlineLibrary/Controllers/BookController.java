package com.LibraryCom.OnlineLibrary.Controllers;

import com.LibraryCom.OnlineLibrary.Models.Book;
import com.LibraryCom.OnlineLibrary.Models.User;
import com.LibraryCom.OnlineLibrary.Services.BookService;
import com.LibraryCom.OnlineLibrary.Services.userServices.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;

@Controller
@Slf4j
@AllArgsConstructor
@RequestMapping("/book")
public class BookController {
    //Required variables
    private final BookService bookService;
    private final UserService userService;

    //Showing the addBookPage.html
    @GetMapping("/addNewBook")
    public String addBookPage(Model model, Principal principal){
//        User user = userService.findUserByPrincipal(principal);

//        model.addAttribute("user",user);

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
    public String viewBook(@PathVariable(name = "id") Long id,Model model,Principal principal){

        Book book = bookService.getBookById(id);

        //Add atribute to the page
        model.addAttribute("book",book);
//        model.addAttribute("Responces",bookService.getAllBookResponces(book,true));

        return "bookViewPage";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable(name = "id") Long id){

        //Execute deleteBook method
        bookService.deleteBook(id);

        return "redirect:/";
    }

    @GetMapping("/takeBook/{id}")
    public String takeBook(@PathVariable(name = "id") Long id,
                           Model model,
                           Principal principal){

        Book book = bookService.getBookById(id);
        User user = userService.findUserByPrincipal(principal);

        if(bookService.addBookToInventory(book,user)){
            model.addAttribute("className","alert alert-success");
            model.addAttribute("text","Ви успішно орендували книгу!\n" +
                    "Не забудьте її вчасно вернути");
        }else{
            model.addAttribute("className", "alert alert-danger");
            model.addAttribute("text", "Книга не була орендована");
        }

        model.addAttribute("book", book);
        return "bookViewPage";
    }
    //!!!Fix in the future!!!
//    @PostMapping("{id}/addBookResponce/")
//    public String saveBookResponce(@ModelAttribute BookResponce bookResponce,
//                                   @PathVariable(name = "id") Long id,
//                                   Principal principal, Model model){
//        User user = userService.findUserByPrincipal(principal);
//
//
//        Book book = bookService.getBookById(id);
//
//        model.addAttribute("book",book);
////        model.addAttribute("Responces",bookService.getAllBookResponces(book,true));
//
//        if(responseService.saveResponse(bookResponce,user,book)){
//            model.addAttribute("class","alert alert-success");
//            model.addAttribute("Message","Дякуємо за ваш відгук!");
//        }else{
//            model.addAttribute("class", "alert alert-danger");
//            model.addAttribute("Message", "Ви вже залишали ваш відгук на цю книгу!");
//        }
//
//        model.addAttribute("user",user);
//        return "bookViewPage";
//    }
}
