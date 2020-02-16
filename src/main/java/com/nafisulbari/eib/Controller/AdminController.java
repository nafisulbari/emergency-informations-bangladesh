package com.nafisulbari.eib.Controller;


import com.nafisulbari.eib.Model.Citizen;
import com.nafisulbari.eib.Model.Hospital;
import com.nafisulbari.eib.Model.PoliceStation;
import com.nafisulbari.eib.Model.User;
import com.nafisulbari.eib.Service.CitizenService;
import com.nafisulbari.eib.Service.HospitalService;
import com.nafisulbari.eib.Service.PoliceStationService;
import com.nafisulbari.eib.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
public class AdminController {


    @Autowired
    private UserService userService;

    @Autowired
    private CitizenService citizenService;

    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private PoliceStationService policeStationService;


    @InitBinder
    private void dateBinder(WebDataBinder binder) {
        //The date format to parse or output your dates
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //Create a new CustomDateEditor
        CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
        //Register it as custom editor for the Date type
        binder.registerCustomEditor(Date.class, editor);
    }


    @GetMapping("/admin/add-citizen")
    public ModelAndView addCitizen() {
        return new ModelAndView("admin/add-citizen");
    }


    @PostMapping("/admin/add-citizen-action")
    public ModelAndView addCitizenAction(@RequestParam("file") MultipartFile image, Citizen citizen, Model model) {

        User tempUser = userService.findByEmail(citizen.getEmail());
        if (tempUser != null) {
            model.addAttribute("flag", "Another user exists with same email");
            return new ModelAndView("admin/add-police-station");
        }

        citizenService.saveCitizen(citizen, image);

        model.addAttribute("flag", "Citizen Saved");
        return new ModelAndView("admin/add-citizen");
    }


    @GetMapping("/admin/add-police-station")
    public ModelAndView addPoliceStation() {
        return new ModelAndView("admin/add-police-station");
    }


    @PostMapping("/admin/add-police-station-action")
    public ModelAndView addPoliceStationAction(PoliceStation policeStation, Model model) {

        User tempUser = userService.findByEmail(policeStation.getEmail());
        if (tempUser != null) {
            model.addAttribute("flag", "Another user exists with same email");
            return new ModelAndView("admin/add-police-station");
        }
        model.addAttribute("flag", "Police Station Saved");
        policeStationService.savePoliceStation(policeStation);

        return new ModelAndView("admin/add-police-station");
    }


    @GetMapping("/admin/add-hospital")
    public ModelAndView addHospital() {
        return new ModelAndView("admin/add-hospital");
    }


    @PostMapping("/admin/add-hospital-action")
    public ModelAndView addHospitalAction(Hospital hospital, Model model) {

        User tempUser = userService.findByEmail(hospital.getEmail());
        if (tempUser != null) {
            model.addAttribute("flag", "Another user exists with same email");
            return new ModelAndView("admin/add-hospital");
        }

        hospitalService.saveHospital(hospital);

        model.addAttribute("flag", "Hospital Saved");
        return new ModelAndView("admin/add-hospital");
    }
}
