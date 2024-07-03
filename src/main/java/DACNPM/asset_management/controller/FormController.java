package DACNPM.asset_management.controller;

import DACNPM.asset_management.model.*;
import DACNPM.asset_management.model.response.ListBorrowResponse;
import DACNPM.asset_management.service.FormService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class FormController {
    @Autowired
    FormService formService;

    @GetMapping("/form")
    public String showForm(@SessionAttribute(name = "loggedInAccount", required = false) Account loggedInAccount, Model model, HttpSession session) {
        if (loggedInAccount == null) {
            return "redirect:/dang-nhap";
        }
        model.addAttribute("formData", new ListBorrow(new BorrowId()));
        model.addAttribute("loggedInAccount", loggedInAccount);

        return "ui-forms";
    }

    @PostMapping("/form/request")
    public String requestForm(@ModelAttribute("formData") ListBorrow formData, HttpSession session) {
        formData.setBorrowDate(new Date());
        formData.getId().setStatus(0);
        if (!formService.create(formData)) {
            session.setAttribute("error", FormService.MESS);
            return "redirect:/form";
        }
        session.removeAttribute("error");
        return "redirect:/home";
    }

    @GetMapping("/response")
    public String showResponse(@SessionAttribute(name = "loggedInAccount", required = false) Account loggedInAccount, Model model, HttpSession session) {
        if (loggedInAccount == null) {
            return "redirect:/dang-nhap";
        }
        List<ListBorrowResponse> rs = new ArrayList<>();

        if (loggedInAccount.getRole() == 1) {
            rs = formService.listAllRequestUser(loggedInAccount.getId_account());
        } else {
            rs = formService.listAllRequest();
        }
        model.addAttribute("loggedInAccount", loggedInAccount);
        model.addAttribute("listAllRequest", rs);

        return "view-borrow";

    }

    @GetMapping("/updateStatusListBorrow/{idAsset}/{idAccount}")
    public String updateAsset(@PathVariable("idAsset") int idAsset, @PathVariable("idAccount") int idAccount) {
        formService.update(idAccount, idAsset);
        return "redirect:/response";
    }


}