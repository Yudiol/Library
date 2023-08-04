package com.yudin.spring.services;

import com.yudin.spring.models.Book;
import com.yudin.spring.models.Person;
import com.yudin.spring.repositories.BooksRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookService {
    private final BooksRepository booksRepository;

    public BookService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public Page<Book> findAll(PageRequest pageRequest) {
        return booksRepository.findAll(pageRequest);
    }

    public Book findById(int id) {
        return booksRepository.findById(id).orElse(null);
    }

    @Transactional
    public void update(int id, Book book) {
        book.setId(id);
        booksRepository.save(book);
    }

    @Transactional
    public void deleteById(int id) {
        booksRepository.deleteById(id);
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void release(int id) {
        Optional<Book> optionalBook = booksRepository.findById(id);
        if (optionalBook.isPresent()) {
            Person person = optionalBook.get().getReader();
            person.getBookList().remove(optionalBook.get());
            optionalBook.get().setReader(null);
        }
    }

    @Transactional
    public void assign(int id, Person person) {
        Book book = booksRepository.findById(id).orElse(null);
        book.setReader(person);
        book.setDateOfAppointment(new Date());
    }

    public Page<Book> find(String name, String author, PageRequest pageRequest) {
//        return booksRepository.findByNameLikeAndAuthorLike(name, author, pageRequest);
        return booksRepository.searchByNameLikeAndAuthorLike(name, author, pageRequest);

    }

    public Page<Book> findByAuthor(String author, PageRequest pageRequest) {
        return booksRepository.findByAuthor(author, pageRequest);
    }

    public List<Book> findByReader(Person person) {
        return booksRepository.findByReader(person).orElse(null);
    }

    public Optional<Book> findByName(String name) {
        return booksRepository.findByName(name);
    }
}
