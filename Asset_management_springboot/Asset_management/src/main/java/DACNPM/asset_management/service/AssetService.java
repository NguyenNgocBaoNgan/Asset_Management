package DACNPM.asset_management.service;

import jakarta.annotation.Resource;
import DACNPM.asset_management.model.Asset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.stereotype.Service;
import DACNPM.asset_management.repository.AssetRepository;

import java.util.List;

@Service
public class AssetService {
    @Autowired
    AssetRepository assetRepository;

    public List<Asset> getAllAssets(){
        return assetRepository.getAllAs();
    }




}
