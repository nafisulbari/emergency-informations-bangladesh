package com.nafisulbari.eib.controller;

import com.nafisulbari.eib.model.Citizen;
import com.nafisulbari.eib.model.MedicalRecord;
import com.nafisulbari.eib.service.CitizenService;
import com.nafisulbari.eib.service.HospitalService;
import com.nafisulbari.eib.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@Controller
public class HospitalController {


    @Autowired
    HospitalService hospitalService;

    @Autowired
    CitizenService citizenService;

    @Autowired
    UserService userService;

    @InitBinder
    private void dateBinder(WebDataBinder binder) {
        //The date format to parse or output your dates
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //Create a new CustomDateEditor
        CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
        //Register it as custom editor for the Date type
        binder.registerCustomEditor(Date.class, editor);
    }


    @GetMapping("/hospital/{citizenId}/add-medical-record")
    public ModelAndView addMedicalRecordPage(@PathVariable("citizenId") Long citizenId, Model model) {


        Citizen citizen = citizenService.findCitizenById(citizenId);

        String authUserEmail = userService.getAuthUserEmail();
        model.addAttribute("authUserEmail", authUserEmail);

        model.addAttribute("citizen", citizen);
        return new ModelAndView("hospital/add-medical-record");
    }


    @PostMapping("/hospital/{citizenId}/add-medical-record-action")
    public ModelAndView addMedicalRecordAction(@PathVariable("citizenId") Long citizenId, MedicalRecord medicalRecord, String citizenPoint, Model model) {


        switch (citizenService.addCitizenPoints(citizenPoint)) {
            case "noCitizenFound":
                model.addAttribute("flagCp", "No citizen found with id " + citizenPoint + " to add citizen points");
                break;
            case "added":
                model.addAttribute("flagCp", "Added citizen points to " + citizenPoint);
            default:
        }

        Citizen citizen = citizenService.findCitizenById(citizenId);
        hospitalService.saveMedicalRecord(medicalRecord,
                hospitalService.findHospitalByEmail(userService.getAuthUserEmail()),
                citizen);

        String authUserEmail = userService.getAuthUserEmail();

        model.addAttribute("authUserEmail", authUserEmail);
        model.addAttribute("flag", "Medical Record Saved");
        model.addAttribute("citizen", citizen);

        return new ModelAndView("hospital/add-medical-record");
    }


    @GetMapping("/hospital/edit-medical-record/{id}/{citizenId}")
    public ModelAndView editMedicalRecord(@PathVariable("id") Long id,
                                          @PathVariable("citizenId") Long citizenId,
                                          Model model) {


        MedicalRecord medicalRecord = hospitalService.findMedicalRecordById(id);

        if (medicalRecord == null) {
            model.addAttribute("flag", "You do not have access to view this record");
            return new ModelAndView("redirect:/error");
        }

        Citizen citizen = citizenService.findCitizenById(citizenId);
        String authUserEmail = userService.getAuthUserEmail();

        model.addAttribute("medicalRecord", medicalRecord);
        model.addAttribute("authUserEmail", authUserEmail);
        model.addAttribute("citizen", citizen);
        return new ModelAndView("hospital/add-medical-record");
    }


    @PostMapping("/hospital/edit-medical-record-action/{citizenId}/{hospitalId}/{recordId}")
    public ModelAndView editMedicalRecordAction(@PathVariable("citizenId") Long citizenId,
                                                @PathVariable("hospitalId") Long hospitalId,
                                                @PathVariable("recordId") Long recordId,
                                                MedicalRecord medicalRecord, Model model) {


        Citizen citizen = citizenService.findCitizenById(citizenId);


        medicalRecord.setId(recordId);
        hospitalService.saveMedicalRecord(medicalRecord,
                hospitalService.findHospitalById(hospitalId),
                citizen);

        String authUserEmail = userService.getAuthUserEmail();
        model.addAttribute("authUserEmail", authUserEmail);
        model.addAttribute("flag", "Medical Record Updated");
        model.addAttribute("citizen", citizen);

        return new ModelAndView("hospital/add-medical-record");
    }


    @GetMapping("/hospital/hospitals-records")
    public ModelAndView citizensOfHospital(@RequestParam(name = "search", required = false) String key,
                                           Model model) {
        if (key != null && !key.equals("")) {

            model.addAttribute("medicalRecords", hospitalService.searchMedicalRecordsByCitizen(key));
            return new ModelAndView("/hospital/hospitals-records");
        }
        List<MedicalRecord> medicalRecords = hospitalService.findAdmittedCitizensOfHospital();

        model.addAttribute("medicalRecords", medicalRecords);

        return new ModelAndView("/hospital/hospitals-records");
    }

}
