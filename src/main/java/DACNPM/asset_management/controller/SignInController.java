package DACNPM.asset_management.controller;

import DACNPM.asset_management.model.Account;
import DACNPM.asset_management.service.SignInService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SignInController {

    @Autowired
    SignInService signInService;

    @GetMapping("/dang-nhap")
    public String redirectSignIn(Model model, HttpSession session) {
        if (session.getAttribute("loggedInAccount") != null) {
            return "redirect:/home";
        }
        model.addAttribute("account", new Account());
        return "authentication-login";
    }

    @GetMapping("/login")
    public String newLogin() {
        return "redirect:/dang-nhap";
    }

    @PostMapping("/checkLogin")
    public String checkLogin(@RequestParam("id_account") int id_account, @RequestParam("password") String password, HttpSession session) {
        try {
            Account account = signInService.checkLogin(id_account, password);


            if (account != null) {
                // Thêm loggedInAccount vào session
                session.removeAttribute("error");
                session.setAttribute("loggedInAccount", account);
                return "redirect:/home";
            } else {
                session.setAttribute("error", "Login failed");
                return "redirect:/login";
            }
        } catch (Exception ex) {
            session.setAttribute("error", "Login failed");
            return "redirect:/login";
        }
    }
}
