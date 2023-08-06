package com.yudin.librarygit.controllers;

import com.yudin.librarygit.models.Librarian;
import com.yudin.librarygit.services.LibrarianService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
//@RequestMapping("/librarians")
public class LibrarianController {
    private final LibrarianService librarianService;

    @GetMapping("/librarians/registration")
    public String registration(@ModelAttribute("librarian") Librarian librarian) {
        return "librarians/registration";
    }

    @PostMapping("/librarians/registration")
    public String create(@ModelAttribute("librarian") Librarian librarian) {
        librarianService.save(librarian);
        return "redirect:/librarians";
    }

    @GetMapping("/librarians")
    public String librarian(Model model) {
        model.addAttribute("librarians", librarianService.findAll());
        return "librarians/librarian";
    }

    @PostMapping("/librarians/{id}")
    public String update(@PathVariable("id") int id
            , @RequestParam(name = "checkRole", required = false) String checkRole
            , @RequestParam(name = "checkStatus", required = false) String checkStatus) {
        librarianService.update(id, checkRole, checkStatus);
        return "redirect:/librarians";
    }
    @DeleteMapping("/librarians/{id}")
    public String delete(@PathVariable int id){
        librarianService.delete(id);
        return "redirect:/librarians";
    }

}
