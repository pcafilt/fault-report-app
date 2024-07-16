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
//        List<Fault> faults;
//        if (filter == null || filter.isEmpty()) {
//            faults = faultService.getAllFaults();
//        } else {
//            faults = faultService.getFaultsByFilter(filter);
//        }        model.addAttribute("faults", faults);
//        return "faultManagement";
//    }

    List<Fault> faults;
        if (filter != null && !filter.equals("all")) {
        faults = faultService.getFaultsByFilter(filter);
        System.out.println("Filter applied: " + filter + " | Number of faults: " + faults.size());
    } else {
        faults = faultService.getAllFaults();
        System.out.println("No filter applied | Number of faults: " + faults.size());
    }
        model.addAttribute("faults", faults);
        return "faultManagement";
}


    @PostMapping("/deleteFault")
    public String deleteFault(@RequestParam("id") Long faultId, Principal principal) {
        faultService.deleteFaultById(faultId);

        String username = principal.getName();
//        System.out.println("Current user: " +  userService.findByUsername(username).get().getRoles().get(0).getClass());

        // Check the user's role
        if ( userService.findByUsername(username).get().getRoles().get(0).equals("ADMIN")){
        //if (isUserAdmin(principal.getName())) {
            System.out.println("this is an admin");
            return "redirect:/faultManagement";
        } else {
            System.out.println("this is a simple user");
            return "redirect:/my-reports";
        }
    }



//
//    private boolean isUserAdmin(String username) {
//        Optional<User> user = userService.findByUsername(username);
//        return user.isPresent() && user.get().getRoles().contains("ROLE_ADMIN");
//    }
}
