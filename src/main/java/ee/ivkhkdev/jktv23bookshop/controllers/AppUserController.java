package ee.ivkhkdev.jktv23bookshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class AppUserController {
    @RequestMapping("/list")
    public String listUsers(Module model) {
        model.addAttribute("content", "pages/user");
        return "layout";

    }
}
