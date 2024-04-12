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



}


