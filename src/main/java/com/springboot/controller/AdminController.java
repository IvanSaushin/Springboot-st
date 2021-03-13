package com.springboot.controller;

import com.springboot.model.Role;
import com.springboot.model.User;
import com.springboot.service.RoleService;
import com.springboot.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private RoleService roleService;
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
        return "/bootstrap/user";
    }

    @GetMapping("/user-create")
    public String createUserForm(Model model) {
        model.addAttribute("person", new User());
        return "admin/create";
    }

    @PostMapping("/createpost")
    public String createUser(@ModelAttribute("person") User user,
                             @RequestParam(value = "roles[]") String[] roles) {

        Set<Role> roleSet = new HashSet<>();

        for (String r: roles) {
            roleSet.add(roleService.findRoleById(Long.parseLong(r)));
        }
        user.setRoles(roleSet);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.saveUser(user);
        return "redirect:/bootstrap/adminpage";
    }


    @PostMapping("update/{id}")
    public String update(@ModelAttribute User user,
                         @RequestParam(value = "roles[]") String[] roles) {
        Set<Role> roleSet = new HashSet<>();

        for (String r: roles) {
            roleSet.add(roleService.findRoleById(Long.parseLong(r)));
        }
        user.setRoles(roleSet);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.saveUser(user);
        return "redirect:/bootstrap/adminpage";
    }


    @GetMapping("delete/{id}")
    public String deleteGet(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/bootstrap/adminpage";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.deleteById(id);
        return "redirect:/admin/users";
    }

}
