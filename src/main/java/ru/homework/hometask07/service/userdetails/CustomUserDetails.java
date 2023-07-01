package ru.homework.hometask07.service.userdetails;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomUserDetails implements UserDetails {

    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;
    private final String username;
    private final Integer id;
    private final Boolean enabled;
    private final Boolean accountNotExpired;
    private final Boolean accountNotLocked;

    private final Boolean credentialsNonExpired;

    public CustomUserDetails(String password,
                             Collection<? extends GrantedAuthority> authorities,
                             String username,
                             Integer id) {
        this.password = password;
        this.authorities = authorities;
        this.username = username;
        this.id = id;
        this.enabled = true;
        this.accountNotExpired = true;
        this.accountNotLocked = true;
        this.credentialsNonExpired = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNotExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNotLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public Integer getUserId() {
        return id;
    }
}