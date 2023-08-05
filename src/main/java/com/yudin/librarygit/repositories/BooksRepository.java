package com.yudin.librarygit.repositories;

import com.yudin.librarygit.models.Book;
import com.yudin.librarygit.models.Reader;
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

    Optional<List<Book>> findByReader(Reader person);

    Optional<Book> findByName(String name);

    Page<Book> findByAuthor(String author, Pageable pageable);

    @Query("SELECT b FROM Book b WHERE b.name LIKE %:name% and b.author LIKE %:author%")
    Page<Book> searchByNameLikeAndAuthorLike(@Param("name") String name, @Param("author") String author, Pageable pageable);

    Page<Book> findByNameLikeAndAuthorLike(String name, String author, PageRequest pageRequest);
}
