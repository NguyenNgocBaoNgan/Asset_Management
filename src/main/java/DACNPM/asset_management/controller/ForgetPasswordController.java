package DACNPM.asset_management.controller;

import DACNPM.asset_management.service.ForgetPasswordService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ForgetPasswordController {

    @Autowired
    private ForgetPasswordService forgetPasswordService;

    @GetMapping("/forgot-password")
    public String showForgotPasswordForm(Model model,HttpSession session) {
       // session.removeAttribute("error");

        return "forgot-password";
    }

    @PostMapping("/forgot-password")
    public String processForgotPassword(@RequestParam("email") String email, HttpSession session) {
        try {
            session.removeAttribute("error");
            forgetPasswordService.processForgotPassword(email);
            session.setAttribute("message", "A temporary password has been sent to your email");
        } catch (Exception e) {
            session.removeAttribute("message");
            session.setAttribute("error", "Error: " + e.getMessage());
        }
        return "redirect:/forgot-password";
    }
}
