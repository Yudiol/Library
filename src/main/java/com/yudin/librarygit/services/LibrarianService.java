package com.yudin.librarygit.services;

import com.yudin.librarygit.models.Librarian;
import com.yudin.librarygit.models.Role;
import com.yudin.librarygit.models.Status;
import com.yudin.librarygit.repositories.LibrarianRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LibrarianService {

    private final LibrarianRepository librarianRepository;
    private final PasswordEncoder passwordEncoder;

    public void save(Librarian librarian) {
        librarian.setRole(Role.USER);
        librarian.setStatus(Status.ACTIVE);
        librarian.setPassword(passwordEncoder.encode(librarian.getPassword()));
        librarianRepository.save(librarian);
    }

    public List<Librarian> findAll() {
        return librarianRepository.findAll();
    }
}
