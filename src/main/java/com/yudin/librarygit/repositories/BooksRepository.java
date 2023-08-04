package com.yudin.spring.repositories;

import com.yudin.spring.models.Book;
import com.yudin.spring.models.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {

    Optional<List<Book>> findByReader(Person person);

    Optional<Book> findByName(String name);

    Page<Book> findByAuthor(String author, Pageable pageable);

//    @Query("SELECT b FROM Book b WHERE b.name LIKE %:name% and b.author LIKE %:author%")
//    Page<Book> searchByNameLike(@Param("name") String name, @Param("author") String author);
//@Query("select state from State state where state.stateId.stateCode = ?1")
//public State findStateByCode(String code, Pageable pageable);

    @Query("SELECT b FROM Book b WHERE b.name LIKE %:name% and b.author LIKE %:author%")
    Page<Book> searchByNameLikeAndAuthorLike(@Param("name") String name, @Param("author") String author, Pageable pageable);

    Page<Book> findByNameLikeAndAuthorLike(String name, String author, PageRequest pageRequest);

}
