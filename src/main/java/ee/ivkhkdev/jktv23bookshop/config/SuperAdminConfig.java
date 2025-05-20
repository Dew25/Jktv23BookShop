package ee.ivkhkdev.jktv23bookshop.config;

import ee.ivkhkdev.jktv23bookshop.model.Roles;
import ee.ivkhkdev.jktv23bookshop.model.entity.AppUserDetails;
import ee.ivkhkdev.jktv23bookshop.model.entity.Role;
import ee.ivkhkdev.jktv23bookshop.services.AppUserService;
import ee.ivkhkdev.jktv23bookshop.services.RoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SuperAdminConfig {
    private AppUserService appUserService;
    private PasswordEncoder passwordEncoder;
    private RoleService roleService;

    public SuperAdminConfig(RoleService roleService, AppUserService myUserService, PasswordEncoder passwordEncoder) {
        this.roleService = roleService;
        this.appUserService = myUserService;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public CommandLineRunner insertSuperUser() {
        return args -> {
            if (!(appUserService.allUsers().size() > 0)) {
                AppUserDetails admin = new AppUserDetails();
                admin.setFirstname("Juri");
                admin.setLastname("Melnikov");
                Role roleAdministrator = new Role("ROLE_ADMINISTRATOR");
                roleService.save(roleAdministrator);
                admin.getRoles().add(roleAdministrator);
                Role roleManager = new Role("ROLE_MANAGER");
                roleService.save(roleManager);
                admin.getRoles().add(roleManager);
                Role roleUser = new Role("ROLE_USER");
                roleService.save(roleUser);
                admin.getRoles().add(roleUser);
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("12345"));
                appUserService.save(admin);
            }
        };
    }
}
