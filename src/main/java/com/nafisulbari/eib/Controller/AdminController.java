package com.nafisulbari.eib.Controller;


import com.nafisulbari.eib.Model.*;
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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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


    @GetMapping("/admin/dashboard")
    public ModelAndView dashboard() {
        return new ModelAndView("admin/dashboard");
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
        } else {
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


    @GetMapping("/admin/citizen-update-request")
    public ModelAndView citizenUpdateRequestPage(Model model) {

        model.addAttribute("citizenRequests", citizenService.findAllCitizenRequest());
        return new ModelAndView("admin/citizen-update-request");
    }


    @PostMapping("/admin/citizen-update-request-accept/{id}")
    public ModelAndView citizenRequestAccept(@PathVariable("id") Long id, Model model) {

        citizenService.updateCitizenFromRequest(id);

        model.addAttribute("citizenRequests", citizenService.findAllCitizenRequest());
        return new ModelAndView("admin/citizen-update-request");
    }

    @PostMapping("/admin/citizen-update-request-decline/{id}")
    public ModelAndView citizenRequestDecline(@PathVariable("id") Long id, Model model) {

        citizenService.deleteCitizenRequestById(id);

        model.addAttribute("citizenRequests", citizenService.findAllCitizenRequest());
        return new ModelAndView("admin/citizen-update-request");
    }

    @GetMapping("/admin/criminal-record-review")
    public ModelAndView criminalRecordReview(Model model) {

        model.addAttribute("criminalRecords", policeStationService.findCriminalRecordsByActiveFalse());
        return new ModelAndView("admin/criminal-record-review");
    }


    @GetMapping("/admin/criminal-record-review/{id}")
    public ModelAndView criminalRecordReviewRecord(@PathVariable("id") Long id, Model model) {

        CriminalRecord criminalRecord = policeStationService.findCriminalRecordById(id);

        model.addAttribute("authUserEmail", userService.getAuthUserEmail());
        model.addAttribute("citizen", criminalRecord.getCitizen());
        model.addAttribute("criminalRecord", criminalRecord);
        return new ModelAndView("police/add-criminal-record");
    }


    @GetMapping("/admin/criminal-record-review/accept/{id}")
    public ModelAndView criminalRecordAccept(@PathVariable("id") Long id, Model model) {

        policeStationService.saveActiveCriminalRecord(id);

        CriminalRecord criminalRecord = policeStationService.findCriminalRecordById(id);

        model.addAttribute("authUserEmail", userService.getAuthUserEmail());
        model.addAttribute("citizen", criminalRecord.getCitizen());
        model.addAttribute("criminalRecord", criminalRecord);
        model.addAttribute("flag", "Criminal record added to citizen");
        return new ModelAndView("police/add-criminal-record");
    }

    @GetMapping("/admin/criminal-record-review/delete/{id}")
    public ModelAndView criminalRecordDelete(@PathVariable("id") Long id, Model model) {

        CriminalRecord criminalRecord = policeStationService.findCriminalRecordById(id);
        policeStationService.deleteCriminalRecord(id);

        model.addAttribute("citizen", criminalRecord.getCitizen());
        model.addAttribute("adminDeleteFlag", "Criminal record deleted");
        return new ModelAndView("police/add-criminal-record");
    }


    @GetMapping("/admin/view-criminal-record/{id}")
    public ModelAndView viewCriminalRecord(@PathVariable(name = "id") Long id,
                                           Model model) {
        String authUserEmail = userService.getAuthUserEmail();
        String authUserRole = userService.getAuthUserRole();
        CriminalRecord criminalRecord = policeStationService.findCriminalRecordById(id);

        if (authUserRole.equals("ADMIN")) {
            model.addAttribute("authUserEmail", authUserEmail);
            model.addAttribute("citizen", criminalRecord.getCitizen());
            model.addAttribute("criminalRecord", criminalRecord);

            return new ModelAndView("police/add-criminal-record");
        }
        model.addAttribute("flag", "You are not authorized to view this record");
        return new ModelAndView("error");
    }

    @GetMapping("/admin/view-medical-record/{id}")
    public ModelAndView viewMedicalRecord(@PathVariable(name = "id") Long id,
                                          Model model) {
        String authUserEmail = userService.getAuthUserEmail();
        String authUserRole = userService.getAuthUserRole();
        MedicalRecord medicalRecord = hospitalService.findMedicalRecordById(id);

        if (authUserRole.equals("ADMIN")) {
            model.addAttribute("authUserEmail", authUserEmail);
            model.addAttribute("citizen", medicalRecord.getCitizen());
            model.addAttribute("medicalRecord", medicalRecord);

            return new ModelAndView("hospital/add-medical-record");
        }
        model.addAttribute("flag", "You are not authorized to view this record");
        return new ModelAndView("error");
    }


    @GetMapping("/admin/search-hospital")
    public ModelAndView searchHospitals(@RequestParam(name = "search", required = false) String key,
                                        Model model) {
        if (key != null && !key.equals("")) {

            model.addAttribute("searchedHospitals", hospitalService.searchByHospitalName(key));
            return new ModelAndView("/admin/add-hospital");
        }

        return new ModelAndView("/admin/add-hospital");
    }

    @GetMapping("/admin/search-police-station")
    public ModelAndView searchPoliceStations(@RequestParam(name = "search", required = false) String key,
                                             Model model) {
        if (key != null && !key.equals("")) {

            model.addAttribute("searchedPoliceStations", policeStationService.searchPoliceStationByName(key));
            return new ModelAndView("/admin/add-police-station");
        }

        return new ModelAndView("/admin/add-police-station");
    }

}