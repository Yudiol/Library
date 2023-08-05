package com.yudin.librarygit.repositories;

import com.yudin.librarygit.models.Librarian;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LibrarianRepository extends JpaRepository<Librarian, Integer> {

    Optional<Librarian> findByEmail(String email);
}
