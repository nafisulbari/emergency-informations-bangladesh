package com.nafisulbari.eib.controller;

import com.nafisulbari.eib.model.Citizen;
import com.nafisulbari.eib.model.Hospital;
import com.nafisulbari.eib.model.MedicalRecord;
import com.nafisulbari.eib.service.CitizenService;
import com.nafisulbari.eib.service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LoginController {


    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private CitizenService citizenService;




    @GetMapping("/")
    public ModelAndView index() {

        Citizen citizen = new Citizen();
        citizen.setName("jack");
        citizen.setAge(1);
        citizenService.saveCitizen(citizen);
        System.out.println(citizenService.findCitizenById(1L).toString());

        Hospital hospital =  new Hospital();
        hospital.setName("hos");
        hospital.setAddress("ggg");
        hospitalService.saveHospital(hospital);
        System.out.println(hospitalService.findHospitalById(2L));


        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setTitle("Sick af");
        hospitalService.saveMedicalRecord(medicalRecord,hospital,citizen);

        MedicalRecord medicalRecord1 = hospitalService.findMedicalRecordById(1L);
        System.out.println("------Med---------");
        System.out.println(medicalRecord1.toString());
        System.out.println(medicalRecord1.getCitizen().toString());
        Citizen citizen1 =citizenService.findCitizenById(1L);
        System.out.println(citizen1.toString());



        //citizenService.deleteCitizenById(1L);
        //System.out.println(citizenService.findCitizenById(1L).toString());





        return new ModelAndView("index");
    }
}
