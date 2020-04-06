package com.nafisulbari.eib.storage;

import com.nafisulbari.eib.model.Citizen;
import org.springframework.web.multipart.MultipartFile;

/**
 * This interface is responsible to handle the image and qr upload directories
 * Use @Component over any one of the LocalImageManager or CloudinaryImageManager class to
 * handle images in local storage or cloudinary cloud
 *
 * @author Ahmed Nafisul Bari
 */
public interface ImageManagerService {

    String uploadProfilePicture(MultipartFile file, Long id);

    String generateQrCode(Citizen citizen);

    String uploadFilesFromTinyMCE(MultipartFile files, String recordType, String strDate, int citizen_id);

}
