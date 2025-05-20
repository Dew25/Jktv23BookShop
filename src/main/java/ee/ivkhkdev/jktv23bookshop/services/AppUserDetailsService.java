package ee.ivkhkdev.jktv23bookshop.services;

import ee.ivkhkdev.jktv23bookshop.model.repositories.AppUserDetailsRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserDetailsService implements UserDetailsService {
    private final AppUserDetailsRepository appUserDetailsRepository;

    public AppUserDetailsService(AppUserDetailsRepository appUserDetailsRepository) {
        this.appUserDetailsRepository = appUserDetailsRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return appUserDetailsRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }
}
