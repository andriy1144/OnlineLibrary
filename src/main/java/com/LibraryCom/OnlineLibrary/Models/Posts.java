package com.LibraryCom.OnlineLibrary.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @OneToMany(targetEntity = Images.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Images> imagesList = new ArrayList<>();

    @Column(name = "previewImageId")
    private Long previewImageId;

    @Column(name = "dateOfCreating")
    private Date dateOfCreating;

    @PrePersist
    private void init(){
        dateOfCreating = new Date();
    }

    public void addImage(Images image){
        imagesList.add(image);
    }
}
