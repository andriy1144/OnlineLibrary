package com.LibraryCom.OnlineLibrary.Controllers;

import com.LibraryCom.OnlineLibrary.FunctionalClasses.TokensType;
import com.LibraryCom.OnlineLibrary.Models.Book;
import com.LibraryCom.OnlineLibrary.Models.Token;
import com.LibraryCom.OnlineLibrary.Models.User;
import com.LibraryCom.OnlineLibrary.Repositories.TokenRepo;
import com.LibraryCom.OnlineLibrary.Repositories.UserRepo;
import com.LibraryCom.OnlineLibrary.Services.BookService;
import com.LibraryCom.OnlineLibrary.Services.MailSenderService.MailSenderService;
import com.LibraryCom.OnlineLibrary.Services.userServices.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private MailSenderService mailSenderService;

    @Autowired
    private BookService bookService;

    @Autowired
    private TokenRepo tokenRepo;

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
    public String returnBookConfirmPage(@PathVariable(name = "id") Long bookId,
                                        Model model) {
        Book book = bookService.getBookById(bookId);
        User user = book.getUserTaker();

        if (userService.getTokenByUser(user, TokensType.CONFIRM_RETURN) == null) {
            Token token = new Token(book.getUserTaker(), TokensType.CONFIRM_RETURN);
            tokenRepo.save(token);

            String text = "Ваш код підтвердження: " + token.getToken();

            Runnable thread = () -> mailSenderService.sendMailMessage(book.getUserTaker().getEmail(), text);
            Thread t = new Thread(thread, "Confirming book return");
            t.start();
        }

        model.addAttribute("book", book);

        return "returnConfirm";
    }
}
