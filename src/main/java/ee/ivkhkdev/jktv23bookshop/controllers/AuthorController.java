package ee.ivkhkdev.jktv23bookshop.controllers;

import ee.ivkhkdev.jktv23bookshop.model.entity.Author;
import ee.ivkhkdev.jktv23bookshop.services.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }
    @GetMapping("/")
    public String showListAuthorsForm(Model model) {
        model.addAttribute("authors", authorService.list());
        return "authors/listAuthors";
    }

    @GetMapping("/new")
    public String showAddAuthorForm(Model model) {
        model.addAttribute("author", new Author());
        return "authors/add-author";
    }

    @PostMapping("/save")
    public String saveAuthor(@RequestParam String firstname,@RequestParam String lastname) {
        Author author = new Author();
        author.setFirstname(firstname);
        author.setLastname(lastname);
        authorService.add(author);
        return "redirect:/"; // можно изменить на "/" если нужно
    }
}
