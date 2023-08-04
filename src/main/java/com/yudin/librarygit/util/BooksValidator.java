package com.yudin.spring.util;

import com.yudin.spring.models.Book;
import com.yudin.spring.services.BookService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class BooksValidator implements Validator {
    private final BookService bookService;

    public BooksValidator(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Book.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Book newBook = (Book) o;
        Optional<Book> takenFromTable = bookService.findByName(newBook.getName());
        if (takenFromTable.isPresent() && newBook.getId() != takenFromTable.get().getId()
                && newBook.getAuthor().equals(takenFromTable.get().getAuthor())
                && newBook.getYearOfProduction() == takenFromTable.get().getYearOfProduction()) {
            errors.rejectValue("name", "", "This book already exist");
        }


    }
}
