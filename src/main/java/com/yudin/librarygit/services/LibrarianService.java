package com.yudin.librarygit.services;

import com.yudin.librarygit.models.Librarian;
import com.yudin.librarygit.models.Role;
import com.yudin.librarygit.models.Status;
import com.yudin.librarygit.repositories.LibrarianRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LibrarianService {


    private final LibrarianRepository librarianRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void save(Librarian librarian) {
        librarian.setRole(Role.USER);
        librarian.setStatus(Status.ACTIVE);
        librarian.setPassword(passwordEncoder.encode(librarian.getPassword()));
        librarianRepository.save(librarian);
    }

    public List<Librarian> findAll() {
        return librarianRepository.findAll().stream()
                .sorted(Comparator.comparing(Librarian::getUsername)).collect(Collectors.toList());
    }

    @Transactional
    public void update(int id, String checkRole, String checkStatus) {
        Librarian librarian = librarianRepository.findById(id).orElse(null);
        if (Objects.equals(checkStatus, "on")) {
            librarian.setStatus(Status.ACTIVE);
        } else {
            librarian.setStatus(Status.BANNED);
        }
        if (Objects.equals(checkRole, "on")) {
            librarian.setRole(Role.ADMIN);
        } else {
            librarian.setRole(Role.USER);
        }
        librarianRepository.save(librarian);
    }

    @Transactional
    public void delete(int id) {
        librarianRepository.deleteById(id);
    }

    public Optional<Librarian> findByEmail(String email) {
        return librarianRepository.findByEmail(email);
    }
}
