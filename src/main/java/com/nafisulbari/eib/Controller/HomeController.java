package com.nafisulbari.eib.Controller;

import com.nafisulbari.eib.Model.Citizen;
import com.nafisulbari.eib.Service.CitizenService;
import com.nafisulbari.eib.Service.HospitalService;
import com.nafisulbari.eib.Service.PoliceStationService;
import com.nafisulbari.eib.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HomeController {


    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private PoliceStationService policeStationService;

    @Autowired
    private CitizenService citizenService;

    @Autowired
    private UserService userService;


    @GetMapping("/")
    public ModelAndView index(Model model) {

        model.addAttribute("authUserRole", userService.getAuthUserRole());
        return new ModelAndView("index");
    }


    @GetMapping("/login")
    public ModelAndView homeToLoginPage() {

        return new ModelAndView("login");
    }


    @RequestMapping("/home-detect")
    public ModelAndView homeDetect() {

        return new ModelAndView("redirect:/");
    }


    @GetMapping("/{id}")
    public ModelAndView getCitizen(@PathVariable("id") int id, Model model) {

        Long citizenId =(long)id;
        Citizen citizen =citizenService.findCitizenById(citizenId);
        if (citizen!=null) {

            if (userService.getAuthUserRole().equals("HOSPITAL")){
                model.addAttribute("medicalRecords", hospitalService.findMedicalRecordsByCitizenId(citizenId));
            }
            if (userService.getAuthUserRole().equals("POLICE")){
                model.addAttribute("criminalRecords", policeStationService.findCriminalRecordsByCitizenId(citizenId));
            }
            if (userService.getAuthUserEmail().equals(citizen.getEmail())){
                citizenService.generateQrCode( citizenId);
            }

            model.addAttribute("citizen", citizen);
            model.addAttribute("authUserRole", userService.getAuthUserRole());
            model.addAttribute("authUserEmail", userService.getAuthUserEmail());

        }else {
            model.addAttribute("errorMessage", "No citizen found with id: "+citizenId);
        }


        return new ModelAndView("citizen-info");
    }


}
