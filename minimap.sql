/*
Navicat MySQL Data Transfer

Source Server         : localMysql
Source Server Version : 50726
Source Host           : localhost:3306
Source Database       : minimap

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2021-05-18 13:54:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for loc_collection
-- ----------------------------
DROP TABLE IF EXISTS `loc_collection`;
CREATE TABLE `loc_collection` (
  `locid` int(4) NOT NULL AUTO_INCREMENT,
  `name` varchar(80) DEFAULT NULL,
  `userid` varchar(64) NOT NULL,
  PRIMARY KEY (`locid`),
  KEY `userid` (`userid`),
  CONSTRAINT `userid` FOREIGN KEY (`userid`) REFERENCES `t_users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of loc_collection
-- ----------------------------
INSERT INTO `loc_collection` VALUES ('3', '远大花园', '21032675MNPA3ZMW');
INSERT INTO `loc_collection` VALUES ('4', '万象首府', '21032675MNPA3ZMW');
INSERT INTO `loc_collection` VALUES ('10', '沈阳昊诚电气有限公司', '21032675MNPA3ZMW');
INSERT INTO `loc_collection` VALUES ('11', '华创风能公司', '21032675MNPA3ZMW');
INSERT INTO `loc_collection` VALUES ('12', '丹宇建材商店', '21032675MNPA3ZMW');
INSERT INTO `loc_collection` VALUES ('13', '中国·机床城', '21032675MNPA3ZMW');
INSERT INTO `loc_collection` VALUES ('14', '和谐广场', '21032675MNPA3ZMW');
INSERT INTO `loc_collection` VALUES ('15', '特变之家', '21032675MNPA3ZMW');
INSERT INTO `loc_collection` VALUES ('16', '中央大街(地铁站)', '21032675MNPA3ZMW');

-- ----------------------------
-- Table structure for t_users
-- ----------------------------
DROP TABLE IF EXISTS `t_users`;
CREATE TABLE `t_users` (
  `id` varchar(64) NOT NULL COMMENT 'ID',
  `username` varchar(20) NOT NULL COMMENT '用户名',
  `password` varchar(64) NOT NULL COMMENT '密码，已加密',
  `face_image` varchar(255) NOT NULL COMMENT '用户头像',
  `face_image_big` varchar(255) NOT NULL COMMENT '用户大头像',
  `nickname` varchar(20) DEFAULT NULL COMMENT '用户昵称',
  `qrcode` varchar(255) DEFAULT NULL COMMENT '用户二维码',
  `cid` varchar(64) DEFAULT NULL COMMENT '用户客户端id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_users
-- ----------------------------
INSERT INTO `t_users` VALUES ('21032675MNPA3ZMW', '王子荣', '4QrcOUm6Wau+VuBX8g+IPg==', '', '', '永不言弃', '', null);
INSERT INTO `t_users` VALUES ('210326A8ZTZ11C4H', '一乐', '1B2M2Y8AsgTpgAmY7PhCfg==', '', '', '为是主义', '', 'da28a3d1671673984f268c8f639529a2');
INSERT INTO `t_users` VALUES ('210329A194AN8280', '王文', '4QrcOUm6Wau+VuBX8g+IPg==', '', '', '王文', '', 'da28a3d1671673984f268c8f639529a2');
INSERT INTO `t_users` VALUES ('im01', '王一凡', '123456', 'images/xxx.jpg', 'images/xxxzd.jpg', 'fanfan', 'images/qr.jpg', 'uuid001');
