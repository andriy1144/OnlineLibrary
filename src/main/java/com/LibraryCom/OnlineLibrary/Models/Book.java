package com.LibraryCom.OnlineLibrary.Models;

import com.LibraryCom.OnlineLibrary.Models.ResponcesEntities.BookResponce;
import com.LibraryCom.OnlineLibrary.Models.ResponcesEntities.LibraryResponse;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.List;

@Entity
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "imagesList")
    @OneToMany(targetEntity = Images.class,fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Images> imagesList = new ArrayList<>();

    @Column(name = "author")
    private String author;

    @Column(name = "daysLeft")
    private Long daysLeft;

    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.REFRESH})
    private User userTaker;

    @Column(name = "features")
    private List<String> features;

    //BookResponce Relation
    @OneToMany(mappedBy = "book",cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    private List<BookResponce> bookResponces = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.MERGE
            })
    @JoinTable(name = "books_genres",
    joinColumns = {@JoinColumn(name = "book_id")},
    inverseJoinColumns = {@JoinColumn(name = "genre_id")})
    private Set<Genre> genres = new HashSet<>();

    public void addGenre(Genre genre){
        this.genres.add(genre);
        genre.getBooks().add(this);
    }
    public void removeGenre(long genreId){
        Genre genre = this.genres.stream().filter( t-> t.getId() == genreId).findFirst().orElse(null);
        if(genre != null){
            this.genres.remove(genre);
            genre.getBooks().remove(this);
        }
    }
    public void addImage(Images img){
        imagesList.add(img);
    }
}
