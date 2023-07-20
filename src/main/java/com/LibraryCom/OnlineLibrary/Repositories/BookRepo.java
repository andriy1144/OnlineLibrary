package com.LibraryCom.OnlineLibrary.Repositories;


import com.LibraryCom.OnlineLibrary.Models.Book;
import com.LibraryCom.OnlineLibrary.Models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface BookRepo extends JpaRepository<Book,Long> {
    Set<Book> findAllByGenresIn(Set<Genre> genres);
}
