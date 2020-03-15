package com.nafisulbari.eib.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


/**
 * HomeController is responsible for "/home", "/login" and basic routes
 *
 * @author  Ahmed Nafisul Bari
 */


@Controller
public class HomeController {



    //--------------------Login Home & Basic Controls----------------------------------------------------------------
    //---------------------------------------------------------------------------------------------------------------


    @GetMapping("favicon.ico")
    @ResponseBody
    void returnNoFavicon() {
    }


    @GetMapping("/")
    public ModelAndView index(@RequestParam(name = "search", required = false) String search) {

        if (search != null) {

            return new ModelAndView("redirect:/" + search);
        }
        return new ModelAndView("index");
    }


    @GetMapping("/login")
    public ModelAndView loginPage() {
        return new ModelAndView("login");
    }


    @RequestMapping("/home-detect")
    public ModelAndView homeDetect() {

        return new ModelAndView("redirect:/");
    }





}
