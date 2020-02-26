package com.nafisulbari.eib.Controller;


import com.nafisulbari.eib.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {


    @Autowired
    UserService userService;

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        System.err.println("ErrorController: Error code: "+status.toString());
        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());

            if (statusCode == HttpStatus.NOT_FOUND.value()) {
                model.addAttribute("errorMessage", "ERROR " + HttpStatus.NOT_FOUND.value() + ", Not Found !");
                return "error";
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                model.addAttribute("errorMessage", "ERROR " + HttpStatus.INTERNAL_SERVER_ERROR.value() + ", Internal Server Error !\n Our Engineers are informed !");
                return "error";
            } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
                System.err.println(userService.getAuthUserEmail() + " : Tried to access forbidden " + HttpStatus.FORBIDDEN.value());
                model.addAttribute("errorMessage", "ERROR " + HttpStatus.FORBIDDEN.value() + ", This action is reported for review !");
                return "error";
            }
        }
        model.addAttribute("errorMessage", "Unexpected Error Occurred\n Our Engineers are informed !");
        return "error";
    }


    @Override
    public String getErrorPath() {
        return "/error";
    }
}
