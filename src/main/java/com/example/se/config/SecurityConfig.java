package com.example.se.config;

import com.example.se.failureHandler.CustomAuthenticationFailureHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Autowired
    CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception
    {
        httpSecurity
                .formLogin(form -> form
                        .loginProcessingUrl("/authenticateTheUser")
                        .failureHandler(customAuthenticationFailureHandler)
                        .loginPage("/login").permitAll());

        String[] staticResources = {
                "/css/**",
                "/images/**",
                "/fonts/**",
                "/js/**",};

        httpSecurity
                .authorizeHttpRequests(auth->auth
                        .requestMatchers(staticResources).permitAll()
                        .requestMatchers("/resources/**").permitAll()
                        .requestMatchers("/register").permitAll()
                        .requestMatchers("/login").anonymous()
                        .anyRequest()
                            .authenticated());

        httpSecurity
                .logout(logout -> logout
                        .deleteCookies("remember-me")
                        .permitAll());

        httpSecurity
                .rememberMe(rememberMe -> rememberMe
                        .tokenValiditySeconds(3600*24*30));

        return httpSecurity.build();
    }
}
