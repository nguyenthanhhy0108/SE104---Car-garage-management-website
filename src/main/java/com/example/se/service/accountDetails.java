package com.example.se.service;

import com.example.se.model.account;
import com.example.se.repository.accountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class accountDetails implements UserDetailsService {
    @Autowired
    private accountRepository AccountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        List<account> accounts = AccountRepository.findByUsername(username);
        String password = null;
        List<GrantedAuthority> authorities = null;
        if(accounts.isEmpty()){
            throw new UsernameNotFoundException("Account not found");
        }
        else{
            password = accounts.get(0).getPassword();
            String role = accounts.get(0).getRole();
            authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return new User(username, password, authorities);
    }
}
