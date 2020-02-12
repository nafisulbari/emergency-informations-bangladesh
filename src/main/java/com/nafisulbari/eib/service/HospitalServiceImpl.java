package com.nafisulbari.eib.service;

import com.nafisulbari.eib.dao.HospitalRepository;
import com.nafisulbari.eib.dao.MedicalRecordRepository;
import com.nafisulbari.eib.model.Citizen;
import com.nafisulbari.eib.model.Hospital;
import com.nafisulbari.eib.model.MedicalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HospitalServiceImpl implements HospitalService {


    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Override
    public void saveHospital(Hospital hospital) {
        hospitalRepository.save(hospital);
    }

    @Override
    public Hospital findHospitalById(Long id) {
        return hospitalRepository.findHospitalById(id);
    }

    @Override
    public void saveMedicalRecord(MedicalRecord medicalRecord, Hospital hospital, Citizen citizen) {
        medicalRecord.setHospital(hospital);
        medicalRecord.setCitizen(citizen);
        medicalRecordRepository.save(medicalRecord);
    }

    @Override
    public MedicalRecord findMedicalRecordById(Long id) {
        return medicalRecordRepository.findMedicalRecordById(id);
    }
}
