package com.LibraryCom.OnlineLibrary.Repositories;

import com.LibraryCom.OnlineLibrary.Models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepo extends JpaRepository<Genre, Long> {
    Genre findGenreByName(String name);
}