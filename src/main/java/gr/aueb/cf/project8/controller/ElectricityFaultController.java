//package gr.aueb.cf.project8.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.multipart.MultipartFile;
//import gr.aueb.cf.project8.model.ElectricityFault;
//import gr.aueb.cf.project8.service.ElectricityFaultService;
//
//import java.io.IOException;
//
//@Controller
//public class ElectricityFaultController {
//
//    @Autowired
//    private ElectricityFaultService faultService;
//
//    @PostMapping("/submitElectricityReport")
//    public String submitElectricityReport(
//            @RequestParam("location") String location,
//            @RequestParam("faultType") String faultType,
//            @RequestParam("description") String description,
//            @RequestParam("observationTime") String observationTime,
//            @RequestParam(value = "contactInfo", required = false) String contactInfo,
//            @RequestParam(value = "photo", required = false) MultipartFile photo) {
//
//        ElectricityFault fault = new ElectricityFault();
//        fault.setLocation(location);
//        fault.setFaultType(faultType);
//        fault.setDescription(description);
//        fault.setObservationTime(observationTime);
//        fault.setContactInfo(contactInfo);
//
//        if (photo != null && !photo.isEmpty()) {
//            try {
//                fault.setPhoto(photo.getBytes());
//            } catch (IOException e) {
//                e.printStackTrace();
//                // Handle the error, maybe add a message to the model to show on the error page
//                return "redirect:/failure";
//            }
//        }
//
//        faultService.saveFault(fault);
//
//        return "redirect:/success";
//    }
//}



//
//package gr.aueb.cf.project8.controller;
//
//import gr.aueb.cf.project8.dto.ReportElectricityFaultDTO;
//import gr.aueb.cf.project8.model.ElectricityFault;
//import gr.aueb.cf.project8.repository.ElectricityFaultRepository;
//import gr.aueb.cf.project8.service.ElectricityFaultService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//
//@Controller
//public class ElectricityFaultController {
//
//    @Autowired
//    private ElectricityFaultService faultService;
//
//    @PostMapping("/submitElectricityReport")
//    public String submitElectricityReport(@ModelAttribute ReportElectricityFaultDTO dto) {
//         faultService.saveFault(dto);
//
//        return "redirect:/reportSuccess";
//    }
//
//    @GetMapping("/reportSuccess")
//    public String reportSuccess(Model model) {
//        return "reportSuccess";
//    }
//
//
//}



package gr.aueb.cf.project8.controller;

import gr.aueb.cf.project8.dto.ReportElectricityFaultDTO;
import gr.aueb.cf.project8.model.ElectricityFault;
import gr.aueb.cf.project8.service.ElectricityFaultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class ElectricityFaultController {

    @Autowired
    private ElectricityFaultService faultService;

    @PostMapping("/submitElectricityReport")
    public String submitElectricityReport(@ModelAttribute ReportElectricityFaultDTO dto, RedirectAttributes redirectAttributes, Principal principal, Model model) {
        String username = principal.getName();
        ElectricityFault fault = faultService.saveFault(dto,username);
        redirectAttributes.addFlashAttribute("fault", fault);
        redirectAttributes.addFlashAttribute("photoBase64", fault.getPhotoBase64()); // Explicitly add photoBase64 to flash attributes

        return "redirect:/reportSuccess";
    }

    @GetMapping("/reportSuccess")
    public String reportSuccess(Model model,Principal principal) {


        if (!model.containsAttribute("fault")) {
            return "redirect:/reportFault";
        }
        ElectricityFault fault = (ElectricityFault) model.getAttribute("fault");
        model.addAttribute("photoBase64", fault.getPhotoBase64());
        model.addAttribute("username", principal.getName());
        return "reportSuccess";
    }

    @GetMapping("/reportFault")
    public String showReportForm(Model model) {
        model.addAttribute("reportElectricityFaultDTO", new ReportElectricityFaultDTO());
        return "electricityReg";
    }


//    @GetMapping("/backToMainMenu")
//    public String backToMainMenu() {
//        return "success"; // Assuming success.html is your main menu page
//    }
}
