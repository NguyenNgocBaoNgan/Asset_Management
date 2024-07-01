package DACNPM.asset_management.service;

import DACNPM.asset_management.model.Warehouse;
import DACNPM.asset_management.repository.WarehouseRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WarehouseService {
    @Resource
    WarehouseRepository warehouseRepository;

    public Optional<Warehouse> getQuantityByAssetId(int id){
        return warehouseRepository.findById(id);
    }

    public void initWarehouse(int id,int stockQuantity, int unavailableQuantity) throws Exception {
            warehouseRepository.save(new Warehouse(id,stockQuantity,unavailableQuantity));
    }

    public  void updateQuantity(int id,int stockQuantity, int unavailQuantity){
        warehouseRepository.save(new Warehouse(id,stockQuantity,unavailQuantity));
    }

}
