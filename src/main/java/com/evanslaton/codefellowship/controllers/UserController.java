package com.evanslaton.codefellowship.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    // Displays the home page
    @RequestMapping(value="/", method= RequestMethod.GET)
    public String showHomePage() {
        return "index";
    }

}
