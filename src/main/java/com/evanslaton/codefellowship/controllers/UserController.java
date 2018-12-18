package com.evanslaton.codefellowship.controllers;

import com.evanslaton.codefellowship.ApplicationUser;
import com.evanslaton.codefellowship.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class UserController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ApplicationUserRepository applicationUserRepo;

    // Displays the home page
    @RequestMapping(value="/", method= RequestMethod.GET)
    public String showHomePage() {
        return "index";
    }

    @RequestMapping(value="/signup", method= RequestMethod.GET)
    public String showSignUpPage() {
        return "sign-up";
    }

    @RequestMapping(value="/perform-signup", method= RequestMethod.POST)
    public RedirectView signUpUser(@RequestParam String firstName,
                                   @RequestParam String lastName,
                                   @RequestParam String dateOfBirth,
                                   @RequestParam String bio,
                                   @RequestParam String username,
                                   @RequestParam String password,
                                   Model userModel) {
        ApplicationUser newUser = new ApplicationUser(firstName, lastName, dateOfBirth, bio, username, bCryptPasswordEncoder.encode(password));
        applicationUserRepo.save(newUser);
        return new RedirectView("/profile/" + newUser.id);
    }

    @RequestMapping(value="/profile/{userId}", method= RequestMethod.GET)
    public String viewProfile(@PathVariable long userId,
                              Model userModel) {
        userModel.addAttribute("user", applicationUserRepo.findById(userId).get());
        return "profile";
    }
}

//albumModel.addAttribute("album", albumRepo.findById(albumId).get());
