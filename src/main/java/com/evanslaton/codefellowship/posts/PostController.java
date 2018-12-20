package com.evanslaton.codefellowship.posts;

import com.evanslaton.codefellowship.applicationuser.ApplicationUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class PostController {

    @Autowired
    private PostRepository postRepo;

    // Adds posts
    @RequestMapping(value="/post", method= RequestMethod.POST)
    public RedirectView post(@RequestParam String body, Principal p, Model m) {

        // Gets and formats the date
        Format formatDate = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String date = formatDate.format(new Date());

        Post newPost = new Post(date, body);
        newPost.applicationUser = (ApplicationUser)((UsernamePasswordAuthenticationToken) p).getPrincipal();
        postRepo.save(newPost);
        return new RedirectView("/myprofile");
    }
}
