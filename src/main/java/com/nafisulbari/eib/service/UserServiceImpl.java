package com.nafisulbari.eib.service;

import com.nafisulbari.eib.dao.MedicalRecordRepository;
import com.nafisulbari.eib.dao.UserRepository;
import com.nafisulbari.eib.model.Citizen;
import com.nafisulbari.eib.model.MedicalRecord;
import com.nafisulbari.eib.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    public User findUserById(Long userId) {
        return userRepository.findUserById(userId);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


}
