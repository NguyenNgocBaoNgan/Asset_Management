package DACNPM.asset_management.controller;

import DACNPM.asset_management.model.Account;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {
    @GetMapping("/logout")
    public String logout(Model model, HttpSession session) {
        if (session.getAttribute("loggedInAccount") == null) {
            return "redirect:/login";
        }
        model.addAttribute("account", new Account());
        session.removeAttribute("error");
        session.removeAttribute("loggedInAccount");
        return "authentication-login";
    }
}
