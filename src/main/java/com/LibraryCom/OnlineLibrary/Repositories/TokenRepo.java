package com.LibraryCom.OnlineLibrary.Repositories;

import com.LibraryCom.OnlineLibrary.FunctionalClasses.TokensType;
import com.LibraryCom.OnlineLibrary.Models.Token;
import com.LibraryCom.OnlineLibrary.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepo extends JpaRepository<Token,Long> {
    Token findTokenByTokenAndTokensType(String token,TokensType type);
    Token findTokenByUserAndTokensType(User user, TokensType type);
}
