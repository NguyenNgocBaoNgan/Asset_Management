package DACNPM.asset_management.controller;

import DACNPM.asset_management.model.BorrowId;
import DACNPM.asset_management.model.ListBorrow;
import DACNPM.asset_management.service.FormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

@Controller
public class FormController {
    @Autowired
    FormService formService;


}
