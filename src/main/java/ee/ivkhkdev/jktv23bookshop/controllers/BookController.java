package ee.ivkhkdev.jktv23bookshop.controllers;

import ee.ivkhkdev.jktv23bookshop.model.entity.Author;
import ee.ivkhkdev.jktv23bookshop.model.entity.Book;
import ee.ivkhkdev.jktv23bookshop.services.AuthorService;
import ee.ivkhkdev.jktv23bookshop.services.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/books")
public class BookController {
    private final AuthorService authorService;
    private BookService bookService;

    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("books", bookService.list());

        return "index";
    }

    @GetMapping("/new")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        List<Author> authors = authorService.list();
        model.addAttribute("authors", authors);
        return "books/add-book";
    }

    @PostMapping("/save")
    public String saveBook(@RequestParam String title,
                           @RequestParam String publishedYear,
                           @RequestParam(name="authorIds", required = false) Set<Long> authorIds,
                           @RequestParam("coverFile") MultipartFile coverFile) {
        Set<Author> authors = new HashSet<>();
        if (authorIds == null) {
            authorIds = new HashSet<>();
        }
        for (Long authorId : authorIds) {
            Optional<Author> optionalAuthor = authorService.findById(authorId);
            if (optionalAuthor.isPresent()) {
                authors.add(optionalAuthor.get());
            }
        }
        Book book = new Book();
        book.setTitle(title);
        book.setPublisherYear(publishedYear);
        book.setAuthors(authors);
        if (!coverFile.isEmpty()) {
            try {
                book.setCover(coverFile.getBytes());
            } catch (IOException e) {
                e.printStackTrace(); // лучше логировать
            }
        }
        bookService.add(book);
        return "redirect:/books/";
    }
}
