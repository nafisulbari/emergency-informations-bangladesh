package com.nafisulbari.eib.controller;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



/**
 * TinyRestController is responsible for handling TinyMCE image uploads
 *
 * @author  Ahmed Nafisul Bari
 */


@RestController
public class TinyRestController {


    private static String strDate;
    private static int citizen_id;


    @RequestMapping(value = "/police/images/{citizenId}", method = RequestMethod.POST)
    @ResponseBody
    public String handleTinyMCEUploadPolice(@RequestParam("files") MultipartFile files, @PathVariable("citizenId") int citizenId) {

        strDate = getStrDate();

        citizen_id = citizenId;

        String filePath = "\\citizen-records\\" + citizen_id + "\\criminal\\" + strDate + "\\" + files.getOriginalFilename();


        String result = uploadFilesFromTinyMCE(files, "criminal");

        String location = "{\"location\":\"" + filePath + "\"}";
        location = location.replace('\\', '/');

        return location;

    }


    @RequestMapping(value = "/hospital/images/{citizenId}", method = RequestMethod.POST)
    @ResponseBody
    public String handleTinyMCEUploadMedical(@RequestParam("files") MultipartFile files, @PathVariable("citizenId") int citizenId) {

        strDate = getStrDate();

        citizen_id = citizenId;

        String filePath = "\\citizen-records\\" + citizen_id + "\\medical\\" + strDate + "\\" + files.getOriginalFilename();

        String result = uploadFilesFromTinyMCE(files, "medical");

        String location = "{\"location\":\"" + filePath + "\"}";
        location = location.replace('\\', '/');

        return location;

    }

    private String uploadFilesFromTinyMCE(MultipartFile files, String recordType) {

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


            return result.toString();

        } catch (Exception e) {
            return "Error Occured while uploading files." + " => " + e.getMessage();
        }
    }


    public String getStrDate() {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        strDate = dateFormat.format(date);
        strDate = strDate.replace(':', '-').replaceAll("\\s+", "_");
        return strDate;
    }
}
