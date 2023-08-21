package com.LibraryCom.OnlineLibrary.Repositories.ResponcesRepo;

import com.LibraryCom.OnlineLibrary.Models.Book;
import com.LibraryCom.OnlineLibrary.Models.ResponcesEntities.BookResponce;
import com.LibraryCom.OnlineLibrary.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookResponceRepo extends JpaRepository<BookResponce,Long> {
    //Find responce by user and book to check if user has responce already
    BookResponce findBookResponceByUserAndBook(User user,Book book);
}
