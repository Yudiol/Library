package com.yudin.librarygit.repositories;

import com.yudin.librarygit.models.Reader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<Reader, Integer> {
    Optional<Reader> findByEmail(String email);

    @Query("SELECT p FROM Reader p WHERE p.name LIKE %:name% and p.surname LIKE %:surname%")
    Page<Reader> searchByNameLikeAndSurnameLike(@Param("name") String name, @Param("surname") String surname, Pageable pageable);
}
