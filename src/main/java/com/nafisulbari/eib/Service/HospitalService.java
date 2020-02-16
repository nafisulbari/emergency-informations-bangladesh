package com.nafisulbari.eib.service;

import com.nafisulbari.eib.model.Citizen;
import com.nafisulbari.eib.model.Hospital;
import com.nafisulbari.eib.model.MedicalRecord;

public interface HospitalService {


    void saveHospital(Hospital hospital);

    Hospital findHospitalById(Long id);

    void saveMedicalRecord(MedicalRecord medicalRecord, Hospital hospital, Citizen citizen);

    MedicalRecord findMedicalRecordById(Long id);


}
