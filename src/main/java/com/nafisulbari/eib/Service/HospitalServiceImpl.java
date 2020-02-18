package com.nafisulbari.eib.Service;

import com.nafisulbari.eib.Dao.HospitalRepository;
import com.nafisulbari.eib.Dao.MedicalRecordRepository;
import com.nafisulbari.eib.Model.Citizen;
import com.nafisulbari.eib.Model.Hospital;
import com.nafisulbari.eib.Model.MedicalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Component
public class HospitalServiceImpl implements HospitalService {


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;





    
    @Override
    public void saveHospital(Hospital hospital) {

        hospital.setPassword(passwordEncoder.encode(hospital.getPassword()));
        hospital.setRole("HOSPITAL");
        hospital.setPermissions("");

        hospitalRepository.save(hospital);
    }

    @Override
    public Hospital findHospitalById(Long id) {
        return hospitalRepository.findHospitalById(id);
    }

    @Override
    public Hospital findHospitalByEmail(String email) {
        return hospitalRepository.findHospitalByEmail(email);
    }

    @Override
    public void saveMedicalRecord(MedicalRecord medicalRecord, Hospital hospital, Citizen citizen) {

        Calendar c = Calendar.getInstance();
        c.setTime(medicalRecord.getDate());
        c.add(Calendar.DATE, 1);
        medicalRecord.setDate(c.getTime());

        medicalRecord.setHospital(hospital);
        medicalRecord.setCitizen(citizen);
        medicalRecordRepository.save(medicalRecord);
    }

    @Override
    public MedicalRecord findMedicalRecordById(Long id) {
        return medicalRecordRepository.findMedicalRecordById(id);
    }
}
