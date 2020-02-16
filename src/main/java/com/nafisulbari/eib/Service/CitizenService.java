package com.nafisulbari.eib.Service;

import com.nafisulbari.eib.Model.Citizen;
import com.nafisulbari.eib.Model.Hospital;
import com.nafisulbari.eib.Model.MedicalRecord;
import org.springframework.web.multipart.MultipartFile;

public interface CitizenService {

    void addMedicalRecord(Long citizenId, MedicalRecord medicalRecord);

    Citizen findCitizenById(Long id);

    void deleteCitizenById(Long id);

    void saveCitizen(Citizen citizen, MultipartFile image);

    void addMedicalRecord(MedicalRecord medicalRecord, Citizen citizen, Hospital hospital);

    void generateQrCode(Long id);
}
