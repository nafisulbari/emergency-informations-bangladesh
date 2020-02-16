package com.nafisulbari.eib.Service;

import com.nafisulbari.eib.Model.Citizen;
import com.nafisulbari.eib.Model.Hospital;
import com.nafisulbari.eib.Model.MedicalRecord;

public interface HospitalService {


    void saveHospital(Hospital hospital);

    Hospital findHospitalById(Long id);

    void saveMedicalRecord(MedicalRecord medicalRecord, Hospital hospital, Citizen citizen);

    MedicalRecord findMedicalRecordById(Long id);


}
