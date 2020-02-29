package com.nafisulbari.eib.service;

import com.nafisulbari.eib.dao.UserRepository;
import com.nafisulbari.eib.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public String getAuthUserRole() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        String role = "";

        if (!email.equals("anonymousUser")) {
            role = userRepository.findByEmail(email).getRole();
        }

        return role;
    }

    @Override
    public String getAuthUserEmail() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }


    public User findUserById(Long userId) {
        return userRepository.findUserById(userId);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


}
