package ee.ivkhkdev.jktv23bookshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LoginController {
    @RequestMapping("/login")
    public String listUsers(Model model) {
        model.addAttribute("content", "/users/login");
        return "layout";
    }
}
