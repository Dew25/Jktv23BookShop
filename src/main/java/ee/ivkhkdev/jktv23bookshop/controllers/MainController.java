package ee.ivkhkdev.jktv23bookshop.controllers;

import ee.ivkhkdev.jktv23bookshop.model.entity.AppUserDetails;
import ee.ivkhkdev.jktv23bookshop.model.entity.Role;
import ee.ivkhkdev.jktv23bookshop.services.AppUserService;
import ee.ivkhkdev.jktv23bookshop.services.RoleService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    private final AppUserService appUserService;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    public MainController(AppUserService appUserService, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.appUserService = appUserService;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("content","/index");
        return "layout";
    }
    @GetMapping("/loginForm")
    public String login(Model model) {
        model.addAttribute("content","/loginForm");
        return "layout";
    }
    @GetMapping("/registrationForm")
    public String registrationForm(Model model) {
        model.addAttribute("content","/registrationForm");
        return "layout";
    }
    @PostMapping("/registration")
    public String registration(Model model,
                               @RequestParam String firstname,
                               @RequestParam String lastname,
                               @RequestParam String username,
                               @RequestParam String password) {
        AppUserDetails appUserDetails = new AppUserDetails();
        appUserDetails.setFirstname(firstname);
        appUserDetails.setLastname(lastname);
        appUserDetails.setUsername(username);
        appUserDetails.setPassword(passwordEncoder.encode(password));
        Role roleUser = roleService.findRole("ROLE_USER");
        appUserDetails.getRoles().add(roleUser);
        appUserService.save(appUserDetails);
        model.addAttribute("content","/index");
        return "layout";
    }


}
