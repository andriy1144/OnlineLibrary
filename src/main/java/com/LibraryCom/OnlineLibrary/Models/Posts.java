package com.LibraryCom.OnlineLibrary.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Entity
@Table(name = "Posts")
@Getter
@Setter
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description",columnDefinition = "text")
    private String description;

    @Column(name = "imagesList")
    @OneToMany(targetEntity = Images.class,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Images> imagesList = new ArrayList<>();

    @Column(name = "previewImageId")
    private Long previewImageId;

    @Column(name = "dateOfCreating")
    private LocalDateTime dateOfCreating;

    @PrePersist
    private void init(){
        dateOfCreating = LocalDateTime.now();
    }

    public void addImage(Images image){
        imagesList.add(image);
    }
}
