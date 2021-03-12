package com.springboot.controller;

import com.springboot.model.User;
import com.springboot.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/users")
    public String findAll(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/user-list";
    }

    @GetMapping("/{id}")
    public String showUser(@PathVariable("id") Long id, Model model ){
        model.addAttribute("person", userService.getUserById(id));
//        return "/admin/user";
        return "/strap/user";
    }

    @GetMapping("/user-create")
    public String createUserForm(Model model) {
        model.addAttribute("person", new User());
        return "admin/create";
    }

    @PostMapping("/createpost")
    public String createUser(@ModelAttribute("person") User user) {
        userService.setDefaultRole(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.saveUser(user);
//        return "redirect:/admin/users";
        return "redirect:/strap/adminpage";
    }

//    @GetMapping("/update/{id}")
//    public String updateForm(@PathVariable("id") Long id, Model model) {
//        model.addAttribute("user", userService.getUserById(id));
//        return "/admin/update";
//    }

    @PostMapping("update/{id}")
    public String update(@ModelAttribute User user) {
        userService.setRole(user, "USER");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.saveUser(user);
        return "redirect:/strap/adminpage";
    }
//    @PostMapping("/update")
//    public String update(@ModelAttribute User user) {
//        userService.setRole(user, "USER");
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        userService.saveUser(user);
//        return "redirect:/admin/users";
//    }


    @GetMapping("delete/{id}")
    public String deleteGet(@PathVariable("id") Long id) {
        userService.deleteById(id);
//        return "redirect:/admin/users";
        return "redirect:/strap/adminpage";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/admin/users";
    }

}
