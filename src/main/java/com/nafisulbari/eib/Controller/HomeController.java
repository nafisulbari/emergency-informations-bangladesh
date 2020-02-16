package com.nafisulbari.eib.controller;

import com.nafisulbari.eib.Storage.FileService;
import com.nafisulbari.eib.service.CitizenService;
import com.nafisulbari.eib.service.HospitalService;
import com.nafisulbari.eib.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class HomeController {


    @Autowired
    private HospitalService hospitalService;

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

        model.addAttribute("citizen", citizenService.findCitizenById((long) id));
        model.addAttribute("authUserRole", userService.getAuthUserRole());

        return new ModelAndView("citizen-info");
    }


}
