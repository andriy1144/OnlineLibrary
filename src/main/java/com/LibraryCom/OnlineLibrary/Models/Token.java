package com.LibraryCom.OnlineLibrary.Models;

import com.LibraryCom.OnlineLibrary.FunctionalClasses.TokensType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Entity
@Table(name = "Token")
@Getter
@Setter
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "token")
    private String token;

    @Column(name = "type")
    private TokensType tokensType;

    @OneToOne(targetEntity = User.class,fetch = FetchType.EAGER)
    private User user;

    public Token(){

    }
    public Token(User user, TokensType type){
        this.user = user;
        token = UUID.randomUUID().toString();
        this.tokensType = type;
    }
}
