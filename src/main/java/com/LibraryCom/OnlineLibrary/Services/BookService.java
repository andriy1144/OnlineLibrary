package com.LibraryCom.OnlineLibrary.Services;

import com.LibraryCom.OnlineLibrary.Models.Book;
import com.LibraryCom.OnlineLibrary.Models.Genre;
import com.LibraryCom.OnlineLibrary.Models.Images;
import com.LibraryCom.OnlineLibrary.Models.ResponcesEntities.LibraryResponse;
import com.LibraryCom.OnlineLibrary.Models.User;
import com.LibraryCom.OnlineLibrary.Repositories.BookRepo;
import com.LibraryCom.OnlineLibrary.Repositories.GenreRepo;
import com.LibraryCom.OnlineLibrary.Repositories.UserRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class BookService {
    private final UserRepo userRepo;

    private final GenreRepo genreRepo;
    private final BookRepo bookRepo;

    /*
       -------------------GENRES SECTION------------------
    */
    public boolean addNewGenre(String genre){
        List<Genre> genres = genreRepo.findAll();
        Genre newGenre = new Genre(genre);
        for (Genre g : genres){
            if(g.getName().equals(newGenre.getName())){
                return false;
            }
        }
        genreRepo.save(newGenre);
        return true;
    }

    public List<Genre> getAllGenres(){
        return genreRepo.findAll();
    }

    public void deleteGenre(Long id){
        genreRepo.deleteById(id);
    }

    private Genre findGenreByName(String name){
        return genreRepo.findGenreByName(name);
    }
    /*
    ---------------------------------BOOK SECTION-------------------------
    */

    public void addNewBook(Book book, MultipartFile[] files,String[] genres) throws IOException {
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                Images img = PostService.toImage(file);
                book.addImage(img);
            }
        }
        for (String genre : genres) {
            Genre gnr = findGenreByName(genre);
            System.out.println(gnr.getName());
            book.addGenre(gnr);
        }

        bookRepo.save(book);
        log.info("-- Saved new book with id : {}, name: {}, genreListSize : {} , imagesListSize : {}",book.getId(),book.getName(),book.getGenres().size(),book.getImagesList().size());
    }

    public List<Book> getAllBooks(){
        return bookRepo.findAll();
    }
    public Book getBookById(Long id){
        Book requiredBook = bookRepo.findById(id).orElse(null);
        return requiredBook != null ? requiredBook : null;
    }

    public List<Book> seacrhBook(String title,String[] genres){
        List<Book> result;
        if(genres != null) {
            Set<Genre> genresSet = Arrays.stream(genres).map(genreRepo::findGenreByName).collect(Collectors.toSet());
            Set<Book> bookWithGenres = bookRepo.findAllByGenresIn(genresSet);
            result = bookWithGenres.stream().filter( b -> b.getName().toLowerCase().startsWith(title)).toList();
        }else{
            List<Book> books = bookRepo.findAll();
            result = books.stream().filter(b -> b.getName().toLowerCase().startsWith(title)).toList();
        }
        return result;
    }

    public boolean deleteBook(Long id){
        //Deleting book
        try{
            //if Something goes wrong it will catch and return false
            bookRepo.deleteById(id);

            log.info("--Book with id: {} deleted successful",id);

            return true;
        }catch (Exception e){
            log.error("-- Book deleting went wrong --");
            e.printStackTrace();
            return false;
        }
    }

    public boolean addBookToInventory(Book book, User user){
        if(book != null){
            user.addBookToInventory(book);

            book.setTaken(true);

            bookRepo.save(book);

            userRepo.save(user);
            log.info("-- Book with id {} , added to the user's inventory with id {}",book.getId(),user.getId());

            return true;
        }

        return false;
    }
//    public Set<LibraryResponse> getAllBookResponces(Book book, boolean half){
//        Set<LibraryResponse> bookResponces = book.getLibraryResponseSet();
//        if(half){
//            if(bookResponces.size() >= 4){
//                List<LibraryResponse> responsesList = bookResponces.stream().toList();
//                bookResponces.clear();
//                for(int i = 0; i < 4;i++){
//                    bookResponces.add(responsesList.get(i));
//                }
//            }
//            return bookResponces;
//        }
//        return bookResponces;
//    }
}
