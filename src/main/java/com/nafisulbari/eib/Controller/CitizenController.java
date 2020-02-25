package com.nafisulbari.eib.Controller;


import com.nafisulbari.eib.Model.*;
import com.nafisulbari.eib.Service.CitizenService;
import com.nafisulbari.eib.Service.HospitalService;
import com.nafisulbari.eib.Service.PoliceStationService;
import com.nafisulbari.eib.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;


@Controller
public class CitizenController {


    @Autowired
    private CitizenService citizenService;

    @Autowired
    private UserService userService;

    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private PoliceStationService policeStationService;








    @GetMapping("/{id}")
    public ModelAndView getCitizen(@PathVariable("id") String id,
                                   Model model) {
        Long citizenId;
        try {
            citizenId = Long.parseLong(id);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Wrong citizen id Perhaps?");
            return new ModelAndView("citizen-info");
        }

        citizenId = Long.parseLong(id);
        Citizen citizen = citizenService.findCitizenById(citizenId);
        if (citizen != null) {

            if (userService.getAuthUserRole().equals("HOSPITAL")) {
                model.addAttribute("medicalRecords", hospitalService.findMedicalRecordsByCitizenId(citizenId));
            }
            if (userService.getAuthUserRole().equals("POLICE")) {
                model.addAttribute("criminalRecords", policeStationService.findCriminalRecordsByCitizenId(citizenId));
            }
            if (userService.getAuthUserEmail().equals(citizen.getEmail())) {
                model.addAttribute("medicalRecords", hospitalService.findMedicalRecordsByCitizenId(citizenId));
                model.addAttribute("criminalRecords", policeStationService.findCriminalRecordsByCitizenId(citizenId));
                citizenService.generateQrCode(citizenId);
            }

            model.addAttribute("citizen", citizen);
            model.addAttribute("authUserRole", userService.getAuthUserRole());
            model.addAttribute("authUserEmail", userService.getAuthUserEmail());

        } else {
            model.addAttribute("errorMessage", "No citizen found with id: " + citizenId);
        }


        return new ModelAndView("citizen-info");
    }





    @GetMapping("/citizen/criminal-record/{id}")
    public ModelAndView medicalRecord(@PathVariable(name = "id") Long id,
                                Model model) {

        String authUserEmail = userService.getAuthUserEmail();
        CriminalRecord criminalRecord = policeStationService.findCriminalRecordById(id);

        if (authUserEmail.equals(criminalRecord.getCitizen().getEmail())) {

            Citizen citizen = citizenService.findCitizenByEmail(authUserEmail);

            model.addAttribute("authUserEmail", authUserEmail);
            model.addAttribute("citizen", citizen);
            model.addAttribute("criminalRecord", criminalRecord);

            return new ModelAndView("police/add-criminal-record");
        }
        model.addAttribute("flag", "You are not authorized to view this record");
        return new ModelAndView("error");
    }


    @GetMapping("/citizen/medical-record/{id}")
    public ModelAndView criminalRecord(@PathVariable(name = "id") Long id,
                                Model model) {

        String authUserEmail = userService.getAuthUserEmail();
        MedicalRecord medicalRecord = hospitalService.findMedicalRecordById(id);

        if (authUserEmail.equals(medicalRecord.getCitizen().getEmail())) {

            Citizen citizen = citizenService.findCitizenByEmail(authUserEmail);

            model.addAttribute("authUserEmail", authUserEmail);
            model.addAttribute("citizen", citizen);
            model.addAttribute("medicalRecord", medicalRecord);

            return new ModelAndView("hospital/add-medical-record");
        }

        return new ModelAndView("index");
    }



    @GetMapping("/citizen/request-update")
    public ModelAndView requestUpdatePage(Model model) {

        model.addAttribute("citizen", citizenService.findCitizenByEmail(userService.getAuthUserEmail()));
        return new ModelAndView("citizen/request-update");
    }

    @PostMapping("/citizen/request-update-action")
    public ModelAndView requestUpdateAction(@RequestParam("file") MultipartFile image, CitizenRequest citizenRequest, Model model, BindingResult result) {

        Citizen citizen=citizenService.findCitizenByEmail(userService.getAuthUserEmail());
        User tempUser = userService.findByEmail(citizenRequest.getEmail());

        if (tempUser != null && !tempUser.getEmail().equals(citizen.getEmail())) {
            model.addAttribute("flagUserExists", "Another user exists with same email");
            model.addAttribute("citizen", citizenService.findCitizenByEmail(userService.getAuthUserEmail()));
            return new ModelAndView("citizen/request-update");
        }


        if (Objects.equals(image.getOriginalFilename(), "")) {
            citizenService.saveCitizenRequestOnly(citizenRequest, citizen);
        }else {
            citizenService.saveCitizenRequest(citizenRequest,image,citizen);
        }

        model.addAttribute("flag","Your update is requested");
        return new ModelAndView("citizen/request-update");
    }



}
