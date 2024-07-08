/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 100428 (10.4.28-MariaDB)
 Source Host           : localhost:3306
 Source Schema         : asset_management

 Target Server Type    : MySQL
 Target Server Version : 100428 (10.4.28-MariaDB)
 File Encoding         : 65001

 Date: 08/07/2024 18:24:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`  (
  `id_account` int NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `role` int NULL DEFAULT NULL,
  PRIMARY KEY (`id_account`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES (123456, '12345678', 0);
INSERT INTO `account` VALUES (365646, '(TH6L-v5', 0);
INSERT INTO `account` VALUES (507859, 'dXyqjVlU', 0);
INSERT INTO `account` VALUES (741152, 'IPQq?m.<', 1);
INSERT INTO `account` VALUES (921976, 'VE8Nh?&W', 0);
INSERT INTO `account` VALUES (938413, 'HF7kd?ID', 1);

-- ----------------------------
-- Table structure for asset
-- ----------------------------
DROP TABLE IF EXISTS `asset`;
CREATE TABLE `asset`  (
  `id_asset` int NOT NULL AUTO_INCREMENT,
  `asset_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `id_status` int NOT NULL,
  `purchase_price` int NULL DEFAULT NULL,
  `date_purchase` date NULL DEFAULT NULL,
  `id_type` int NULL DEFAULT NULL,
  PRIMARY KEY (`id_asset`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11112 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of asset
-- ----------------------------
INSERT INTO `asset` VALUES (5, 'Máy tính MSI 15.6 inch', 'Ram: 8G\r\nCPU: siêu sịn mịn \r\nChỉ có tổng giám đốc trở lên mới có thể sử dụng và bắt buộc trả trong vòng 7 ngày tính từ ngày bắt đầu mượn. Nếu vượt hạn mượn thì sẽ phải trả phí  10% giá trị của đồ vật', 0, 20000000, '2024-04-16', 3);
INSERT INTO `asset` VALUES (12, 'Ghế Công Thái Học', 'Ghế Công Thái Học ', 0, 500000, '2024-04-16', 2);
INSERT INTO `asset` VALUES (16, 'Bàn Chữ K', '1m2x1m4', 0, 500000, '2024-04-16', 2);
INSERT INTO `asset` VALUES (18, 'Bàn Chữ U', '1m2x1m4', 0, 120000, '2024-04-24', 1);
INSERT INTO `asset` VALUES (19, 'Ghế Sofa', '1m2x1m4', 0, 9000000, '2024-05-02', 1);
INSERT INTO `asset` VALUES (20, 'Bàn Kính', '1m8x2m', 0, 4000000, '2024-05-02', 1);
INSERT INTO `asset` VALUES (30, 'Ghế giám đốc', '545', 0, 4000000, '2024-04-20', 1);
INSERT INTO `asset` VALUES (11111, 'Laptop DELL 14 inch', 'Ram: 8G\r\nCPU: siêu sịn mịn \r\nChỉ có tổng giám đốc trở lên mới có thể sử dụng và bắt buộc trả trong vòng 7 ngày tính từ ngày bắt đầu mượn. Nếu vượt hạn mượn thì sẽ phải trả phí  10% giá trị của đồ vật', 0, 12000000, '2024-04-12', 2);

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `account_id` int NULL DEFAULT NULL,
  `asset_id` int NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `created_at` datetime(6) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 56 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (1, 123456, 18, 'ồ đẹp thế', '2024-07-10 15:47:44.000000');
INSERT INTO `comment` VALUES (16, 365646, 16, 'no khong hien thi kia', '2024-07-07 17:23:15.000000');
INSERT INTO `comment` VALUES (49, 123456, 5, 'Đồ dùng tốt', '2024-07-08 15:00:03.000000');
INSERT INTO `comment` VALUES (51, 123456, 11111, 'ghế gỗ tốt', '2024-07-08 17:06:30.000000');
INSERT INTO `comment` VALUES (52, 123456, 20, 'ban vuong tốt', '2024-07-08 17:06:30.000000');
INSERT INTO `comment` VALUES (53, 123456, 12, 'Ghế Công Thái Học đang sử dụng rất ok', '2024-07-08 17:06:30.000000');
INSERT INTO `comment` VALUES (54, 123456, 12, 'Sử dụng tốt lắm mọi người, nên mượn', '2024-07-08 17:06:30.000000');
INSERT INTO `comment` VALUES (55, 123456, 12, 'Ghế Công Thái Học mọi người nhớ nâng phần phía sau lên để k bị đau lưng nhé', '2024-07-08 18:17:40.000000');

-- ----------------------------
-- Table structure for detail_account
-- ----------------------------
DROP TABLE IF EXISTS `detail_account`;
CREATE TABLE `detail_account`  (
  `id_account` int NOT NULL,
  `first_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `last_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `day_of_birth` datetime(6) NULL DEFAULT NULL,
  `mail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `role` int NULL DEFAULT NULL,
  PRIMARY KEY (`id_account`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of detail_account
-- ----------------------------
INSERT INTO `detail_account` VALUES (123456, 'Bao', 'Nguyen', '2002-02-01 10:48:14.000000', 'bao@gmail.com', 1);
INSERT INTO `detail_account` VALUES (365646, 'nhi', 'phuongnhi', '2000-08-08 00:00:00.000000', '20130353@st.hcmuaf.edu.vn', 0);
INSERT INTO `detail_account` VALUES (507859, 'nhi', 'phuongnhi', '2000-08-08 00:00:00.000000', '20130353@st.hcmuaf.edu.vn', 0);
INSERT INTO `detail_account` VALUES (741152, 'nhi', 'phuongnhi', '2000-08-08 00:00:00.000000', '20130353@st.hcmuaf.edu.vn', 1);
INSERT INTO `detail_account` VALUES (921976, 'Ngan', 'Nguyen', '2000-09-12 00:00:00.000000', 'nganluvjb@gmail.com', 0);
INSERT INTO `detail_account` VALUES (938413, 'Nhi', 'Nguyen', '2000-07-19 00:00:00.000000', 'nhi252002@gmail.com', 1);

-- ----------------------------
-- Table structure for list_borrow
-- ----------------------------
DROP TABLE IF EXISTS `list_borrow`;
CREATE TABLE `list_borrow`  (
  `id_account` int NOT NULL,
  `id_asset` int NOT NULL,
  `borrow_date` datetime NOT NULL,
  `return_date` datetime NULL DEFAULT NULL,
  `quantity` int NULL DEFAULT NULL,
  `status` int NULL DEFAULT NULL,
  INDEX `FKhkxmqm4xtfovb3fhe8kemrf0k`(`id_asset` ASC) USING BTREE,
  INDEX `FK2vivh0ittj555jcyvc1gkphyu`(`id_account` ASC) USING BTREE,
  INDEX `FK2v73k00t57lnr402gn9ho9jpq`(`status` ASC) USING BTREE,
  CONSTRAINT `FK2v73k00t57lnr402gn9ho9jpq` FOREIGN KEY (`status`) REFERENCES `status` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK2vivh0ittj555jcyvc1gkphyu` FOREIGN KEY (`id_account`) REFERENCES `detail_account` (`id_account`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FKhkxmqm4xtfovb3fhe8kemrf0k` FOREIGN KEY (`id_asset`) REFERENCES `asset` (`id_asset`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of list_borrow
-- ----------------------------
INSERT INTO `list_borrow` VALUES (938413, 16, '2024-04-13 15:50:47', '2024-07-03 16:50:57', 1, 1);
INSERT INTO `list_borrow` VALUES (938413, 5, '2024-04-13 15:50:47', '2024-07-03 16:53:27', 1, 1);
INSERT INTO `list_borrow` VALUES (123456, 11111, '2024-04-13 15:50:47', '2024-04-13 15:51:14', 1, 1);
INSERT INTO `list_borrow` VALUES (123456, 20, '2024-07-07 12:43:18', '2024-07-07 12:53:31', 2, 1);
INSERT INTO `list_borrow` VALUES (123456, 20, '2024-07-07 12:54:04', '2024-07-07 12:54:43', 2, 1);
INSERT INTO `list_borrow` VALUES (123456, 12, '2024-07-08 15:29:28', NULL, 2, 0);

-- ----------------------------
-- Table structure for status
-- ----------------------------
DROP TABLE IF EXISTS `status`;
CREATE TABLE `status`  (
  `id` int NOT NULL,
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of status
-- ----------------------------
INSERT INTO `status` VALUES (0, 'Enable');
INSERT INTO `status` VALUES (1, 'Disable');
INSERT INTO `status` VALUES (2, 'cũ rồi');

-- ----------------------------
-- Table structure for type
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type`  (
  `id_type` int NOT NULL,
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `description` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id_type`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of type
-- ----------------------------
INSERT INTO `type` VALUES (1, 'Trang thiết bị', 'Trang thiết bị');
INSERT INTO `type` VALUES (2, 'Nội thất', 'Nội thất');
INSERT INTO `type` VALUES (3, 'Điện tử', 'Điện tử');

-- ----------------------------
-- Table structure for warehouse
-- ----------------------------
DROP TABLE IF EXISTS `warehouse`;
CREATE TABLE `warehouse`  (
  `id_asset` int NOT NULL AUTO_INCREMENT,
  `stock_quantity` int NULL DEFAULT NULL,
  `unavailable_quantity` int NULL DEFAULT NULL,
  PRIMARY KEY (`id_asset`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11112 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of warehouse
-- ----------------------------
INSERT INTO `warehouse` VALUES (5, 2, 0);
INSERT INTO `warehouse` VALUES (12, 52, 2);
INSERT INTO `warehouse` VALUES (16, 5, 0);
INSERT INTO `warehouse` VALUES (18, 5, 0);
INSERT INTO `warehouse` VALUES (19, 8, 0);
INSERT INTO `warehouse` VALUES (20, 4, 0);
INSERT INTO `warehouse` VALUES (30, 200, 10);
INSERT INTO `warehouse` VALUES (64, 65, 0);
INSERT INTO `warehouse` VALUES (11111, 500000, 0);

-- ----------------------------
-- Procedure structure for checkLogin
-- ----------------------------
DROP PROCEDURE IF EXISTS `checkLogin`;
delimiter ;;
CREATE PROCEDURE `checkLogin`(IN `ID` INT)
BEGIN
    DECLARE rowCount INT;
    
    -- Check if ID parameter is not null
    IF ID IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'ID cannot be null';
    END IF;

    -- Count the number of rows with matching ID
    SELECT COUNT(*) INTO rowCount
    FROM account
    WHERE account.id_account = ID;

    -- If no matching record found, return an error
    IF rowCount = 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Invalid ID';
    END IF;

    -- If a matching record found, return the result
    SELECT * FROM account WHERE account.id_account = ID;

END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for check_quantity
-- ----------------------------
DROP PROCEDURE IF EXISTS `check_quantity`;
delimiter ;;
CREATE PROCEDURE `check_quantity`(IN `id_asset` INT)
BEGIN
    DECLARE stock INT; -- trong kho có
    DECLARE borrow INT; -- SL đã mượn
    DECLARE rs INT; -- SL đã mượn

    -- Calculate the total quantity borrowed for the given asset
    SELECT SUM(quantity) INTO borrow FROM list_borrow WHERE list_borrow.id_asset = `id_asset` AND status = 0;

    -- Retrieve the available stock quantity for the given asset
    SELECT stock_quantity INTO stock FROM warehouse WHERE warehouse.id_asset = `id_asset` LIMIT 1;

    -- Subtract the borrowed quantity from the available quantity
    SET rs = stock - borrow;

    SELECT rs AS rs;
END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for getAccountInfo
-- ----------------------------
DROP PROCEDURE IF EXISTS `getAccountInfo`;
delimiter ;;
CREATE PROCEDURE `getAccountInfo`(IN `ID` int)
BEGIN
 select * from detail_account WHERE detail_account.id_account= ID;

END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for getListBorrow
-- ----------------------------
DROP PROCEDURE IF EXISTS `getListBorrow`;
delimiter ;;
CREATE PROCEDURE `getListBorrow`()
BEGIN
    -- Select the list of borrowed assets for all users
    SELECT d.id_account, d.last_name, d.first_name, t.`value` AS asset_type, a.asset_name, SUM(l.quantity) AS total_borrowed
    FROM detail_account d
    INNER JOIN list_borrow l ON d.id_account = l.id_account
    INNER JOIN asset a ON l.id_asset = a.id_asset
    INNER JOIN type t ON a.id_type = t.id_type
    WHERE l.return_date IS NULL
    GROUP BY d.id_account, l.id_asset;

END
;;
delimiter ;

-- ----------------------------
-- Procedure structure for getListBorrowOfUser
-- ----------------------------
DROP PROCEDURE IF EXISTS `getListBorrowOfUser`;
delimiter ;;
CREATE PROCEDURE `getListBorrowOfUser`(IN `ID` VARCHAR(255))
BEGIN
    -- Declare variables to hold user details
    DECLARE user_last_name VARCHAR(255);
    DECLARE user_first_name VARCHAR(255);

    -- Fetch user details
    SELECT last_name, first_name INTO user_last_name, user_first_name
    FROM detail_account
    WHERE id_account = ID;

    -- Select the list of borrowed assets for the user
    SELECT d.id_account, user_last_name, user_first_name, t.`value` AS asset_type, a.asset_name, SUM(l.quantity) AS total_borrowed
    FROM detail_account d
    INNER JOIN list_borrow l ON d.id_account = l.id_account
    INNER JOIN asset a ON l.id_asset = a.id_asset
    INNER JOIN type t ON a.id_type = t.id_type
    WHERE l.return_date IS NULL AND d.id_account = ID
    GROUP BY d.id_account, l.id_asset;

END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
