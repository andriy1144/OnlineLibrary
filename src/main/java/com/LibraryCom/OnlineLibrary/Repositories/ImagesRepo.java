package com.LibraryCom.OnlineLibrary.Repositories;

import com.LibraryCom.OnlineLibrary.Models.Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagesRepo extends JpaRepository<Images,Long> {
}
