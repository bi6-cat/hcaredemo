package com.zett.hcaredemo.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
        @Bean
        public PasswordEncoder bCryptPasswordEncoder() {
                return new BCryptPasswordEncoder();
        }

        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
                http.csrf(AbstractHttpConfigurer::disable)
                        .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                                // allow access to login/register page
                                .requestMatchers("/auth/**").permitAll()
                                // allow access to home page allow access to static resources
                                .requestMatchers("/").permitAll()
                                .requestMatchers("/services/**").permitAll()
                                .requestMatchers("/doctors/**").permitAll()
                                .requestMatchers("/hospitals/**").permitAll()
//                                .requestMatchers("/hospitals/**/departments/**").hasAnyRole("ADMIN", "DOCTOR", "PATIENT")
                                .requestMatchers("/contact/**").permitAll()
                                // allow access to static resources
                                .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                                // protect admin resources
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                .requestMatchers("/patient/**").hasAnyRole("ADMIN", "PATIENT")
                                .requestMatchers("/doctor/**").hasAnyRole("ADMIN", "DOCTOR")
                                // protect all other requests and require user authentication
                                .anyRequest().authenticated())
                                // configure login/logout redirection
                        .formLogin(formLogin -> formLogin
                                // configure login page
                                .loginPage("/auth/login")
                                .loginProcessingUrl("/auth/login")
                                // After successful login, redirect to home page
                                .defaultSuccessUrl("/")
                                // After failed login, redirect to login page with error
                                .failureUrl("/auth/login?error"))
                        .logout(logout -> logout
                                .logoutUrl("/auth/logout")
                                .logoutSuccessUrl("/auth/login?logout")
                                .permitAll())
                        .exceptionHandling(exceptionHandling -> exceptionHandling
                                .accessDeniedPage("/auth/access-denied")); // 403 page
                return http.build();
        }
}