package com.nafisulbari.eib.service;

import com.nafisulbari.eib.model.Citizen;
import com.nafisulbari.eib.model.Hospital;
import com.nafisulbari.eib.model.MedicalRecord;

public interface CitizenService {

    void addMedicalRecord(Long citizenId, MedicalRecord medicalRecord);

    Citizen findCitizenById(Long id);

    void deleteCitizenById(Long id);

    void saveCitizen(Citizen citizen);

    void addMedicalRecord(MedicalRecord medicalRecord, Citizen citizen, Hospital hospital);
}
