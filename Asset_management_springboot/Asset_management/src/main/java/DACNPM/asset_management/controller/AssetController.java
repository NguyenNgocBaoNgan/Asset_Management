package DACNPM.asset_management.controller;

import DACNPM.asset_management.model.Asset;
import DACNPM.asset_management.service.AssetService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.List;
@Controller
public class AssetController {

    @Autowired
    AssetService assetService;

    @GetMapping("/home")
    public String getAllAsset(Model model){
        List<Asset> allAsset= assetService.getAllAssets();
        model.addAttribute("model",allAsset);
        return "index";
    }
}
