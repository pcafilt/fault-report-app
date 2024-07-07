package gr.aueb.cf.project8.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class BackToFormController {

    @GetMapping("/backToForm")
    public String reportSuccess(Model model, Principal principal) {

        return "redirect:/electricityReg";

    }
}