package com.nafisulbari.eib.Controller;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@RestController
public class TinyRestController {


    private static String strDate;
    private static int citizen_id;


    @RequestMapping(value = "/hospital/images/{citizenId}", method = RequestMethod.POST)
    @ResponseBody
    public String handleTinyMCEUpload(@RequestParam("files") MultipartFile files, @PathVariable("citizenId") int citizenId) {

        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        strDate = dateFormat.format(date);
        strDate = strDate.replace(':', '-').replace("\\s+", "-");

        citizen_id = citizenId;

        String filePath = "\\citizen-records\\" + citizen_id + "\\medical\\" + strDate + "\\" + files.getOriginalFilename();

        String result = uploadFilesFromTinyMCE("", files, false);

        String location = "{\"location\":\"" + filePath + "\"}";
        location = location.replace('\\', '/');

        return location;

    }

    private String uploadFilesFromTinyMCE(String prefix, MultipartFile files, boolean isMain) {

        try {
            String folder = System.getProperty("user.dir") + "\\citizen-records\\" + citizen_id + "\\medical\\" + strDate + "\\" + prefix;

            StringBuffer result = new StringBuffer();
            byte[] bytes = null;
            result.append("Uploading of File(s) ");

            if (!files.isEmpty()) {

                try {
                    boolean created = false;

                    try {
                        File theDir = new File(folder);
                        theDir.mkdir();
                        created = true;
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
                    throw new RuntimeException("Product Image saving failed", e);
                }

            } else
                result.append(files.getOriginalFilename() + " Failed. ");


            return result.toString();

        } catch (Exception e) {
            return "Error Occured while uploading files." + " => " + e.getMessage();
        }
    }
}
