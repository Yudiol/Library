package com.yudin.librarygit.controllers;

import com.yudin.librarygit.models.Librarian;
import com.yudin.librarygit.services.LibrarianService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class LibrarianController {
    private final LibrarianService librarianService;

    @GetMapping("/registration")
    public String registration(@ModelAttribute("librarian") Librarian librarian) {
        return "librarians/registration";
    }

    @PostMapping("/registration")
    public String create(@ModelAttribute("librarian") Librarian librarian) {
        librarianService.save(librarian);
        return "redirect:/librarians";
    }

    @GetMapping("/librarians")
    public String librarian(Model model) {
        model.addAttribute("librarians", librarianService.findAll());
        return "librarians/librarian";
    }
}
