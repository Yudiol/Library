package com.yudin.librarygit.models;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

public enum Role {

    USER(Set.of("READ")), ADMIN(Set.of("READ", "WRITE"));

    private Set<String> permissionSet;

    Role(Set<String> permissions) {
        this.permissionSet = permissions;
    }

    public Set<String> getPermissionSet() {
        return permissionSet;
    }

    public Set<SimpleGrantedAuthority> getAuthorities() {
        return getPermissionSet().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
    }
}
