package DACNPM.asset_management.service;

import DACNPM.asset_management.model.Asset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import DACNPM.asset_management.repository.AssetRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AssetService {
    @Autowired
    AssetRepository assetRepository;




}
