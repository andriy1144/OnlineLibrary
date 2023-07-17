package com.LibraryCom.OnlineLibrary.Services;

import com.LibraryCom.OnlineLibrary.Models.Images;
import com.LibraryCom.OnlineLibrary.Models.Posts;
import com.LibraryCom.OnlineLibrary.Repositories.PostsRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class PostService {

    //Required variables
    private final PostsRepo postsRepo;


    public void savePost(Posts posts, MultipartFile[] files) throws IOException{

        //Adding images to Post
        for(MultipartFile file: files){
            if(!file.isEmpty()){
                Images image = toImage(file);
                posts.addImage(image);
            }
        }

        postsRepo.save(posts);

        if(posts.getImagesList().size() != 0) {
            Images previewImage =posts.getImagesList().get(0);
            posts.setPreviewImageId(previewImage.getId());
            previewImage.setIsPreviewImage(true);
        };

        postsRepo.save(posts);

        log.info("--New post has been created id : {} , imagesList-size: {}, Time: {}", posts.getId(),posts.getImagesList().size(),posts.getDateOfCreating());
    }

    public static Images toImage(MultipartFile file) throws IOException {
        if(file.getSize() != 0){
            Images image = new Images();
            image.setName(file.getName());
            image.setSize(file.getSize());
            image.setContentType(file.getContentType());
            image.setBytes(file.getBytes());
            image.setOrigname(file.getOriginalFilename());
            return image;
        }else{
            throw new IOException();
        }
    }

    public List<Posts> findAllPosts(){
        return postsRepo.findAll();
    }

    public Posts findPostById(Long id){
        Posts post = postsRepo.findById(id).orElse(null);
        if(post != null){
            return post;
        }
        return null;
    }

    public void deletePostById(Long id){
        postsRepo.deleteById(id);
        log.info("--Post with id {} was deleted--",id);
    }
}
