package com.folivora.distant2.domain;

import org.springframework.security.core.GrantedAuthority;
//Уровни доступа
public enum Role implements GrantedAuthority {
    USER, TEACHER, ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
