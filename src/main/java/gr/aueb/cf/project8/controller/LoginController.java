package gr.aueb.cf.project8.controller;

import gr.aueb.cf.project8.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.security.Principal;

@Controller
public class LoginController {

    @Autowired
    private  UserRepository userRepository;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }


    @GetMapping("/electricityReg")
    public String showElectricityForm(Principal principal, Model model) {
        String temp = principal.getName();
        model.addAttribute("username",temp);
        return "electricityReg";
    }

}



