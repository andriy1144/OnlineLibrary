package com.LibraryCom.OnlineLibrary.Repositories;

import com.LibraryCom.OnlineLibrary.Models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreContainerRepository extends JpaRepository<Genre, Long> {
}