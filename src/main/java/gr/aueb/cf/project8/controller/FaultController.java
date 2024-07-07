package gr.aueb.cf.project8.controller;

import gr.aueb.cf.project8.dto.ReportFaultDTO;
import gr.aueb.cf.project8.model.Fault;
import gr.aueb.cf.project8.service.FaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class FaultController {

    @Autowired
    private FaultService faultService;

    @PostMapping("/submitReport")
    public String submitReport(@ModelAttribute ReportFaultDTO dto, RedirectAttributes redirectAttributes, Principal principal, Model model) {
        String username = principal.getName();
        Fault fault = faultService.saveFault(dto,username);
        redirectAttributes.addFlashAttribute("fault", fault);
        redirectAttributes.addFlashAttribute("photoBase64", fault.getPhotoBase64()); // Explicitly add photoBase64 to flash attributes

        return "redirect:/reportSuccess";
    }

    @GetMapping("/reportSuccess")
    public String reportSuccess(Model model,Principal principal) {

        if (!model.containsAttribute("fault")) {
            return "redirect:/electricityReg";
        }
        Fault fault = (Fault) model.getAttribute("fault");
        model.addAttribute("photoBase64", fault.getPhotoBase64());
        model.addAttribute("username", principal.getName());
        return "reportSuccess";
    }



}
