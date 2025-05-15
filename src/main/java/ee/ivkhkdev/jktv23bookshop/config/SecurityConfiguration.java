package ee.ivkhkdev.jktv23bookshop.config;

import ee.ivkhkdev.jktv23bookshop.model.Roles;
import ee.ivkhkdev.jktv23bookshop.model.entity.AppUser;
import ee.ivkhkdev.jktv23bookshop.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/css/**",
                                "/js/**",
                                "/images/**",
                                "/favicon.ico",
                                "/",
                                "/login",
                                "/loginForm",
                                "/registrationForm",
                                "/registration"
                        ).permitAll()
                        .requestMatchers("/user/**").hasAuthority(Roles.USER.toString())
                        .requestMatchers("/manager/**").hasAuthority(Roles.MANAGER.toString())
                        .requestMatchers("/admin/**").hasAuthority(Roles.ADMINISTRATOR.toString())
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/loginForm")
                        .defaultSuccessUrl("/")
                        .failureUrl("/login?error=true")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                        .logoutSuccessUrl("/login?logout=true")
                        .permitAll()
                )
                .exceptionHandling(ex -> ex
                        .accessDeniedPage("/access-denied")
                )
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}