package ee.ivkhkdev.jktv23bookshop.controllers;

import ee.ivkhkdev.jktv23bookshop.model.entity.Book;
import ee.ivkhkdev.jktv23bookshop.services.AuthService;
import ee.ivkhkdev.jktv23bookshop.services.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AuthController {
    private final BookService bookService;
    private AuthService authService;

    public AuthController(BookService bookService, AuthService authService) {
        this.bookService = bookService;
        this.authService = authService;
    }

    @RequestMapping("/")
    public String showMainForm(Model model) {
        List<Book> books = bookService.list();
        model.addAttribute("title", books);
        model.addAttribute("content", "index");
        return "layout";
    }
    @GetMapping("/loginForm")
    public String showLoginForm(Model model) {
        model.addAttribute("content", "/users/loginForm");
        return "layout";
    }
    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        Model model) {
        boolean authenticated = authService.authenticate(username, password);
        if (authenticated) {
            return "redirect:/";  // успешный вход — редирект на главную
        } else {
            model.addAttribute("info", "Неверный логин или пароль");
            return "/loginForm";  // возвращаем страницу логина с ошибкой
        }
    }
}
