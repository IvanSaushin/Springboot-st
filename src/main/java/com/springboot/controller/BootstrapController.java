package com.springboot.controller;

import com.springboot.model.User;
import com.springboot.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/strap")
public class BootstrapController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/adminpage")
    public String getAdmin(Model model) {
//        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        model.addAttribute("person", new User());
//        model.addAttribute("currentUser", userDetails);
        return "/strap/adminpage";
    }

    @GetMapping("/loginpage")
    public String login() {
        return "/strap/loginpage";
    }

    @GetMapping("/userpage")
    public String getUser(Model model) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", userDetails);
        return "/strap/userpage";
    }

}
