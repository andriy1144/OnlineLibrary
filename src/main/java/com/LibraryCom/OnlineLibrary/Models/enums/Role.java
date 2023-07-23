package com.LibraryCom.OnlineLibrary.Models.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER_ROLE,ADMIN_ROLE;

    @Override
    public String getAuthority() {
        return name();
    }
}
