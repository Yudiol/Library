package com.yudin.librarygit.services;

import com.yudin.librarygit.models.Librarian;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@RequiredArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private final Librarian librarian;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return librarian.getRole().getAuthorities();
    }

    @Override
    public String getPassword() {
        return librarian.getPassword();
    }

    @Override
    public String getUsername() {
        return librarian.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return librarian.getStatus().toString().equals("ACTIVE");
    }

    @Override
    public boolean isAccountNonLocked() {
        return librarian.getStatus().toString().equals("ACTIVE");
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return librarian.getStatus().toString().equals("ACTIVE");
    }

    @Override
    public boolean isEnabled() {
        return librarian.getStatus().toString().equals("ACTIVE");
    }
}
