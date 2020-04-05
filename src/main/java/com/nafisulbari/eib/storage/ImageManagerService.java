package com.nafisulbari.eib.storage;

import com.nafisulbari.eib.model.Citizen;
import org.springframework.web.multipart.MultipartFile;

public interface ImageManagerService {

     String uploadProfilePicture(MultipartFile file, Long id);
     String generateQrCode(Citizen citizen) ;
    String uploadFilesFromTinyMCE(MultipartFile files, String recordType, String strDate, int citizen_id);

}
