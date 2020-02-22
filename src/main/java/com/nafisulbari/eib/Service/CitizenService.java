package com.nafisulbari.eib.Service;

import com.nafisulbari.eib.Model.Citizen;
import com.nafisulbari.eib.Model.Hospital;
import com.nafisulbari.eib.Model.MedicalRecord;
import org.springframework.web.multipart.MultipartFile;

public interface CitizenService {

    Citizen findCitizenById(Long id);

    Citizen findCitizenByEmail(String email);

    void deleteCitizenById(Long id);

    void saveCitizen(Citizen citizen, MultipartFile image);

    void generateQrCode(Long id);
    void saveCitizenOnly(Citizen citizen);
}
