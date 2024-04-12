package DACNPM.asset_management.controller;

import DACNPM.asset_management.model.Account;
import DACNPM.asset_management.model.Asset;
import DACNPM.asset_management.model.Status;
import DACNPM.asset_management.model.Type;
import DACNPM.asset_management.service.AssetService;

import DACNPM.asset_management.service.StatusService;
import DACNPM.asset_management.service.TypeService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;


import java.util.List;
import java.util.Optional;


@Controller
public class AssetController {

    @Autowired
    AssetService assetService;

    @Resource
    StatusService statusService;
    @Resource
    TypeService typeService;



    @GetMapping("/home")
    public String getAllAsset(@SessionAttribute(name = "loggedInAccount", required = false) Account loggedInAccount, Model model, HttpSession session) {
        if (loggedInAccount == null) {
            return "redirect:/login";
        }
        List<Asset> listAsset= assetService.getAllAssets();
        List<Type> listType=typeService.getAllType();
        model.addAttribute("listAsset", listAsset);
        model.addAttribute("listType",listType);
        model.addAttribute("asset", new Asset());
        model.addAttribute("loggedInAccount", loggedInAccount);

        return "index";
    }
    @ResponseBody
    @GetMapping("/listAsset")
    public List<Asset> getAll(){
        return assetService.getAllAssets();
    }

    @PostMapping("/addNewAsset")
    public String addNewAsset(@ModelAttribute("asset")  Asset asset){
        assetService.addNewAsset(asset);
        return "redirect:/home";
    }

    @GetMapping("/updateAsset/{id}")
    public String getUpdateAsset(@PathVariable("id") int id, Model model) {
        Optional<Asset> optionalAsset  = assetService.findAssetById(id);
        Type oldType = typeService.getTypeByAssetId(id);
        List<Type> listType=typeService.getAllType();
        List<Status> listStatus=statusService.getAllStatus();
        Status oldStatus=statusService.getStatusByAssetId(id);

        model.addAttribute("listType",listType);
        model.addAttribute("oldType",oldType);

        if (optionalAsset.isPresent()) {
            Asset asset = optionalAsset.get();
            model.addAttribute("asset", asset);
        } else {
            model.addAttribute("errorMessage", "Not found asset with ID: " + id);
        }

//        model.addAttribute("listStatus",listStatus);
//        model.addAttribute("oldStatus",oldStatus);
        return "edit-asset";
    }

    @PostMapping("updateAsset/{id}")
    public String updateAsset(@PathVariable("id") int id, @ModelAttribute("asset") Asset asset) throws Exception {
        assetService.updateAsset(id, asset);
        return "redirect:/home";
    }

    @PostMapping("deleteAsset/{id}")
    public String deleteAsset(@PathVariable("id")String id){
        assetService.deleteAsset(Integer.parseInt(id));
        return "redirect:/home";
    }



}


