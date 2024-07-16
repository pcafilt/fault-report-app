package gr.aueb.cf.project8.controller;

import gr.aueb.cf.project8.model.Fault;
import gr.aueb.cf.project8.model.User;
import gr.aueb.cf.project8.service.FaultService;
import gr.aueb.cf.project8.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
public class FaultManagementController {

    @Autowired
    private FaultService faultService;

    @Autowired
    private UserService userService;

    @GetMapping("/faultManagement")
    public String faultManagement(Model model,@RequestParam(required = false) String filter,Principal principal) {

    List<Fault> faults;
        if (filter != null && !filter.equals("all")) {
        faults = faultService.getFaultsByFilter(filter);
    } else {
        faults = faultService.getAllFaults();
    }
        model.addAttribute("faults", faults);
        return "faultManagement";
}

    @PostMapping("/deleteFault")
    public String deleteFault(@RequestParam("id") Long faultId, Principal principal) {
        faultService.deleteFaultById(faultId);

        String username = principal.getName();

        // Checking for the user's role and redirects respectively
        if ( userService.findByUsername(username).get().getRoles().get(0).equals("ADMIN")){
            return "redirect:/faultManagement";}
        else {
            return "redirect:/my-reports";
             }
    }

}
