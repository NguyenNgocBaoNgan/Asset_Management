package DACNPM.asset_management.controller;

import DACNPM.asset_management.model.Account;
import DACNPM.asset_management.model.Asset;
import DACNPM.asset_management.model.DetailAccount;
import DACNPM.asset_management.repository.DetailAccountRepository;
import DACNPM.asset_management.service.AssetService;
import DACNPM.asset_management.service.SignUpService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class CreateAccountController {

    @Autowired
    private SignUpService signUpService;

    @PostMapping("/detailAcc/register")
    public String registerUser(@ModelAttribute DetailAccount da, Account acc, HttpSession session) {
        try {
            if (da.getRole() != 1 && da.getRole() != 0) {
                session.setAttribute("error", "Nhập role sai, chỉ có thể nhập 0 (manager) hoặc 1 (employee).");
                return "redirect:/register";
//            } if (!signUpService.isValidDateFormat(da.getDayOfBirth())) {
//                session.setAttribute("error", "Sai định dạng ngày sinh.");
//                return "redirect:/register";
            } else {
                session.removeAttribute("error");
                signUpService.register(da, acc);
            }
        } catch (RuntimeException e) {
            session.setAttribute("error", "Vui lòng điền đầy đủ thông tin.");
            return "redirect:/register";
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/register";
    }



}