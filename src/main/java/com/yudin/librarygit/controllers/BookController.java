package com.yudin.spring.controllers;

import com.yudin.spring.models.Book;
import com.yudin.spring.models.Person;
import com.yudin.spring.services.BookService;
import com.yudin.spring.services.PeopleService;
import com.yudin.spring.util.BooksValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    private final PeopleService peopleService;
    private final BooksValidator booksValidator;

    @GetMapping
    public String index(Model model, @RequestParam(required = false, defaultValue = "0") int page,
                        @RequestParam(required = false, defaultValue = "5") int size) {
        Page<Book> listBook = bookService.findAll(PageRequest.of(page, size, Sort.by("name")));
        model.addAttribute("books", listBook);
        model.addAttribute("page", page);
        model.addAttribute("size", listBook.getTotalPages());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Book book = bookService.findById(id);
        model.addAttribute("book", book);
        if (book.getReader() == null) {
            model.addAttribute("people", peopleService.findAll());
            model.addAttribute("person", new Person());
        } else {
            model.addAttribute("owner", book.getReader());
        }
        return "books/view";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookService.findById(id));
        return "books/edit";
    }

    @GetMapping("/author/{author}")
    public String author(@PathVariable("author") String author, Model model,
                         @RequestParam(required = false, defaultValue = "0") int page,
                         @RequestParam(required = false, defaultValue = "3") int size) {
        Page<Book> listBook = bookService.findByAuthor(author, PageRequest.of(page, size, Sort.by("name")));
        model.addAttribute("books", listBook);
        model.addAttribute("page", page);
        model.addAttribute("size", listBook.getTotalPages());
        model.addAttribute("author", author);
        return "books/author";
    }

    @GetMapping("/search")
    public String find(Model model, @RequestParam("name") String name, @RequestParam("author") String author,
                       @RequestParam(required = false, defaultValue = "0") int page,
                       @RequestParam(required = false, defaultValue = "3") int size) {
        System.out.println(name + " " + author);
        Page<Book> listBook = bookService.find(name, author, PageRequest.of(page, size, Sort.by("name")));
        model.addAttribute("books", listBook);
        model.addAttribute("page", page);
        model.addAttribute("size", listBook.getTotalPages());
        model.addAttribute("name", name);
        model.addAttribute("author", author);
        return "books/search";
    }

    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id, @ModelAttribute("book") @Valid Book book, BindingResult
            bindingResult) {
        booksValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors()) {
            return "books/edit";
        }
        bookService.update(id, book);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookService.deleteById(id);
        return "redirect:/books";
    }

    @GetMapping("/new")
    public String createBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping
    public String newBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult) {
        booksValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors()) {
            return "books/new";
        }
        bookService.save(book);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id) {
        bookService.release(id);
        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("person") Person person) {
        bookService.assign(id, person);
        return "redirect:/books/" + id;
    }

}
