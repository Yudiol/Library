package com.yudin.librarygit.util;

import com.yudin.librarygit.models.Reader;
import com.yudin.librarygit.services.PeopleService;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class PeopleValidator implements Validator {
    private final PeopleService peopleService;

    public PeopleValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Reader.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Reader person = (Reader) o;
        Optional<Reader> optional = peopleService.findByEmail(person.getEmail());
        if (optional.isPresent() && optional.get().getId() != person.getId()) {
            errors.rejectValue("email", "", "This email already taken");
        }
    }
}
