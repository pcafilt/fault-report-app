package gr.aueb.cf.project8.controller;

import gr.aueb.cf.project8.model.Fault;
import gr.aueb.cf.project8.service.FaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
public class MyReportsController {

    @Autowired
    private FaultService faultService;

    @GetMapping("/my-reports")
    public String getUserReports(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        String username = userDetails.getUsername();
        List<Fault> faults = faultService.findFaultsByUser(username);
        model.addAttribute("faults", faults);
        model.addAttribute("username", username);
        return "my-reports";
    }


    @PostMapping("/deleteFault")
    public String deleteFault(@RequestParam("id") Long faultId) {
        faultService.deleteFaultById(faultId);
        return "redirect:/my-reports";
    }


}
