package com.nafisulbari.eib.Controller;

import com.nafisulbari.eib.Model.Citizen;
import com.nafisulbari.eib.Model.MedicalRecord;
import com.nafisulbari.eib.Service.CitizenService;
import com.nafisulbari.eib.Service.HospitalService;
import com.nafisulbari.eib.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class HospitalController {

    @InitBinder
    private void dateBinder(WebDataBinder binder) {
        //The date format to parse or output your dates
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //Create a new CustomDateEditor
        CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
        //Register it as custom editor for the Date type
        binder.registerCustomEditor(Date.class, editor);
    }


    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private CitizenService citizenService;

    @Autowired
    private UserService userService;


    @GetMapping("/hospital/{citizenId}/add-medical-record")
    public ModelAndView addMedicalRecordPage(@PathVariable("citizenId") Long citizenId, Model model) {

        Citizen citizen = citizenService.findCitizenById(citizenId);
        model.addAttribute("citizenId", citizenId);
        model.addAttribute("citizen", citizen);
        return new ModelAndView("hospital/add-medical-record");
    }


    @PostMapping("/hospital/{citizenId}/add-medical-record-action")
    public ModelAndView addMedicalRecordAction(@PathVariable("citizenId") Long citizenId, MedicalRecord medicalRecord, Model model) {

        Citizen citizen = citizenService.findCitizenById(citizenId);

        hospitalService.saveMedicalRecord(medicalRecord,
                hospitalService.findHospitalByEmail(userService.getAuthUserEmail()),
                citizen);

        model.addAttribute("flag", "Medical Record Saved");
        model.addAttribute("citizen", citizen);

        return new ModelAndView("hospital/add-medical-record");
    }


    @GetMapping("/hospital/edit-medical-record/{id}/{citizenId}")
    public ModelAndView editMedicalRecord(@PathVariable("id") Long id,
                                          @PathVariable("citizenId") Long citizenId,
                                          Model model) {

        Citizen citizen = citizenService.findCitizenById(citizenId);

        MedicalRecord medicalRecord = hospitalService.findMedicalRecordById(id);
        if (medicalRecord == null) {
            model.addAttribute("flag", "You do not have access to view this record");
            return new ModelAndView("hospital/add-medical-record");
        }

        model.addAttribute("citizenId", citizenId);
        model.addAttribute("medicalRecord", medicalRecord);
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

        model.addAttribute("citizenId", medicalRecord.getCitizen().getId());
        model.addAttribute("flag", "Medical Record Updated");
        model.addAttribute("citizen", citizen);
        return new ModelAndView("hospital/add-medical-record");
    }


}
