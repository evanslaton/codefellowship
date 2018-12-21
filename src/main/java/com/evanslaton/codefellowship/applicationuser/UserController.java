package com.evanslaton.codefellowship.applicationuser;

import com.evanslaton.codefellowship.posts.Post;
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
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ApplicationUserRepository applicationUserRepo;

    // Serves the home page
    @RequestMapping(value="/", method= RequestMethod.GET)
    public String showHomePage(Principal p, Model m) {
        getUsername(p, m);
          return "index";
    }

    // Serves the signup page
    @RequestMapping(value="/signup", method= RequestMethod.GET)
    public String showSignUpPage(Principal p, Model m) {
        getUsername(p, m);
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
                                   Model m) {
        ApplicationUser newUser = new ApplicationUser(firstName, lastName, dateOfBirth, bio, username, bCryptPasswordEncoder.encode(password));
        applicationUserRepo.save(newUser);

        // Auto logins new users immediately after creating an account
        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new RedirectView("/myprofile");
    }

    // Serves login page
    @RequestMapping(value="/login", method= RequestMethod.GET)
    public String login(Principal p, Model m) {
        getUsername(p, m);
        return "login";
    }

    // Serves users their profile page
    @RequestMapping(value="/myprofile", method= RequestMethod.GET)
    public String showMyProfile(Principal p, Model m) {
        ApplicationUser currentUser = (ApplicationUser)((UsernamePasswordAuthenticationToken) p).getPrincipal();

        // Gets the current user's posts and if there are any, adds them to the model
        List<Post> posts = applicationUserRepo.findById(currentUser.id).get().posts;
        if (posts.size() > 0) {m.addAttribute("posts", posts);}
        m.addAttribute("user", currentUser);
        return "profile";
    }

    // Serves the profile page of the user whose id = the userId path variable
    @RequestMapping(value="/profile/{userId}", method= RequestMethod.GET)
    public String viewProfile(@PathVariable long userId,
                              Model m) {
        m.addAttribute("user", applicationUserRepo.findById(userId).get());
        return "profile";
    }

   public void getUsername(Principal p, Model m) {
       if (p != null) {
           ApplicationUser currentUser = (ApplicationUser)((UsernamePasswordAuthenticationToken) p).getPrincipal();
           m.addAttribute("user", applicationUserRepo.findById(currentUser.id).get());
       } else {
           m.addAttribute("user", false);
       }
   }
}
