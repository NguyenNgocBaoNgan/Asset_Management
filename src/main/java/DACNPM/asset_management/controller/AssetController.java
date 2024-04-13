package DACNPM.asset_management.controller;

import DACNPM.asset_management.model.*;
import DACNPM.asset_management.service.AssetService;

import DACNPM.asset_management.service.StatusService;
import DACNPM.asset_management.service.TypeService;
import DACNPM.asset_management.service.WarehouseService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Controller
public class AssetController {

    @Autowired
    AssetService assetService;

    @Resource
    StatusService statusService;
    @Resource
    TypeService typeService;

    @Resource
    WarehouseService warehouseService;



    @GetMapping("/home")
    public String getAllAsset(@SessionAttribute(name = "loggedInAccount", required = false) Account loggedInAccount, Model model, HttpSession session) {
        if (loggedInAccount == null) {
            return "redirect:/login";
        }

        List<Asset> listAsset= assetService.getAllAssets();
        Map<Integer, Warehouse> warehouseMap = new HashMap<>();

        for(Asset asset:listAsset){
            Optional<Warehouse> warehouseOptional=warehouseService.getQuantityByAssetId(asset.getIdAsset());
            if(warehouseOptional.isPresent()){
                Warehouse warehouse=warehouseOptional.get();
                warehouseMap.put(asset.getIdAsset(), warehouse); // Store warehouse info by assetId
            }
        }
        List<Type> listType=typeService.getAllType();
        model.addAttribute("assetWarehouseMap", warehouseMap);
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
    public String addNewAsset(@ModelAttribute("asset")  Asset asset,@RequestParam("quantity") int quantity) throws Exception {
        asset.setStatus(0);
        assetService.addNewAsset(asset);

        warehouseService.initWarehouse(asset.getIdAsset(),quantity,0);
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
        Optional<Warehouse> warehouseOptional=warehouseService.getQuantityByAssetId(id);
        if(warehouseOptional.isPresent()){
            Warehouse warehouse=warehouseOptional.get();
            model.addAttribute("warehouse",warehouse);
        }

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
    public String updateAsset(@PathVariable("id") int id, @ModelAttribute("asset") Asset asset,@RequestParam("newStockQuantity") int newQuantity,@RequestParam("newUnavailable") int unavalQuantity) throws Exception {
        assetService.updateAsset(id, asset);
        warehouseService.updateQuantity(id,newQuantity,unavalQuantity);
        return "redirect:/home";
    }


    @PostMapping("deleteAsset/{id}")
    public String deleteAsset(@PathVariable("id")String id) throws Exception {
        Optional<Asset> optionalAsset  = assetService.findAssetById(Integer.parseInt(id));
        if (optionalAsset.isPresent()) {
            Asset asset = optionalAsset.get();
            assetService.deleteAsset(Integer.parseInt(id));
        }

        return "redirect:/home";
    }



}