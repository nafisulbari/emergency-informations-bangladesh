package com.nafisulbari.eib.service;

import com.nafisulbari.eib.model.MedicalRecord;
import com.nafisulbari.eib.model.User;

public interface UserService {

     User findUserById(int userId);

     void addMedicalRecord(int userId, MedicalRecord medicalRecord);
}
