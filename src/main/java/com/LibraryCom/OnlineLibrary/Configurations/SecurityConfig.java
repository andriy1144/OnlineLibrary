package com.LibraryCom.OnlineLibrary.Configurations;

import com.LibraryCom.OnlineLibrary.Models.enums.Role;
import com.LibraryCom.OnlineLibrary.Services.userServices.UserDetaiService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetaiService userDetaiService;


    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetaiService).passwordEncoder(passwordEncoder());
    }

    //Main Configuration System
    @Bean
    public SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(request -> {
                            request.requestMatchers("/", "/blog/**", "/book/**", "/images/**", "/registration", "/static/**", "/about", "/search"
                                            , "/libraryRespones")
                                    .permitAll();

                            request.requestMatchers("/adminPanel")
                                    .hasAuthority(Role.ADMIN_ROLE.getAuthority());

                            request.anyRequest().authenticated();
                        }

                )
                .formLogin(
                        login ->
                                login.loginPage("/login")
                                        .permitAll()
                )
                .logout(
                       LogoutConfigurer::permitAll
                );

        return httpSecurity.build();
    }


    //Password encoder
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder(8);
    }
}
