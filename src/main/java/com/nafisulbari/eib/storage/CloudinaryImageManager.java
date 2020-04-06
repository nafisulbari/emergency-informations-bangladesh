package com.nafisulbari.eib.storage;

import com.cloudinary.utils.ObjectUtils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.nafisulbari.eib.model.Citizen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.EnumMap;
import java.util.Map;

/**
 * Use @Component over this class to enable image uploads in cloud
 * do not forger to remove @Component over LocalImageManager class
 *
 * @author  Ahmed Nafisul Bari
 */

public class CloudinaryImageManager implements ImageManagerService {


    @Autowired
    CloudinaryService cloudinaryService;

    @Override
    public String uploadProfilePicture(MultipartFile file, Long id) {

        String uploadDir = "citizen-records/" + id + "/";

        String fileName = "citizen" + getStrDate() + file.getOriginalFilename().replaceAll("\\s+", "");

        Map params = ObjectUtils.asMap("public_id", uploadDir + fileName, "use_filename", true, "unique_filename", false);

        try {

            Map uploadResult = cloudinaryService.upload(file.getBytes(), params);
            return uploadResult.get("url").toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Unable to upload");
        return null;

    }

    @Override
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


        int size = 300;


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


            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ImageIO.write(image, "png", byteArrayOutputStream);

            String uploadDir_filename = "citizen-records/" + citizen.getId() + "/" + citizen.getId();
            Map params = ObjectUtils.asMap("public_id", uploadDir_filename, "use_filename", true, "unique_filename", false);

            try {

                Map uploadResult = cloudinaryService.upload(byteArrayOutputStream.toByteArray(), params);
                return uploadResult.get("url").toString();
            } catch (Exception e) {
                e.printStackTrace();
            }


        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }


        System.out.println("Unable to upload");
        return null;
    }

    @Override
    public String uploadFilesFromTinyMCE(MultipartFile files, String recordType, String strDate, int citizen_id) {


        String uploadDir = "citizen-records/" + citizen_id + "/" + recordType + "/" + strDate + "/";

        String fileName = "record_" + strDate + files.getOriginalFilename().replaceAll("\\s+", "");

        Map params = ObjectUtils.asMap("public_id", uploadDir + fileName, "use_filename", true, "unique_filename", false);

        try {

            Map uploadResult = cloudinaryService.upload(files.getBytes(), params);

            String location = "{\"location\":\"" + uploadResult.get("url").toString() + "\"}";
            location = location.replace('\\', '/');

            return location;

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Unable to upload from tinyMCE");
        return null;


    }


    public String getStrDate() {

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(date);
        strDate = strDate.replace(':', '-').replaceAll("\\s+", "_");

        return strDate;
    }
}
