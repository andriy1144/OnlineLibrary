package com.LibraryCom.OnlineLibrary.Repositories;

import com.LibraryCom.OnlineLibrary.Models.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostsRepo extends JpaRepository<Posts,Long> {
}
