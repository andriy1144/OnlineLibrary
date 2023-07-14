package com.LibraryCom.OnlineLibrary.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true,name = "genre")
    private String genre;

    public Genre(){

    }
    public Genre(String genre){
        this.genre = genre;
    }

}
