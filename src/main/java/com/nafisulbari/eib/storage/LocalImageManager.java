package com.nafisulbari.eib.storage;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.nafisulbari.eib.model.Citizen;
import com.nafisulbari.eib.service.CitizenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.EnumMap;
import java.util.Map;

@Service
public class LocalImageManager {









    public String uploadProfilePicture(MultipartFile file ,Long id) {

        String uploadDir = System.getProperty("user.dir") + "\\citizen-records\\"+id;

        String fileName="citizen" + getStrDate() + file.getOriginalFilename().replaceAll("\\s+", "");

        File theDir = new File(uploadDir);
        theDir.mkdir();

        try {
            Path copyLocation = Paths
                    .get(uploadDir + File.separator + StringUtils.cleanPath(fileName));

            Files.copy(file.getInputStream(), copyLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            e.printStackTrace();
            throw new FileStorageException("Could not store file " + file.getOriginalFilename()
                    + ". Please try again!");
        }
        System.out.println("/citizen-records/"+id+"/"+fileName);
        return "/citizen-records/"+id+"/"+fileName;
    }




    public String generateQrCode(Citizen citizen) {
        //Source: http://zxing.github.io/zxing/apidocs/index.html


        String myCodeText = "BEGIN:VCARD\n" +
                "VERSION:3.0\n" +
                "FN:" + citizen.getName() + "\n" +
                "ADR;PREF:ID: ;;" + citizen.getId() + "\n" +
                "ADR;PREF:Blood Group: ;;" + citizen.getBloodGroup() + "\n" +
                "ADR;PREF:Emergency Contact: ;;" + citizen.getEmergencyRelation() + "\n" +
                "TEL;TYPE=VOICE,pref:+88 " + citizen.getEmergencyMobile() + "\n" +
                "URL:https://eib.nafisulbari.com/" + citizen.getId() + "\n" +
                "END:VCARD";

        String filePath = System.getProperty("user.dir") + "/citizen-records/" + citizen.getId() + "/" + citizen.getId() + ".png";
        int size = 300;
        String fileType = "png";
        File myFile=new File(filePath);

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
            BufferedImage image = new BufferedImage(CrunchifyWidth, CrunchifyWidth + 25,
                    BufferedImage.TYPE_INT_RGB);
            image.createGraphics();

            Graphics2D graphics = (Graphics2D) image.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, CrunchifyWidth, CrunchifyWidth + 25);
            graphics.setColor(Color.BLACK);

            for (int i = 0; i < CrunchifyWidth; i++) {
                for (int j = 0; j < CrunchifyWidth; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }

            String citizenId = "" + citizen.getId();
            graphics.drawString(citizenId, (310 / 2) - (citizenId.length() * 2), 310);



            ImageIO.write(image, fileType, myFile);



        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }

        System.out.println("\n\nYou have successfully created QR Code.");
        //file path according to web
        return "/citizen-records/" + citizen.getId() + "/" + citizen.getId() +".png";
    }




    public String uploadFilesFromTinyMCE(MultipartFile files, String recordType, String strDate, int citizen_id) {



        try {
            String folder = System.getProperty("user.dir") + "\\citizen-records\\" + citizen_id + "\\" + recordType + "\\" + strDate + "\\";
            String folderPre = System.getProperty("user.dir") + "\\citizen-records\\" + citizen_id + "\\" + recordType + "\\";

            StringBuffer result = new StringBuffer();

            result.append("Uploading of File(s) ");

            if (!files.isEmpty()) {

                try {
                    boolean created = false;

                    try {
                        File theDirPre = new File(folderPre);
                        theDirPre.mkdir();
                        File theDir = new File(folder);
                        created = theDir.mkdir();

                    } catch (SecurityException se) {
                        se.printStackTrace();
                    }
                    if (created) {
                        System.out.println("DIR created");
                    }
                    String path = "";
                    path = folder + files.getOriginalFilename();
                    File destination = new File(path);

                    System.out.println("--> " + destination);

                    files.transferTo(destination);
                    result.append(files.getOriginalFilename() + " Succsess. ");
                } catch (Exception e) {
                    throw new RuntimeException("Image saving failed", e);
                }

            } else
                result.append(files.getOriginalFilename() + " Failed. ");


            String filePath = "\\citizen-records\\" + citizen_id + "\\"+recordType+"\\" + strDate + "\\" + files.getOriginalFilename();
            String location = "{\"location\":\"" + filePath + "\"}";
            location = location.replace('\\', '/');
            return location;

        } catch (Exception e) {
            return "Error Occured while uploading files." + " => " + e.getMessage();
        }
    }


    public String getStrDate() {

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(date);
        strDate = strDate.replace(':', '-').replaceAll("\\s+", "_");

        return strDate;
    }
}