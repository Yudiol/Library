package com.yudin.spring.util;

import com.yudin.spring.models.Person;
import com.yudin.spring.services.PeopleService;
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
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;
        Optional<Person> optional = peopleService.findByEmail(person.getEmail());
        if (optional.isPresent() && optional.get().getId() != person.getId()) {
            errors.rejectValue("email", "", "This email already taken");
        }
    }
}
