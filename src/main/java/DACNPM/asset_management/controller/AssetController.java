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
import org.springframework.data.repository.query.Param;
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
    public String getAllAsset(@SessionAttribute(name = "loggedInAccount", required = false) Account loggedInAccount, Model model,@Param("keyword") String keyword, HttpSession session) {
        if (loggedInAccount == null) {
            return "redirect:/login";
        }
        List<Asset> listAsset= assetService.getAllAssets(keyword);
        List<Type> listType=typeService.getAllType();
        model.addAttribute("listAsset", listAsset);
        model.addAttribute("listType",listType);
        model.addAttribute("asset", new Asset());
        model.addAttribute("loggedInAccount", loggedInAccount);
        model.addAttribute("keyword", keyword);


        return "index";
    }
    @ResponseBody
    @GetMapping("/listAsset")
    public List<Asset> getAll(@Param("keyword") String keyword){
        return assetService.getAllAssets(keyword);
    }

    @PostMapping("/addNewAsset")
    public String addNewAsset(@ModelAttribute("asset")  Asset asset){
        assetService.addNewAsset(asset);
        return "redirect:/home";
    }

    @GetMapping("/updateAsset/{id}")
    public String getUpdateAsset(@SessionAttribute(name = "loggedInAccount", required = false) Account loggedInAccount, @PathVariable("id") int id, Model model) {

        if (loggedInAccount == null) {
            return "redirect:/login";
        }
        model.addAttribute("loggedInAccount", loggedInAccount);
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

        model.addAttribute("listStatus",listStatus);
        model.addAttribute("oldStatus",oldStatus);
        return "edit-asset";
    }

    @PostMapping("updateAsset/{id}")
    public String updateAsset(@PathVariable("id") int id, @ModelAttribute("asset") Asset asset) throws Exception {
        assetService.updateAsset(id, asset);
        return "redirect:/home";
    }


    @PostMapping("deleteAsset/{id}")
    public String deleteAsset(@PathVariable("id")String id) throws Exception {
        Optional<Asset> optionalAsset  = assetService.findAssetById(Integer.parseInt(id));
        if (optionalAsset.isPresent()) {
            Asset asset = optionalAsset.get();
            if(asset.getStatus()!=1){
                assetService.deleteAsset(Integer.parseInt(id));
            }else{

            }
        }

        return "redirect:/home";
    }



}


