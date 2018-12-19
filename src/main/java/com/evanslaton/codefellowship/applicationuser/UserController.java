package com.evanslaton.codefellowship.applicationuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.ArrayList;

@Controller
public class UserController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ApplicationUserRepository applicationUserRepo;

    // Serves the home page
    @RequestMapping(value="/", method= RequestMethod.GET)
    public String showHomePage(Principal principal, Model userModel) {
        if (principal != null) {
            userModel.addAttribute("user", principal);
        }
        return "index";
    }

    // Serves the signup page
    @RequestMapping(value="/signup", method= RequestMethod.GET)
    public String showSignUpPage(Principal principal, Model userModel) {
        ApplicationUser applicationUser = new ApplicationUser();
        userModel.addAttribute("user", applicationUser);
        return "sign-up";
    }

    // Creates a new users, logs that user in and redirects that user to /profile/{userId}
    @RequestMapping(value="/signup", method= RequestMethod.POST)
    public RedirectView signUpUser(@RequestParam String firstName,
                                   @RequestParam String lastName,
                                   @RequestParam String dateOfBirth,
                                   @RequestParam String bio,
                                   @RequestParam String username,
                                   @RequestParam String password,
                                   Model userModel) {
        ApplicationUser newUser = new ApplicationUser(firstName, lastName, dateOfBirth, bio, username, bCryptPasswordEncoder.encode(password));
        applicationUserRepo.save(newUser);

        // Auto logins new users so they don't have to login in immediately after creating an account
        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new RedirectView("/myprofile");
    }

    // Serves login page
    @RequestMapping(value="/login", method= RequestMethod.GET)
    public String login() {
        return "login";
    }

    // Serves users their profile page
    @RequestMapping(value="/myprofile", method= RequestMethod.GET)
    public String showMyProfile(Principal principal, Model userModel) {
        userModel.addAttribute("user", ((UsernamePasswordAuthenticationToken) principal).getPrincipal());
        return "profile";
    }

//     Serves a users their profile page
//    @RequestMapping(value="/profile/{userId}", method= RequestMethod.GET)
//    public String viewProfile(@PathVariable long userId,
//                              Model userModel) {
//        userModel.addAttribute("user", applicationUserRepo.findById(userId).get());
//        return "profile";
//    }
}
