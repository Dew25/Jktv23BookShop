package ee.ivkhkdev.jktv23bookshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class AppUserDetailsController {
    @RequestMapping("/list")
    public String listUsers(Model model) {
        model.addAttribute("content", "/users/listUsers");
        return "layout";

    }
}
