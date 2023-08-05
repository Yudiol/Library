package com.yudin.librarygit.services;

import com.yudin.librarygit.models.Reader;
import com.yudin.librarygit.repositories.PeopleRepository;
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

    public Page<Reader> find(String name, String surname, PageRequest pageRequest) {
        return peopleRepository.searchByNameLikeAndSurnameLike(name, surname, pageRequest);
    }

    public Page<Reader> findAllByPageRequest(PageRequest pageRequest) {
        return peopleRepository.findAll(pageRequest);
    }

    public List<Reader> findAll() {
        return peopleRepository.findAll();
    }

    public Reader findOne(int id) {
        return peopleRepository.findById(id).orElse(null);
    }

    @Transactional
    public void update(int id, Reader person) {
        person.setId(id);
        peopleRepository.save(person);
    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }

    @Transactional
    public void save(Reader person) {
        person.setRegistrationTime(new Date());
        peopleRepository.save(person);
    }

    public Optional<Reader> findByEmail(String email) {
        return peopleRepository.findByEmail(email);
    }
}
