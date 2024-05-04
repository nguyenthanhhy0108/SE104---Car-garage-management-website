package com.example.se.config;

import com.example.se.failureHandler.customAuthenticationFailureHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@EnableWebSecurity
@Configuration
public class securityConfig {

    customAuthenticationFailureHandler customAuthenticationFailureHandler;

    /**
     * Dependency Injection
     * @param customAuthenticationFailureHandler: CustomAuthenticationFailureHandler object
     */
    @Autowired
    public securityConfig(customAuthenticationFailureHandler customAuthenticationFailureHandler) {
        this.customAuthenticationFailureHandler = customAuthenticationFailureHandler;
    }

    /**
     * Create bean passwordEncoder used for whole project
     * @return
     * BCryptPasswordEncoder object
     */
    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * Point that authentication will use own database
     * @param dataSource: DataSource object
     * @return
     * JdbcUserDetailsManager object
     */
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }

    /**
     * Config Spring Security filter
     * @param httpSecurity: HttpSecurity object
     * @return
     * Build HttpSecurity
     * @throws Exception
     * Exception when execute
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception
    {
        //Disable CSRF and CORS
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
//        httpSecurity.cors(AbstractHttpConfigurer::disable);

        //Custom login form
        //Redirect login form to home page
        //Config customAuthenticationFailureHandler
        //Point that /login API can be access without inhibition
        httpSecurity
                .formLogin(form -> form
                        .loginProcessingUrl("/authenticateTheUser")
                        .successForwardUrl("/home")
                        .defaultSuccessUrl("/", true)
                        .failureHandler(customAuthenticationFailureHandler)
                        .loginPage("/login").permitAll());

        //Config default directory for Front-end
        String[] staticResources = {
                "/css/**",
                "/images/**",
                "/fonts/**",
                "/js/**",};

        //Config some API request
        httpSecurity
                .authorizeHttpRequests(auth->auth
//                                .anyRequest().permitAll()); //For testing
                        .requestMatchers(staticResources).permitAll()
                        .requestMatchers("/resources/**").permitAll()
                        .requestMatchers("/get-all-records").permitAll()
                        .requestMatchers("/add-form").permitAll()
                        .requestMatchers("/delete-row-form1").permitAll()
                        .requestMatchers("/delete-row-form1/**").permitAll()
                        .requestMatchers("/change-form1").permitAll()
                        .requestMatchers("/get-old-data-change-form1").permitAll()
                        .requestMatchers("/password").permitAll()
                        .requestMatchers("/test").permitAll()
                        .requestMatchers("/get-all-payment").permitAll()
                        .requestMatchers("/register").permitAll()
//                        .requestMatchers("/login").anonymous()
                        .anyRequest()
                            .authenticated());

        //Config logout API and delete cookies
        httpSecurity
                .logout(logout -> logout
                        .deleteCookies("remember-me")
                        .permitAll());

        //Config rememberMe feature
        httpSecurity
                .rememberMe(rememberMe -> rememberMe
                        .tokenValiditySeconds(3600*24*30));

        return httpSecurity.build();
    }
}
