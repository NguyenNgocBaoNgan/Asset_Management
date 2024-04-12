package DACNPM.asset_management.controller;

import DACNPM.asset_management.model.Account;
import DACNPM.asset_management.model.Asset;
import DACNPM.asset_management.service.AssetService;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;
@Controller
public class AssetController {

    @Autowired
    AssetService assetService;

    @GetMapping("/home")
    public String getAllAsset(@SessionAttribute(name = "loggedInAccount", required = false) Account loggedInAccount, Model model, HttpSession session) {
        if (loggedInAccount == null) {
            return "redirect:/login";
        }
        List<Asset> allAsset= assetService.getAllAssets();
        model.addAttribute("model",allAsset);
        model.addAttribute("loggedInAccount", loggedInAccount);

        return "index";
    }
}
