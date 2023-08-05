package com.yudin.librarygit.controllers;

import com.yudin.librarygit.models.Book;
import com.yudin.librarygit.models.Reader;
import com.yudin.librarygit.services.BookService;
import com.yudin.librarygit.services.PeopleService;
import com.yudin.librarygit.util.PeopleValidator;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/people")
@RequiredArgsConstructor
public class PeopleController {
    private final PeopleService peopleService;
    private final BookService bookService;
    private final PeopleValidator peopleValidator;

    @GetMapping()
    public String index(@RequestParam(required = false, defaultValue = "0") int page,
                        @RequestParam(required = false, defaultValue = "10") int size, Model model) {
        Page<Reader> list = peopleService.findAllByPageRequest(PageRequest.of(page, size, Sort.by("surname")));

        model.addAttribute("page", page);
        model.addAttribute("size", list.getTotalPages());
        model.addAttribute("people", list);
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Reader person = peopleService.findOne(id);
        List<Book> listBooks = bookService.findByReader(person);
        model.addAttribute("person", person);
        if (!Objects.equals(listBooks.toString(), "[]")) {
            model.addAttribute("books", listBooks);
        }
        return "people/view";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        peopleService.delete(id);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", peopleService.findOne(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("person") @Valid Reader person, BindingResult bindingResult) {
        peopleValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            return "people/edit";
        }
        peopleService.update(id, person);
        return "redirect:/people";
    }

    @GetMapping("/search")
    public String find(Model model, @RequestParam("name") String name, @RequestParam("surname") String surname,
                       @RequestParam(required = false, defaultValue = "0") int page,
                       @RequestParam(required = false, defaultValue = "5") int size) {
        System.out.println(name + " " + surname);
        Page<Reader> listPerson = peopleService.find(name, surname, PageRequest.of(page, size, Sort.by("name")));
        model.addAttribute("people", listPerson);
        model.addAttribute("page", page);
        model.addAttribute("size", listPerson.getTotalPages());
        model.addAttribute("name", name);
        model.addAttribute("surname", surname);
        return "people/search";
    }

    @GetMapping("new")
    public String newPerson(@ModelAttribute("person") Reader person) {
        return "people/new";
    }

    @PostMapping
    public String create(@ModelAttribute("person") @Valid Reader person, BindingResult bindingResult) {
        peopleValidator.validate(person, bindingResult);
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.getModel());
            return "people/new";
        }
        peopleService.save(person);
        return "redirect:/people";
    }
}
