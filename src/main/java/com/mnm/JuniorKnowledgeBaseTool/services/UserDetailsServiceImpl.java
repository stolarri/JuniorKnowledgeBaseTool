package com.mnm.JuniorKnowledgeBaseTool.services;

import com.mnm.JuniorKnowledgeBaseTool.model.User;
import com.mnm.JuniorKnowledgeBaseTool.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user=userRepository.findByLogin(s);
        if (user == null) {
            throw new UsernameNotFoundException(s);
        }
        return new MyUserPrincipal(user);
    }


}
