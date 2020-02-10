package com.nafisulbari.eib.controller;

import com.nafisulbari.eib.model.MedicalRecord;
import com.nafisulbari.eib.model.User;
import com.nafisulbari.eib.service.UserService;
import com.nafisulbari.eib.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;



    @GetMapping("/")
    public ModelAndView index() {



        System.out.println(userService.findUserById(1).toString());
        System.out.println("ggg");

        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setRecord("very sick and sad");
        userService.addMedicalRecord(1,medicalRecord);

        System.out.println(userService.findUserById(1).toString());

        //test branch commment

        return new ModelAndView("index");
    }
}
