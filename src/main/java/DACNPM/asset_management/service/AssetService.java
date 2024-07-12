package DACNPM.asset_management.service;

import DACNPM.asset_management.model.Asset;
import DACNPM.asset_management.model.Status;
import DACNPM.asset_management.model.Type;
import DACNPM.asset_management.model.Warehouse;

import DACNPM.asset_management.repository.WarehouseRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import DACNPM.asset_management.repository.AssetRepository;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class AssetService {
    private static final Logger logger = Logger.getLogger(AssetService.class.getName());
    @Autowired
    private WarehouseRepository warehouseRepository;
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

    public List<List<String>> previewFile(MultipartFile file) {
        List<List<String>> previewData = new ArrayList<>();

        try (InputStream inputStream = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            Sheet sheet = workbook.getSheet("assets");
            if (sheet == null) {
                throw new IllegalArgumentException("Sheet 'assets' not found");
            }

            for (Row row : sheet) {
                if (row.getRowNum() < 1) {
                    continue;
                }
                List<String> rowData = new ArrayList<>();
                for (Cell cell : row) {
                    switch (cell.getCellType()) {
                        case STRING:
                            rowData.add(cell.getStringCellValue());
                            break;
                        case NUMERIC:
                            if (DateUtil.isCellDateFormatted(cell)) {
                                rowData.add(cell.getDateCellValue().toString());
                            } else {
                                //
                                // rowData.add(String.valueOf(cell.getNumericCellValue()));
                                rowData.add(String.format("%,.0f", cell.getNumericCellValue()));
                            }

                            break;
                        case BOOLEAN:
                            rowData.add(String.valueOf(cell.getBooleanCellValue()));
                            break;
                        default:
                            rowData.add("");
                    }
                }
                previewData.add(rowData);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return previewData;
    }

    public List<Asset> saveFile(MultipartFile file) {
        List<Asset> assets = new ArrayList<>();
        try (InputStream inputStream = file.getInputStream();
             Workbook workbook = new XSSFWorkbook(inputStream)) {
            Sheet sheet = workbook.getSheet("assets");
            if (sheet == null) {
                throw new IllegalArgumentException("Sheet 'assets' not found");
            }
            for (Row row : sheet) {
                if (row.getRowNum() < 1) {
                    continue;
                }

                Asset asset = new Asset();
                try {
                    asset.setIdAsset((int) getCellValueAsNumeric(row.getCell(0)));
                    asset.setAssetName(getCellValueAsString(row.getCell(1)));
                    asset.setDescription(getCellValueAsString(row.getCell(8)));
                    asset.setStatus(getCellValueAsString(row.getCell(3)).equals("Enable") ? 1 : 0); // Assuming 1 for Enable and 0 for Disable
                    asset.setPurchasePrice((int) getCellValueAsNumeric(row.getCell(6)));
                    asset.setDatePurchase(row.getCell(7).getLocalDateTimeCellValue().toLocalDate());
                    asset.setType( (int) getCellValueAsNumeric(row.getCell(2)));
                    assets.add(asset);

                    Warehouse warehouse = new Warehouse();
                    warehouse.setIdAsset(asset.getIdAsset());
                    warehouse.setStockQuantity((int) getCellValueAsNumeric(row.getCell(4)));
                    warehouse.setUnavailableQuantity((int) getCellValueAsNumeric(row.getCell(5)));

                    warehouseRepository.save(warehouse);
                } catch (Exception e) {
                    logger.severe("Error processing row " + row.getRowNum() + ": " + e.getMessage());
                    throw e;
                }
            }

            // Lưu các asset vào database
            assetRepository.saveAll(assets);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return assets;
    }

    private String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return String.valueOf(cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }

    private double getCellValueAsNumeric(Cell cell) {
        if (cell == null) {
            return 0;
        }
        switch (cell.getCellType()) {
            case STRING:
                try {
                    return Double.parseDouble(cell.getStringCellValue());
                } catch (NumberFormatException e) {
                    return 0;
                }
            case NUMERIC:
                return cell.getNumericCellValue();
            case BOOLEAN:
                return cell.getBooleanCellValue() ? 1 : 0;
            case FORMULA:
                return cell.getNumericCellValue();
            default:
                return 0;
        }
    }



}
