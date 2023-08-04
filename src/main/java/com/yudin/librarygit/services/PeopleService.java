package com.yudin.spring.services;

import com.yudin.spring.models.Person;
import com.yudin.spring.repositories.PeopleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository peopleRepository;

    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    //    public Page<Person> findAllByPageRequest(PageRequest pageRequest) {
//        return peopleRepository.findAllByPageRequest(pageRequest);
//    }
    public Page<Person> find(String name, String surname, PageRequest pageRequest) {
//        return booksRepository.findByNameLikeAndAuthorLike(name, author, pageRequest);
        return peopleRepository.searchByNameLikeAndSurnameLike(name, surname, pageRequest);

    }

    public Page<Person> findAllByPageRequest(PageRequest pageRequest) {
        return peopleRepository.findAll(pageRequest);
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Person findOne(int id) {
        return peopleRepository.findById(id).orElse(null);
    }

    @Transactional
    public void update(int id, Person person) {
        person.setId(id);
        peopleRepository.save(person);
    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }

    @Transactional
    public void save(Person person) {
        person.setRegistrationTime(new Date());
        peopleRepository.save(person);
    }

    public Optional<Person> findByEmail(String email) {
        return peopleRepository.findByEmail(email);
    }


}
