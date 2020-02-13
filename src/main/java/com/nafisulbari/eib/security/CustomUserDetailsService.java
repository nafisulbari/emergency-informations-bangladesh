package com.nafisulbari.eib.security;


import com.nafisulbari.eib.dao.UserRepository;
import com.nafisulbari.eib.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {


    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository ) {
        this.userRepository = userRepository;

    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //fetching user from db to know its roles and passed onto spring's UserDetails()

        User user = userRepository.findByEmail(email);

        CustomUserDetails cud = new CustomUserDetails(user);
        return cud;

    }
}