package com.nafisulbari.eib.service;

import com.nafisulbari.eib.model.Citizen;
import com.nafisulbari.eib.model.CitizenRequest;
import com.nafisulbari.eib.model.Hospital;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CitizenService {

    Citizen findCitizenById(Long id);

    Citizen findCitizenByEmail(String email);

    void deleteCitizenById(Long id);

    void saveCitizen(Citizen citizen, MultipartFile image);

    void generateQrCode(Long id);

    void saveCitizenOnly(Citizen citizen);

    String addCitizenPoints(String id);


    void saveCitizenRequest(CitizenRequest citizenRequest, MultipartFile image, Citizen citizen);

    void saveCitizenRequestOnly(CitizenRequest citizenRequest, Citizen citizen);

    void updateCitizenFromRequest(Long id);

    void deleteCitizenRequestById(Long id);

    List<CitizenRequest> findAllCitizenRequest();

    List<Citizen> searchCitizenByName(String key);

}
