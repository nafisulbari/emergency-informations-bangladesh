package com.nafisulbari.eib.service;

import com.nafisulbari.eib.dao.CitizenRepository;
import com.nafisulbari.eib.dao.MedicalRecordRepository;
import com.nafisulbari.eib.model.Citizen;
import com.nafisulbari.eib.model.Hospital;
import com.nafisulbari.eib.model.MedicalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class CitizenServiceImpl implements CitizenService {

    @Autowired
    private CitizenRepository citizenRepository;

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;


    public void addMedicalRecord(Long citizenId, MedicalRecord medicalRecord) {

        Citizen citizen = citizenRepository.findCitizenById(citizenId);
        medicalRecord.setCitizen(citizen);
        medicalRecordRepository.save(medicalRecord);

        citizenRepository.save(citizen);


    }

    @Override
    public Citizen findCitizenById(Long id) {
        return citizenRepository.findCitizenById(id);
    }

    @Transactional
    @Override
    public void deleteCitizenById(Long id) {
        medicalRecordRepository.deleteByCitizen(citizenRepository.findCitizenById(id));
        citizenRepository.deleteCitizenById(id);

    }

    @Override
    public void saveCitizen(Citizen citizen) {
        citizenRepository.save(citizen);
    }

    @Override
    public void addMedicalRecord(MedicalRecord medicalRecord, Citizen citizen, Hospital hospital) {

    }


}
