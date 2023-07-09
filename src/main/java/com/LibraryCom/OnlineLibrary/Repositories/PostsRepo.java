package com.LibraryCom.OnlineLibrary.Repositories;

import com.LibraryCom.OnlineLibrary.Models.Posts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepo extends JpaRepository<Posts,Long> {
}
