/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 100432 (10.4.32-MariaDB)
 Source Host           : localhost:3306
 Source Schema         : asset_management

 Target Server Type    : MySQL
 Target Server Version : 100432 (10.4.32-MariaDB)
 File Encoding         : 65001

 Date: 07/04/2024 14:10:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for acount
-- ----------------------------
DROP TABLE IF EXISTS `acount`;
CREATE TABLE `acount`  (
  `id_account` int NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `role` int NULL DEFAULT NULL,
  PRIMARY KEY (`id_account`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of acount
-- ----------------------------

-- ----------------------------
-- Table structure for asset
-- ----------------------------
DROP TABLE IF EXISTS `asset`;
CREATE TABLE `asset`  (
  `id_asset` int NOT NULL AUTO_INCREMENT,
  `asset_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `status` int NOT NULL,
  `purchase_price` int NULL DEFAULT NULL,
  `date_purchase` datetime(6) NULL DEFAULT NULL,
  `id_type` int NULL DEFAULT NULL,
  PRIMARY KEY (`id_asset`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of asset
-- ----------------------------
INSERT INTO `asset` VALUES (1, 'ban', 'ban', 0, 10000000, '2024-04-17 00:00:00.000000', 1);

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
  PRIMARY KEY (`id_account`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of detail_account
-- ----------------------------

-- ----------------------------
-- Table structure for list_borrow
-- ----------------------------
DROP TABLE IF EXISTS `list_borrow`;
CREATE TABLE `list_borrow`  (
  `id_account` int NOT NULL,
  `id_asset` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `borrow_date` datetime NOT NULL,
  `return_date` datetime NULL DEFAULT NULL,
  `quantity` int NULL DEFAULT NULL,
  `status` int NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of list_borrow
-- ----------------------------

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

-- ----------------------------
-- Table structure for warehouse
-- ----------------------------
DROP TABLE IF EXISTS `warehouse`;
CREATE TABLE `warehouse`  (
  `id_asset` int NOT NULL AUTO_INCREMENT,
  `stock_quantity` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `unavailable_quantity` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id_asset`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of warehouse
-- ----------------------------

-- ----------------------------
-- Procedure structure for checkLogin
-- ----------------------------
DROP PROCEDURE IF EXISTS `checkLogin`;
delimiter ;;
CREATE PROCEDURE `checkLogin`(IN `ID` INT, IN `Pass` VARCHAR(255))
BEGIN
    DECLARE rowCount INT;
    
    -- Check if ID and Pass parameters are not null
    IF ID IS NULL OR Pass IS NULL THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'ID and Pass parameters cannot be null';
    END IF;

    -- Count the number of rows with matching ID and Pass
    SELECT COUNT(*) INTO rowCount
    FROM account
    WHERE account.id_account = ID AND account.password = Pass;

    -- If no matching record found, return an error
    IF rowCount = 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'Invalid ID or Password';
    END IF;

    -- If a matching record found, return the result
    SELECT * FROM account WHERE account.id_account = ID AND account.password = Pass;

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
