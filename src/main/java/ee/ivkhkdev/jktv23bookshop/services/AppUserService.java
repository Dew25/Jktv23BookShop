package ee.ivkhkdev.jktv23bookshop.services;

import ee.ivkhkdev.jktv23bookshop.model.entity.AppUserDetails;
import ee.ivkhkdev.jktv23bookshop.model.repositories.AppUserDetailsRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserService {
    private AppUserDetailsRepository appUserDetailsRepository;
    public AppUserService(AppUserDetailsRepository appUserDetailsRepository) {
        this.appUserDetailsRepository = appUserDetailsRepository;
    }
    public List<AppUserDetails> allUsers() {
        return appUserDetailsRepository.findAll();
    }
    public void save(AppUserDetails admin) {
        appUserDetailsRepository.save(admin);
    }
}

