package com.LibraryCom.OnlineLibrary.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER,
        cascade = {
                CascadeType.MERGE
        },
            mappedBy = "genres")
    @JsonIgnore
    private Set<Book> books = new HashSet<>();

    public Genre(){

    }
    public Genre(String genre){
        this.name = genre;
    }

}
