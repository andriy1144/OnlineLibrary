package com.LibraryCom.OnlineLibrary.Services;

import com.LibraryCom.OnlineLibrary.Models.Book;
import com.LibraryCom.OnlineLibrary.Models.Genre;
import com.LibraryCom.OnlineLibrary.Models.Images;
import com.LibraryCom.OnlineLibrary.Repositories.BookRepo;
import com.LibraryCom.OnlineLibrary.Repositories.GenreRepo;
import jakarta.persistence.NoResultException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.yaml.snakeyaml.util.EnumUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class BookService {

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
}
