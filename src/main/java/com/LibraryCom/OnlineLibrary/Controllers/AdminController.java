package com.LibraryCom.OnlineLibrary.Controllers;

import com.LibraryCom.OnlineLibrary.Models.Book;
import com.LibraryCom.OnlineLibrary.Models.User;
import com.LibraryCom.OnlineLibrary.Repositories.UserRepo;
import com.LibraryCom.OnlineLibrary.Services.BookService;
import com.LibraryCom.OnlineLibrary.Services.userServices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @GetMapping("/panel")
    public String adminPage(Model model, @RequestParam(name = "inputToValidate", required = false) Long phoneNumber){
        List<User> userList = userRepo.findAll();

        if(phoneNumber != null){
            List<User> usersWithNumber = userList.stream().filter((user) -> user.getPhoneNumber().toString().startsWith(phoneNumber.toString())).toList();
            model.addAttribute("Users",usersWithNumber);
        }else{
            model.addAttribute("Users", userList);
        }

        return "adminPage";
    }

    @GetMapping("/user/{id}/takenBooks")
    public String showUsersTakenBooks(@PathVariable(name = "id") Long id,
                                      Model model){
        //Getting user
        User user = userRepo.findById(id).get();

        model.addAttribute("Books",user.getTakenBooks());

        return "usersTakenBooks";
    }

    @GetMapping("/returnBook/{id}")
    public String returnBookConfirm(@PathVariable(name = "id") Long bookId){
        Book book = bookService.getBookById(bookId);

        Long userId = book.getUserTaker().getId();


        bookService.confirmBookReturn(book);

        //... Доробити у майбутньому (покращити)
        return "redirect:/admin/user/" + userId + "/takenBooks";
    }
}
