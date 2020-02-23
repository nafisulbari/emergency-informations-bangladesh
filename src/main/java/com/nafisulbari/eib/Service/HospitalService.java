package com.nafisulbari.eib.Service;

import com.nafisulbari.eib.Model.Citizen;
import com.nafisulbari.eib.Model.Hospital;
import com.nafisulbari.eib.Model.MedicalRecord;

import java.util.List;

public interface HospitalService {


    void saveHospital(Hospital hospital);

    Hospital findHospitalById(Long id);

    Hospital findHospitalByEmail(String email);

    void saveMedicalRecord(MedicalRecord medicalRecord, Hospital hospital, Citizen citizen);

    MedicalRecord findMedicalRecordById(Long id);

    List<MedicalRecord> findMedicalRecordsByCitizenId(Long id);


}
