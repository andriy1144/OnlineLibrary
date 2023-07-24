package com.LibraryCom.OnlineLibrary.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Response {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "responseText")
    private Long responseText;

    @ManyToOne(fetch = FetchType.EAGER,cascade = {CascadeType.REFRESH})
    private User user;

    @Column(name = "userRate")
    private Double userRate;

    @Column(name = "dateOfCreating")
    private LocalDateTime dateOfCreating;

    @PrePersist
    private void init(){
        dateOfCreating = LocalDateTime.now();
    }
}
