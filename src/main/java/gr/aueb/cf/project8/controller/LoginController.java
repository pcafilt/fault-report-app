package gr.aueb.cf.project8.controller;

import gr.aueb.cf.project8.model.User;
import gr.aueb.cf.project8.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private  UserRepository userRepository;


    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // Assuming login.html is your login page
    }

//    @PostMapping("/login")
//    public String handleLogin(
//            @RequestParam String username,
//            @RequestParam String password,
//            HttpServletRequest request,
//            RedirectAttributes redirectAttributes
//    ) {
//        System.out.println("Attempting login for user: " + username); // Debugging statement
//        Optional<User> optionalUser = userRepository.findByUsername(username);
//        if (optionalUser.isPresent()) {
//            User user = optionalUser.get();
//            System.out.println("User found: " + user.getUsername()); // Debugging statement
//            if (user.getPassword().equals(password)) {
//                System.out.println("Password match for user: " + user.getUsername()); // Debugging statement
//                request.getSession().setAttribute("user", user);
//                return "redirect:/success";
//            } else {
//                System.out.println("Password mismatch for user: " + user.getUsername()); // Debugging statement
//            }
//        } else {
//            System.out.println("User not found: " + username); // Debugging statement
//        }
//        redirectAttributes.addFlashAttribute("error", "Invalid username or password");
//        return "redirect:/failure";
//    }


    @GetMapping("/success")
    public String showSuccessPage() {
        return "success"; // Assuming success.html is your success page
    }

    @GetMapping("/failure")
    public String showFailurePage() {
        return "failure"; // Assuming failure.html is your failure page
    }
}



