package com.LibraryCom.OnlineLibrary.Repositories.ResponcesRepo;

import com.LibraryCom.OnlineLibrary.Models.ResponcesEntities.LibraryResponse;
import com.LibraryCom.OnlineLibrary.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryResponseRepo extends JpaRepository<LibraryResponse,Long> {
    LibraryResponse findResponseByUser(User user);
}
