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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;


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
    public ModelAndView addCitizenAction(@RequestParam("file") MultipartFile image, Citizen citizen, Model model, BindingResult result) {

        User tempUser = userService.findByEmail(citizen.getEmail());
        if (tempUser != null) {
            model.addAttribute("flag", "Another user exists with same email");
            model.addAttribute("citizen", citizen);

            return new ModelAndView("admin/add-citizen");

        }

        citizenService.saveCitizen(citizen, image);

        model.addAttribute("flag", "Citizen Saved");
        return new ModelAndView("admin/add-citizen");
    }


    @GetMapping("/admin/edit-citizen/{id}")
    public ModelAndView editCitizen(@PathVariable("id") Long id, Model model) {

        Citizen citizen = citizenService.findCitizenById(id);
        if (citizen == null) {
            model.addAttribute("flag", "No citizen found with id: " + id);
            return new ModelAndView("admin/add-citizen");
        }

        model.addAttribute("citizen", citizen);
        return new ModelAndView("admin/add-citizen");
    }

    @PostMapping("/admin/edit-citizen-action/{id}")
    public ModelAndView editCitizenAction(@RequestParam("file") MultipartFile image,
                                          @PathVariable("id") Long id,
                                          Citizen citizen, Model model, BindingResult result) {

        if (Objects.equals(image.getOriginalFilename(), "")) {
            citizenService.saveCitizenOnly(citizen);
        }else {
            citizenService.saveCitizen(citizen, image);
        }

        model.addAttribute("citizen", citizen);
        model.addAttribute("flag", "Citizen Updated");
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


    @GetMapping("/admin/edit-police-station/{id}")
    public ModelAndView editPoliceStation(@PathVariable("id") Long id, Model model) {

        PoliceStation policeStation = policeStationService.findPoliceStationById(id);
        if (policeStation == null) {
            model.addAttribute("flag", "No police station found with id: " + id);
            return new ModelAndView("admin/add-police-station");
        }

        model.addAttribute("policeStation", policeStation);
        return new ModelAndView("admin/add-police-station");
    }



    @PostMapping("/admin/edit-police-station-action/{id}")
    public ModelAndView editPoliceStationAction(@PathVariable("id") Long id,
                                           PoliceStation policeStation, Model model, BindingResult result) {

        policeStationService.savePoliceStation(policeStation);

        model.addAttribute("policeStation", policeStation);
        model.addAttribute("flag", "Police Station Updated");
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


    @GetMapping("/admin/edit-hospital/{id}")
    public ModelAndView editHospital(@PathVariable("id") Long id, Model model) {

        Hospital hospital = hospitalService.findHospitalById(id);
        if (hospital == null) {
            model.addAttribute("flag", "No Hospital found with id: " + id);
            return new ModelAndView("admin/add-hospital");
        }

        model.addAttribute("hospital", hospital);
        return new ModelAndView("admin/add-hospital");
    }



    @PostMapping("/admin/edit-hospital-action/{id}")
    public ModelAndView editHospitalAction(@PathVariable("id") Long id,
                                          Hospital hospital, Model model, BindingResult result) {

        hospitalService.saveHospital(hospital);

        model.addAttribute("hospital", hospital);
        model.addAttribute("flag", "Hospital Updated");
        return new ModelAndView("admin/add-hospital");
    }


}
