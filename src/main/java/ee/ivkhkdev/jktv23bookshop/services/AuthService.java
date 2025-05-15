package ee.ivkhkdev.jktv23bookshop.services;

import ee.ivkhkdev.jktv23bookshop.model.entity.AppUser;
import ee.ivkhkdev.jktv23bookshop.model.repositories.AppUserRepository;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private AppUserRepository appUserRepository;
    private PasswordEncoder passwordEncoder;
    public AuthService(PasswordEncoder passwordEncoder, AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean authenticate(String username, String password) {
        Optional<AppUser> appUser = appUserRepository.findByUsername(username);
        if (!appUser.isPresent()) {
            return false;
        }
        String encodedPassword = passwordEncoder.encode(password);
        if (!appUser.get().getPassword().equals(encodedPassword)) {
            return false;
        }
        if (!appUser.get().isEnabled()) {
            return false;
        }
        if (!appUser.get().isAccountNonExpired()) {
            return false;
        }
        if (!appUser.get().isAccountNonLocked()) {
            return false;
        }
        if (!appUser.get().isCredentialsNonExpired()) {
            return false;
        }

        return true;
    }
}
