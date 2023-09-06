package com.yudin.librarygit.util;

import com.yudin.librarygit.models.Librarian;
import com.yudin.librarygit.services.LibrarianService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class LibrarianValidator implements Validator {
    private final LibrarianService librarianService;

    public LibrarianValidator(LibrarianService librarianService) {
        this.librarianService = librarianService;
    }


    @Override
    public boolean supports(Class<?> aClass) {
        return Librarian.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Librarian librarian = (Librarian) o;
        Optional<Librarian> optional = librarianService.findByEmail(librarian.getEmail());
        if (optional.isPresent() && optional.get().getId() != librarian.getId()) {
            errors.rejectValue("email", "", "This email already taken");
        }
    }
}
