package com.LibraryCom.OnlineLibrary.Repositories;


import com.LibraryCom.OnlineLibrary.Models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<Book,Long> {

}
