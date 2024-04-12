package DACNPM.asset_management.controller;

import DACNPM.asset_management.model.Account;
import DACNPM.asset_management.model.Asset;
import DACNPM.asset_management.model.DetailAccount;
import DACNPM.asset_management.repository.DetailAccountRepository;
import DACNPM.asset_management.service.AssetService;
import DACNPM.asset_management.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/detailAcc")
public class CreateAccountController {

    @Autowired
    private SignUpService signUpService;


}
