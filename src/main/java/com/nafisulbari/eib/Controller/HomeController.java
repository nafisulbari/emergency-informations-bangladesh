package com.nafisulbari.eib.Controller;

import com.nafisulbari.eib.Model.User;

import com.nafisulbari.eib.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HomeController {


    @Autowired
    private UserService userService;


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
