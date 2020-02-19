package com.nafisulbari.eib.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.nafisulbari.eib.Storage.FileService;
import com.nafisulbari.eib.Dao.CitizenRepository;
import com.nafisulbari.eib.Dao.MedicalRecordRepository;
import com.nafisulbari.eib.Model.Citizen;

import lombok.SneakyThrows;
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

import java.util.*;


@Component
public class CitizenServiceImpl implements CitizenService {

    @Autowired
    private FileService fileService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CitizenRepository citizenRepository;

    @Autowired
    private MedicalRecordRepository medicalRecordRepository;




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

    @SneakyThrows
    @Override
    public void saveCitizen(Citizen citizen, MultipartFile image){


        String fileName = "citizen" + image.getOriginalFilename().replaceAll("\\s+", "");
        citizen.setImageUrl(fileName);

        Calendar c = Calendar.getInstance();
        c.setTime(citizen.getBirthDate());
        c.add(Calendar.DATE, 1);
        citizen.setBirthDate(c.getTime());

        citizen.setPassword(passwordEncoder.encode(citizen.getPassword()));
        citizen.setRole("CITIZEN");
        citizen.setPermissions("");
        citizenRepository.save(citizen);

        fileService.uploadFile(image, fileName, citizen.getId());

    }
    @Override
    public void saveCitizenOnly(Citizen citizen){

        Citizen tempCitizen=citizenRepository.findCitizenById(citizen.getId());
        citizen.setImageUrl(tempCitizen.getImageUrl());

        Calendar c = Calendar.getInstance();
        c.setTime(citizen.getBirthDate());
        c.add(Calendar.DATE, 1);
        citizen.setBirthDate(c.getTime());

        citizen.setPassword(passwordEncoder.encode(citizen.getPassword()));
        citizen.setRole("CITIZEN");
        citizen.setPermissions("");
        citizenRepository.save(citizen);

    }


    @Override
    public void generateQrCode(Long id) {
       //Source: http://zxing.github.io/zxing/apidocs/index.html

       Citizen citizen= citizenRepository.findCitizenById(id);

        String myCodeText = "Name: "+citizen.getName()+"" +
                "\nBlood Group: "+citizen.getBloodGroup()+
                "\nEmergency Contact: "+citizen.getEmergencyRelation()+
                "\nEmergency Mobile: "+citizen.getEmergencyMobile()+
                "\nhttp://eib.nafisulbari.com/"+citizen.getId();
        String filePath = System.getProperty("user.dir")+"/citizen-records/"+citizen.getId()+"/"+citizen.getId()+".png";
        int size = 250;
        String fileType = "png";
        File myFile = new File(filePath);
        try {

            Map<EncodeHintType, Object> hintMap = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
            hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");

            // Now with zxing version 3.2.1 you could change border size (white border size to just 1)
            hintMap.put(EncodeHintType.MARGIN, 1); /* default = 4 */
            hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);

            QRCodeWriter qrCodeWriter = new QRCodeWriter();
            BitMatrix byteMatrix = qrCodeWriter.encode(myCodeText, BarcodeFormat.QR_CODE, size,
                    size, hintMap);
            int CrunchifyWidth = byteMatrix.getWidth();
            BufferedImage image = new BufferedImage(CrunchifyWidth, CrunchifyWidth+24,
                    BufferedImage.TYPE_INT_RGB);
            image.createGraphics();

            Graphics2D graphics = (Graphics2D) image.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, CrunchifyWidth, CrunchifyWidth+25);
            graphics.setColor(Color.BLACK);

            for (int i = 0; i < CrunchifyWidth; i++) {
                for (int j = 0; j < CrunchifyWidth; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            String citizenId=""+citizen.getId();
            graphics.drawString(citizenId,(240/2)-(citizenId.length()*3),260);

            ImageIO.write(image, fileType, myFile);

        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
        System.out.println("\n\nYou have successfully created QR Code.");
    }


}
