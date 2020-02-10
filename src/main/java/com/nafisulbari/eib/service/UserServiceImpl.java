package com.nafisulbari.eib.service;

import com.nafisulbari.eib.dao.MedicalRecordRepository;
import com.nafisulbari.eib.dao.UserRepository;
import com.nafisulbari.eib.model.MedicalRecord;
import com.nafisulbari.eib.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MedicalRecordRepository medicalRecordRepository;


    public void addMedicalRecord(int userId, MedicalRecord medicalRecord) {

        medicalRecordRepository.save(medicalRecord);

        User user = userRepository.findUserById(userId);
        List<MedicalRecord> usersMedicalRecords = user.getMedicalRecords();
        usersMedicalRecords.add(medicalRecord);
        user.setMedicalRecords(usersMedicalRecords);

        userRepository.save(user);

    }

    public User findUserById(int userId) {
        return userRepository.findUserById(userId);
    }


}
