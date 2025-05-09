package ee.ivkhkdev.jktv23bookshop.config;

import ee.ivkhkdev.jktv23bookshop.model.entity.AppUser;
import ee.ivkhkdev.jktv23bookshop.services.AppUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SuperAdminConfig {
    private AppUserService appUserService;
    private PasswordEncoder passwordEncoder;

    public SuperAdminConfig(AppUserService myUserService, PasswordEncoder passwordEncoder) {
        this.appUserService = myUserService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public CommandLineRunner insertSuperUser() {
        return args -> {
            if (!(appUserService.allUsers().size() > 0)) {
                AppUser admin = new AppUser();
                admin.getRoles().add("ADMIN");
                admin.getRoles().add("MANAGER");
                admin.getRoles().add("USER");
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("12345"));
                appUserService.save(admin);
            }
        };
    }
}
