package DACNPM.asset_management.controller;

import DACNPM.asset_management.model.Account;
import DACNPM.asset_management.model.DetailAccount;
import DACNPM.asset_management.service.SignUpService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CreateAccountController {

    @Autowired
    private SignUpService signUpService;

    @PostMapping("/detailAcc/register")
    public String registerUser(@ModelAttribute DetailAccount da, @ModelAttribute Account acc, HttpSession session, RedirectAttributes redirectAttributes) {
        try {
            if (signUpService.emailExists(da.getMail())) {
                redirectAttributes.addFlashAttribute("error", "Email đã tồn tại. Vui lòng nhập email khác!");
                return "redirect:/register";
            }
            if (da.getRole() != 1 && da.getRole() != 0) {
                redirectAttributes.addFlashAttribute("error", "Nhập role sai, chỉ có thể nhập 0 (manager) hoặc 1 (employee).");
                return "redirect:/register";
            }
            signUpService.register(da, acc);
        } catch (RuntimeException | MessagingException e) {
            redirectAttributes.addFlashAttribute("error", "Vui lòng nhập đúng thông tin.");
            return "redirect:/register";
        }
        return "redirect:/register";
    }
}
