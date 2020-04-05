package com.nafisulbari.eib.storage;

import com.nafisulbari.eib.model.Citizen;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


public class CloudinaryImageManager implements ImageManagerService {
    @Override
    public String uploadProfilePicture(MultipartFile file, Long id) {
        return null;
    }

    @Override
    public String generateQrCode(Citizen citizen) {
        return null;
    }

    @Override
    public String uploadFilesFromTinyMCE(MultipartFile files, String recordType, String strDate, int citizen_id) {
        return null;
    }
}
