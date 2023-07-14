package com.LibraryCom.OnlineLibrary.Services;

import com.LibraryCom.OnlineLibrary.Models.Genre;
import com.LibraryCom.OnlineLibrary.Repositories.GenreContainerRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class BookService {

    private final GenreContainerRepository genreContainerRepository;

    /*
       -------------------GENRES SECTION------------------
    */
    public boolean addNewGenre(String genre){
        List<Genre> genres = genreContainerRepository.findAll();
        Genre newGenre = new Genre(genre);
        try {
            genreContainerRepository.save(newGenre);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public List<Genre> getAllGenres(){
        return genreContainerRepository.findAll();
    }

    public void deleteGenre(Long id){
        genreContainerRepository.deleteById(id);
    }
}
