package com.nafisulbari.eib.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.nafisulbari.eib.dao.CitizenRequestRepository;
import com.nafisulbari.eib.model.CitizenRequest;
import com.nafisulbari.eib.storage.FileService;
import com.nafisulbari.eib.dao.CitizenRepository;
import com.nafisulbari.eib.dao.MedicalRecordRepository;
import com.nafisulbari.eib.model.Citizen;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.regex.Pattern;


@Component
public class CitizenServiceImpl implements CitizenService {

    @Autowired
    FileService fileService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    CitizenRepository citizenRepository;

    @Autowired
    CitizenRequestRepository citizenRequestRepository;

    @Autowired
    MedicalRecordRepository medicalRecordRepository;


    @Override
    public Citizen findCitizenById(Long id) {

        return citizenRepository.findCitizenById(id);
    }


    @Override
    public Citizen findCitizenByEmail(String email) {

        return citizenRepository.findCitizenByEmail(email);
    }


    @Transactional
    @Override
    public void deleteCitizenById(Long id) {

        medicalRecordRepository.deleteByCitizen(citizenRepository.findCitizenById(id));

        citizenRepository.deleteCitizenById(id);

    }


    @Override
    public void saveCitizen(Citizen citizen, MultipartFile image) {

        Citizen tempCitizen = citizenRepository.findCitizenById(citizen.getId());
        if (tempCitizen != null) {
            citizen.setCitizenPoint(tempCitizen.getCitizenPoint());
        }

        String fileName = "citizen" + getStrDate() + image.getOriginalFilename().replaceAll("\\s+", "");
        citizen.setImageUrl(fileName);

        Calendar c = Calendar.getInstance();
        c.setTime(citizen.getBirthDate());
        c.add(Calendar.DATE, 1);
        citizen.setBirthDate(c.getTime());

        citizen.setPassword(passwordEncoder.encode(citizen.getPassword()));
        citizen.setRole("CITIZEN");
        citizen.setPermissions("");

        citizenRepository.save(citizen);

        fileService.uploadProfilePicture(image, fileName, citizen.getId());

        generateQrCode(citizen.getId());

    }


    @Override
    public void saveCitizenOnly(Citizen citizen) {

        Citizen tempCitizen = citizenRepository.findCitizenById(citizen.getId());
        citizen.setImageUrl(tempCitizen.getImageUrl());
        citizen.setCitizenPoint(tempCitizen.getCitizenPoint());

        Calendar c = Calendar.getInstance();
        c.setTime(citizen.getBirthDate());
        c.add(Calendar.DATE, 1);
        citizen.setBirthDate(c.getTime());

        citizen.setPassword(passwordEncoder.encode(citizen.getPassword()));
        citizen.setRole("CITIZEN");
        citizen.setPermissions("");

        citizenRepository.save(citizen);

        generateQrCode(citizen.getId());
    }

    @Override
    public String addCitizenPoints(String id) {

        if (id != null && Pattern.matches("(?<=\\s|^)\\d+(?=\\s|$)", id)) {
            try {

                Citizen citizen = citizenRepository.findCitizenById(Long.parseLong(id));

                citizen.setCitizenPoint(citizen.getCitizenPoint() + 1);

                citizenRepository.save(citizen);

                return "added";

            } catch (Exception e) {
                System.err.println(e.getMessage());
                return "noCitizenFound";
            }

        } else if (!id.equals("") && !Pattern.matches("(?<=\\s|^)\\d+(?=\\s|$)", id)) {
            return "noCitizenFound";
        }

        return "";

    }

    @Override
    public void saveCitizenRequest(CitizenRequest citizenRequest, MultipartFile image, Citizen citizen) {

        String fileName = "citizen" + getStrDate() + image.getOriginalFilename().replaceAll("\\s+", "");
        citizenRequest.setImageUrl(fileName);
        citizenRequest.setCitizen(citizen);

        citizenRequestRepository.save(citizenRequest);

        fileService.uploadProfilePicture(image, fileName, citizen.getId());
    }

    @Override
    public void saveCitizenRequestOnly(CitizenRequest citizenRequest, Citizen citizen) {

        citizenRequest.setImageUrl(citizen.getImageUrl());
        citizenRequest.setCitizen(citizen);

        citizenRequestRepository.save(citizenRequest);
    }

    @Override
    public void updateCitizenFromRequest(Long id) {

        CitizenRequest citizenRequest = citizenRequestRepository.findCitizenRequestById(id);

        Citizen citizen = citizenRepository.findCitizenById(citizenRequest.getCitizen().getId());

        citizen.setAddress(citizenRequest.getAddress());
        citizen.setMobile(citizenRequest.getMobile());
        citizen.setEmergencyRelation(citizenRequest.getEmergencyRelation());
        citizen.setEmergencyMobile(citizenRequest.getEmergencyMobile());
        citizen.setEmail(citizenRequest.getEmail());
        citizen.setPassword(passwordEncoder.encode(citizenRequest.getPassword()));
        citizen.setImageUrl(citizenRequest.getImageUrl());

        citizenRepository.save(citizen);

        citizenRequestRepository.delete(citizenRequest);

        generateQrCode(citizen.getId());

    }

    @Override
    public void deleteCitizenRequestById(Long id) {

        citizenRequestRepository.delete(citizenRequestRepository.findCitizenRequestById(id));
    }

    @Override
    public List<CitizenRequest> findAllCitizenRequest() {

        return citizenRequestRepository.findAll();
    }

    @Override
    public List<Citizen> searchCitizenByName(String key) {

        return citizenRepository.findByNameContaining(key);
    }


    @Override
    public void generateQrCode(Long id) {
        fileService.generateQrCode(id);
    }


    public String getStrDate() {

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(date);
        strDate = strDate.replace(':', '-').replaceAll("\\s+", "_");

        return strDate;
    }
}
