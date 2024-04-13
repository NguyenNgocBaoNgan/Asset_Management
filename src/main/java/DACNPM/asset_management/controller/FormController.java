package DACNPM.asset_management.controller;

import DACNPM.asset_management.model.*;
import DACNPM.asset_management.service.FormService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class FormController {
    @Autowired
    FormService formService;

    @GetMapping("/form")
    public String showForm(@SessionAttribute(name = "loggedInAccount", required = false) Account loggedInAccount, Model model, HttpSession session) {
        if (loggedInAccount == null) {
            return "redirect:/login";
        }
        model.addAttribute("formData", new ListBorrow(new BorrowId()));
        return "ui-forms";
    }

    @GetMapping("/response")
    public String showResponse(@SessionAttribute(name = "loggedInAccount", required = false) Account loggedInAccount, Model model, HttpSession session) {
        if (loggedInAccount == null) {
            return "redirect:/login";
        }
        if(loggedInAccount.getRole() == 1){
            return "redirect:/home";
        }else {
            List<ListBorrow> listAllRequest = formService.listAllRequest();
            Map<Integer, String> map = new HashMap<Integer, String>();
            for (ListBorrow l : listAllRequest) {
                map.put(l.getId().getIdAsset(), formService.nameAsset(l.getId().getIdAsset()));
            }
            model.addAttribute("loggedInAccount", loggedInAccount);
            model.addAttribute("listAllRequest", listAllRequest);
            model.addAttribute("map", map);
            return "view-borrow";
        }
    }
    @GetMapping("/updateStatusListBorrow/{idAsset}/{idAccount}")
    public String updateAsset(@PathVariable("idAsset") int idAsset,@PathVariable("idAccount") int idAccount) {
        formService.update(idAccount,idAsset);
        return "redirect:/home";
    }

    @PostMapping("/form/request")
    public String requestForm(@ModelAttribute("formData") ListBorrow formData, HttpSession session) {
        formData.setBorrowDate(new Date());
        formData.getId().setStatus(0);
        if(!formService.create(formData)){
            session.setAttribute("error", FormService.MESS);
            return "redirect:/form";
        }
        session.removeAttribute("error");
        return "redirect:/home";
    }
}