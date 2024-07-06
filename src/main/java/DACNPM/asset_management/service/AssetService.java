package DACNPM.asset_management.service;

import DACNPM.asset_management.model.Asset;
import DACNPM.asset_management.model.Status;
import DACNPM.asset_management.model.Type;
import DACNPM.asset_management.model.Warehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import DACNPM.asset_management.repository.AssetRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AssetService {
    @Autowired
    AssetRepository assetRepository;
    public List<Asset> getAllAssets(String keyword){
        if (keyword != null) {
            return assetRepository.search(keyword);
        }
        return assetRepository.findAll();
    }

    public List<Asset> getAllAssets(){
        return assetRepository.findAll();
    }
    public Asset addNewAsset(Asset asset){
        return assetRepository.save(asset);
    }

    public Optional<Status> getStatusByAssetId(int id){
        return assetRepository.findStatusByAssetId(id);
    }

    public Optional<Type> getTypeByAssetId(int id){
        return assetRepository.findTypeByAssetId(id);
    }

    public void updateAsset(int id,Asset updatedAsset) throws Exception {
        Optional<Asset> oldAsset = assetRepository.findById(id);
        if (oldAsset.isPresent()) {
            Asset asset = oldAsset.get();
            // Cập nhật thông tin của tài sản với dữ liệu từ updatedAsset
            asset.setAssetName(updatedAsset.getAssetName());
            asset.setStatus(updatedAsset.getStatus());
            asset.setType(updatedAsset.getType());
            asset.setPurchasePrice(updatedAsset.getPurchasePrice());
            asset.setDatePurchase(updatedAsset.getDatePurchase());
            asset.setDescription(updatedAsset.getDescription());
            // Lưu thay đổi vào cơ sở dữ liệu
            assetRepository.save(asset);
        } else {
            // Xử lý trường hợp không tìm thấy tài sản với ID đã cung cấp
            throw new Exception("Asset with id " + id + " not found");
        }
    }

    public Optional<Asset> findAssetById(int id){
        return assetRepository.findById(id);
    }

    public void deleteAsset(int id){
        assetRepository.deleteById(id);
    }




    public List<Asset> listSearch(String keyword) {
        if (keyword != null) {
            return assetRepository.search(keyword);
        }
        return assetRepository.findAll();
    }


}
