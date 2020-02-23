package com.nafisulbari.eib.Controller;


import com.nafisulbari.eib.Model.Citizen;
import com.nafisulbari.eib.Model.MedicalRecord;
import com.nafisulbari.eib.Service.CitizenService;
import com.nafisulbari.eib.Service.HospitalService;
import com.nafisulbari.eib.Service.PoliceStationService;
import com.nafisulbari.eib.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;


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


    @GetMapping("/citizen/medical-record/{id}")
    public ModelAndView profile(@PathVariable(name = "id") Long id,
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
    public ModelAndView editPage(Model model) {


        return new ModelAndView("citizen/request-update");
    }


}
