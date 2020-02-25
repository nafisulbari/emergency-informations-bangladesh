package com.nafisulbari.eib.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class HomeController {




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
