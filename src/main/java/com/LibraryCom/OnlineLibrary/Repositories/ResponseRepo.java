package com.LibraryCom.OnlineLibrary.Repositories;

import com.LibraryCom.OnlineLibrary.Models.Response;
import com.LibraryCom.OnlineLibrary.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResponseRepo extends JpaRepository<Response,Long> {
    //Method to find response by user
    Response findResponseByUser(User user);
    Response findResponseByBookResponceTrueAndUser(User user);
    Response findResponseByBookResponceFalseAndUser(User user);
    // Finding responces by bookResponce value
    List<Response> findResponsesByBookResponceFalse();
}
