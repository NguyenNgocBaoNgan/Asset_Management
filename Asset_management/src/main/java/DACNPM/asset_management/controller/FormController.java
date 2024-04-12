package DACNPM.asset_management.controller;

import DACNPM.asset_management.model.BorrowId;
import DACNPM.asset_management.model.ListBorrow;
import DACNPM.asset_management.service.FormService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Date;

@Controller
public class FormController {
    @Autowired
    FormService formService;

    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("formData", new ListBorrow(new BorrowId()));
        return "ui-forms";
    }

    @PostMapping("/form/request")
    public String requestForm(@ModelAttribute("formData") ListBorrow formData, HttpSession session) {
        formData.setBorrowDate(new Date());
        formData.setStatus(0);
        if(!formService.create(formData)){
            session.setAttribute("error", FormService.MESS);
            return "redirect:/form";
        }
        session.removeAttribute("error");
        return "redirect:/home";

    }
}
