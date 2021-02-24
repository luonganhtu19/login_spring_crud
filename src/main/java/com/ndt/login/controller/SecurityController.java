package com.ndt.login.controller;

import com.ndt.login.model.Role;
import com.ndt.login.model.User;
import com.ndt.login.model.UserPrinciple;
import com.ndt.login.service.role.RoleService;
import com.ndt.login.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

@Controller
public class SecurityController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;
    @GetMapping("/user")
    public String userPage(){
        return "user";
    }
    @GetMapping("/admin")
    public String adminPage(){
        return "admin";
    }
    @GetMapping("/")
    public String homePage(){
        return "index";
    }
    @GetMapping("/login")
    public ModelAndView getLogin(){
        ModelAndView modelAndView=new ModelAndView();

//        principal == null ?  modelAndView.setViewName("login") : modelAndView.setViewName("index");
        if (userService.getCurrentUser()==null){
            modelAndView.setViewName("login");
        }else
            {
//                User loginedUser=(User) ((Authentication) principal).getPrincipal();
                modelAndView.setViewName("index");
                modelAndView.addObject("user",userService.getCurrentUser());
            }
        return modelAndView;
    }
    @GetMapping("/accessDenied")
    public String accessDenied(){
        return "access-denied";
    }

}
