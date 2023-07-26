package com.LibraryCom.OnlineLibrary.Controllers.OtherControllers;

import com.LibraryCom.OnlineLibrary.Models.Images;
import com.LibraryCom.OnlineLibrary.Repositories.ImagesRepo;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;

@RestController
@AllArgsConstructor
public class ImagesController {
    //Requred variables
    private final ImagesRepo imagesRepo;

    @GetMapping("/images/{id}")
    public ResponseEntity<?> showImage(@PathVariable(name = "id") Long id){
        //Gets image by id
        Images img = imagesRepo.findById(id).orElse(null);

        //If img not null
        if(img != null){
            return ResponseEntity.ok()
                    .header("imageName",img.getName())
                    .contentType(MediaType.valueOf(img.getContentType()))
                    .contentLength(img.getSize())
                    .body(new InputStreamResource(new ByteArrayInputStream(img.getBytes())));
        }
        return null;
    }

}
