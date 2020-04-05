package com.nafisulbari.eib.controller;


import com.nafisulbari.eib.storage.LocalImageManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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



    @Autowired
    private LocalImageManager localImageManager;




    @RequestMapping(value = "/police/images/{citizenId}", method = RequestMethod.POST)
    @ResponseBody
    public String handleTinyMCEUploadPolice(@RequestParam("files") MultipartFile files, @PathVariable("citizenId") int citizenId) {


        String strDate = getStrDate();

        //returning the location/file path
        return localImageManager.uploadFilesFromTinyMCE(files,"criminal",strDate,citizenId);

    }



    @RequestMapping(value = "/hospital/images/{citizenId}", method = RequestMethod.POST)
    @ResponseBody
    public String handleTinyMCEUploadMedical(@RequestParam("files") MultipartFile files, @PathVariable("citizenId") int citizenId) {


        String strDate = getStrDate();

        //returning the location/file path
        return localImageManager.uploadFilesFromTinyMCE(files,"medical",strDate,citizenId);

    }



    public String getStrDate() {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(date);
        strDate = strDate.replace(':', '-').replaceAll("\\s+", "_");
        return strDate;
    }
}
