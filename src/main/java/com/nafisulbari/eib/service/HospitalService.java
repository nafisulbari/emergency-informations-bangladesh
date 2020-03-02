package com.nafisulbari.eib.service;


import com.nafisulbari.eib.model.Citizen;
import com.nafisulbari.eib.model.Hospital;
import com.nafisulbari.eib.model.MedicalRecord;

import java.util.List;

public interface HospitalService {


    void saveHospital(Hospital hospital);

    Hospital findHospitalById(Long id);

    Hospital findHospitalByEmail(String email);

    void saveMedicalRecord(MedicalRecord medicalRecord, Hospital hospital, Citizen citizen);

    MedicalRecord findMedicalRecordById(Long id);

    List<MedicalRecord> findMedicalRecordsByCitizenId(Long id);

    List<MedicalRecord> findMedicalRecordsByCitizenIdOrderByIdASC(Long id);

    List<MedicalRecord> findAdmittedCitizensOfHospital();

    List<MedicalRecord> searchMedicalRecordsByCitizen(String key);

    List<Hospital> searchByHospitalName(String key);

}
