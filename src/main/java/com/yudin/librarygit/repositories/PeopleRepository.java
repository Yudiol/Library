package com.yudin.spring.repositories;

import com.yudin.spring.models.Book;
import com.yudin.spring.models.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
    Optional<Person> findByEmail(String email);

    //    @Query("from Person")
//    Page<Person> findAllByPageRequest(PageRequest pageRequest);
    @Query("SELECT p FROM Person p WHERE p.name LIKE %:name% and p.surname LIKE %:surname%")
    Page<Person> searchByNameLikeAndSurnameLike(@Param("name") String name, @Param("surname") String surname, Pageable pageable);
}
