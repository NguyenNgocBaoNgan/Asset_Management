package DACNPM.asset_management.controller;

import DACNPM.asset_management.model.Account;
import DACNPM.asset_management.model.Asset;
import DACNPM.asset_management.model.Type;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Controller
public class RegisterController {
    @GetMapping("/register")
    public String showRegistrationForm(@SessionAttribute( name = "loggedInAccount", required = false) Account loggedInAccount, Model model, HttpSession session) {
        if (loggedInAccount == null) {
            return "redirect:/login";
        }
        if(loggedInAccount.getRole()==1){
            return "redirect:/login";
        }
        model.addAttribute("account", new Account());
        model.addAttribute("loggedInAccount", loggedInAccount);

        return "authentication-register";
    }

}