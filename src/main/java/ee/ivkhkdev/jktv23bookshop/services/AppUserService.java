package ee.ivkhkdev.jktv23bookshop.services;

import ee.ivkhkdev.jktv23bookshop.model.entity.AppUser;
import ee.ivkhkdev.jktv23bookshop.model.repositories.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.PasswordAuthentication;
import java.util.Collection;
import java.util.List;

@Service
public class AppUserService {
    private AppUserRepository appUserRepository;
    private PasswordEncoder passwordEncoder;

    public AppUserService(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<AppUser> allUsers() {
        return appUserRepository.findAll();
    }

    public void save(AppUser admin) {
        appUserRepository.save(admin);
    }
}

