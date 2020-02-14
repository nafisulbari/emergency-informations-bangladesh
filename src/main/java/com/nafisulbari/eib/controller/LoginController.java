package com.nafisulbari.eib.controller;

import com.nafisulbari.eib.service.CitizenService;
import com.nafisulbari.eib.service.HospitalService;
import com.nafisulbari.eib.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;



@Controller
public class LoginController {


    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private CitizenService citizenService;

    @Autowired
    private UserService userService;


    @GetMapping("/")
    public ModelAndView index(Model model) {

        model.addAttribute("authenticatedUser", userService.getAuthUserEmail());
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
}
