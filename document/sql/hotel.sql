/*
Navicat MySQL Data Transfer

Source Server         : 酒店管理系统
Source Server Version : 50719
Source Host           : localhost:3306
Source Database       : hotel

Target Server Type    : MYSQL
Target Server Version : 50719
File Encoding         : 65001

Date: 2020-10-28 01:21:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cms_check
-- ----------------------------
DROP TABLE IF EXISTS `cms_check`;
CREATE TABLE `cms_check` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) DEFAULT NULL COMMENT '订单id',
  `name` varchar(20) DEFAULT NULL COMMENT '姓名',
  `card_id` varchar(255) DEFAULT '' COMMENT '身份证号',
  `check_in_time` datetime DEFAULT NULL COMMENT '入住登记时间',
  `check_out_time` datetime DEFAULT NULL COMMENT '退房时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='入住记录';

-- ----------------------------
-- Records of cms_check
-- ----------------------------
INSERT INTO `cms_check` VALUES ('1', '32', '杨锴', '500381199805040856', '2020-10-27 15:47:09', '2020-10-29 15:47:13');
INSERT INTO `cms_check` VALUES ('2', '32', '杨强', '123456', '2020-10-27 15:47:32', '2020-10-29 15:47:34');
INSERT INTO `cms_check` VALUES ('3', '32', '222', '222', '2020-10-27 16:31:29', null);
INSERT INTO `cms_check` VALUES ('4', '32', '111', '2222', '2020-10-27 16:37:58', null);
INSERT INTO `cms_check` VALUES ('5', '31', '111', '2222', '2020-10-27 16:44:44', null);
INSERT INTO `cms_check` VALUES ('6', '31', '222', '2222', '2020-10-27 16:45:04', null);
INSERT INTO `cms_check` VALUES ('7', '31', '222', '3333', '2020-10-27 16:46:07', null);
INSERT INTO `cms_check` VALUES ('8', '31', '222', '3333', '2020-10-27 16:46:40', null);
INSERT INTO `cms_check` VALUES ('9', '31', '222', '333', '2020-10-27 16:46:53', null);
INSERT INTO `cms_check` VALUES ('10', '31', '222', '333', '2020-10-27 16:51:38', null);

-- ----------------------------
-- Table structure for oms_order
-- ----------------------------
DROP TABLE IF EXISTS `oms_order`;
CREATE TABLE `oms_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `order_sn` varchar(64) DEFAULT NULL COMMENT '订单编号',
  `username` varchar(255) DEFAULT NULL COMMENT '用户账号',
  `pay_amount` decimal(10,2) DEFAULT NULL COMMENT '应付金额（实际支付金额）',
  `pay_type` int(1) DEFAULT NULL COMMENT '支付方式：0->未支付；1->支付宝；2->微信，3->现金',
  `source_type` int(1) DEFAULT NULL COMMENT '订单来源：0->PC订单；1->app订单，2->线下订单',
  `status` int(1) DEFAULT '0' COMMENT '订单状态：0->未付款；1->已付款；，2->已取消',
  `order_type` int(1) DEFAULT '0' COMMENT '订单类型：0->正常订单；1->秒杀订单',
  `note` varchar(500) DEFAULT NULL COMMENT '订单备注',
  `payment_time` datetime DEFAULT NULL COMMENT '支付时间',
  `comment_time` datetime DEFAULT NULL COMMENT '评价时间',
  `create_time` datetime DEFAULT NULL COMMENT '提交时间',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  `room_name` varchar(255) DEFAULT NULL COMMENT '房间名',
  `floor` int(10) DEFAULT NULL COMMENT '楼层号',
  `serial` varchar(255) DEFAULT NULL COMMENT '房间号',
  `pic` varchar(255) DEFAULT NULL COMMENT '房间图片',
  `room_id` bigint(20) DEFAULT NULL COMMENT '房间id',
  `card_id` varchar(255) NOT NULL COMMENT '身份证号',
  `register_status` int(1) NOT NULL DEFAULT '0' COMMENT '登记入住状态：0->未登记入住；1->已登记入住；2->已退房',
  `check_in_time` datetime DEFAULT NULL,
  `check_out_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 COMMENT='订单表';

-- ----------------------------
-- Records of oms_order
-- ----------------------------
INSERT INTO `oms_order` VALUES ('31', 'adminTue Oct 20 23:45:04 CST 2020', 'admin', '100.12', '1', '0', '1', '0', 'demoData', '2020-10-22 21:39:35', '2020-10-22 21:39:44', '2020-10-20 15:45:04', '2020-10-22 21:39:50', '111', '1', '0212', 'http://127.0.0.1:9000/hotel/20201018/header.jpg,http://127.0.0.1:9000/hotel/20201018/41412-106.jpg', '1', '500381198805040856', '1', '2020-10-27 16:31:29', null);
INSERT INTO `oms_order` VALUES ('32', 'admin1603208813955', 'admin', '200.00', '1', '1', '1', '0', 'demoData', '2020-10-22 21:39:37', '2020-10-22 21:39:46', '2020-10-20 15:46:54', '2020-10-22 21:39:52', '222', '2', '0213', 'http://127.0.0.1:9000/hotel/20201018/header.jpg,http://127.0.0.1:9000/hotel/20201018/41412-106.jpg', '2', '500381198805040857', '2', null, '2020-10-27 17:00:11');
INSERT INTO `oms_order` VALUES ('33', '1319229828368568320', 'admin', '455.67', '2', '0', '1', '1', 'demoData', '2020-10-22 21:39:39', '2020-10-22 21:39:48', '2020-10-22 10:51:15', '2020-10-22 21:39:54', '333', '1', '0123', 'http://127.0.0.1:9000/hotel/20201018/header.jpg,http://127.0.0.1:9000/hotel/20201018/41412-106.jpg', '3', '500381198805040858', '1', null, null);

-- ----------------------------
-- Table structure for rms_room
-- ----------------------------
DROP TABLE IF EXISTS `rms_room`;
CREATE TABLE `rms_room` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '房间id',
  `name` varchar(255) NOT NULL COMMENT '房间名',
  `status` int(1) DEFAULT '1' COMMENT '房间状态(0-不可用/1-空闲)',
  `clean` int(1) DEFAULT '2' COMMENT '清洁状态(0-未清洁，1-清洁中，2-已清洁)',
  `maintenance` int(1) DEFAULT '2' COMMENT '维修状态(0-未维修，1-维修中，2-维修完成)',
  `description` varchar(255) DEFAULT NULL COMMENT '房间描述',
  `floor` int(10) DEFAULT NULL COMMENT '楼层数',
  `serial` varchar(255) DEFAULT NULL COMMENT '房间序号',
  `sort` bigint(20) DEFAULT '0' COMMENT '排序号',
  `price` decimal(10,2) NOT NULL COMMENT '房间价格',
  `pic` varchar(255) DEFAULT NULL COMMENT '房间图片地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of rms_room
-- ----------------------------
INSERT INTO `rms_room` VALUES ('1', '双人房22', '1', '2', '2', '双人床，有窗，单独', '1', '000101', '0', '10000.00', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ab46a3cN616bdc41.jpg,http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/5ac1bf5fN2522b9dc.jpg');
INSERT INTO `rms_room` VALUES ('2', '3', '1', '0', '0', null, '2', '2', '0', '0.00', 'http://127.0.0.1:9000/hotel/20201018/header.jpg,http://127.0.0.1:9000/hotel/20201018/header.jpg');
INSERT INTO `rms_room` VALUES ('3', '111', '0', '1', '1', '1', '1', '1', '1', '1.00', 'http://127.0.0.1:9000/hotel/20201018/header.jpg,http://127.0.0.1:9000/hotel/20201018/41412-106.jpg');
INSERT INTO `rms_room` VALUES ('4', '222', '1', '2', '2', null, '2', '3', '0', '222.00', 'http://127.0.0.1:9000/hotel/20201028/1.jpg');
INSERT INTO `rms_room` VALUES ('5', '333', '1', '2', '2', null, '3', 'A12', '0', '500.22', 'http://127.0.0.1:9000/hotel/20201028/48637-106.jpg');
INSERT INTO `rms_room` VALUES ('6', 'test', '0', '2', '2', '3333', '222', '3333', null, '5.00', 'http://127.0.0.1:9000/hotel/20201028/header.jpg');
INSERT INTO `rms_room` VALUES ('7', '2', '1', '2', '2', null, '22', '222', '22', '222.00', 'http://127.0.0.1:9000/hotel/20201028/header.jpg');

-- ----------------------------
-- Table structure for ums_admin
-- ----------------------------
DROP TABLE IF EXISTS `ums_admin`;
CREATE TABLE `ums_admin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `icon` varchar(500) DEFAULT NULL COMMENT '头像',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `nick_name` varchar(200) DEFAULT NULL COMMENT '昵称',
  `note` varchar(500) DEFAULT NULL COMMENT '备注信息',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `status` int(1) DEFAULT '1' COMMENT '帐号启用状态：0->禁用；1->启用',
  `phone` varchar(255) DEFAULT NULL COMMENT '手机号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ums_admin_phone` (`phone`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='后台用户表';

-- ----------------------------
-- Records of ums_admin
-- ----------------------------
INSERT INTO `ums_admin` VALUES ('1', 'user', '$2a$10$l6FVQDmrBkfLhnY50r2PeeUaCiAVGRra0oyZ1j3J7SNsw9plkRaBe', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/timg.jpg', 'test@qq.com', '普通用户', null, '2018-09-29 13:55:30', '2018-09-29 13:55:39', '1', null);
INSERT INTO `ums_admin` VALUES ('3', 'admin', '$2a$10$16/4qSKwTraDSz6HrfiiyecbTiYfptOsteidQXZSeiOFS9lZlARXm', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/timg.jpg', 'guitai@163.com', '超级管理员', '超级管理员', '2018-10-08 13:32:47', '2019-04-20 12:45:16', '1', null);
INSERT INTO `ums_admin` VALUES ('10', 'guitai', '$2a$10$QbREB0hHTNaAXL/pVmre2OQOhVu8jPu4yi.aIrK/MK7GQR3tOxFmy', null, '1462541756@qq.com', '柜台服务员', null, '2020-10-19 17:08:51', null, '1', null);

-- ----------------------------
-- Table structure for ums_admin_login_log
-- ----------------------------
DROP TABLE IF EXISTS `ums_admin_login_log`;
CREATE TABLE `ums_admin_login_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin_id` bigint(20) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `ip` varchar(64) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `user_agent` varchar(100) DEFAULT NULL COMMENT '浏览器登录类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=259 DEFAULT CHARSET=utf8 COMMENT='后台用户登录日志表';

-- ----------------------------
-- Records of ums_admin_login_log
-- ----------------------------
INSERT INTO `ums_admin_login_log` VALUES ('1', '3', '2018-12-23 14:27:00', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('2', '3', '2019-04-07 16:04:39', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('3', '3', '2019-04-08 21:47:52', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('4', '3', '2019-04-08 21:48:18', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('5', '3', '2019-04-18 22:18:40', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('6', '3', '2019-04-20 12:45:16', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('7', '3', '2019-05-19 14:52:12', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('8', '3', '2019-05-25 15:00:17', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('9', '3', '2019-06-19 20:11:42', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('10', '3', '2019-06-30 10:33:48', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('11', '3', '2019-06-30 10:34:31', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('12', '3', '2019-06-30 10:35:34', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('13', '3', '2019-07-27 17:11:01', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('14', '3', '2019-07-27 17:13:18', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('15', '3', '2019-07-27 17:15:35', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('16', '3', '2019-07-27 17:17:11', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('17', '3', '2019-07-27 17:18:34', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('18', '3', '2019-07-27 21:21:52', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('19', '3', '2019-07-27 21:34:29', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('20', '3', '2019-07-27 21:35:17', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('21', '3', '2019-07-27 21:35:48', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('22', '3', '2019-07-27 21:40:33', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('23', '3', '2019-08-18 16:00:38', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('24', '3', '2019-08-18 16:01:06', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('25', '3', '2019-08-18 16:47:01', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('26', '3', '2019-10-06 15:54:23', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('27', '3', '2019-10-06 16:03:28', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('28', '3', '2019-10-06 16:04:51', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('29', '3', '2019-10-06 16:06:44', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('30', '3', '2019-10-06 16:14:51', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('31', '1', '2019-10-06 16:15:09', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('32', '1', '2019-10-06 16:16:14', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('33', '3', '2019-10-06 16:16:35', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('34', '3', '2019-10-06 16:16:42', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('35', '3', '2019-10-07 15:20:48', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('36', '3', '2019-10-07 15:40:07', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('37', '3', '2019-10-07 16:34:15', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('38', '3', '2019-10-09 21:19:08', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('39', '4', '2019-10-09 21:30:35', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('40', '4', '2019-10-09 21:31:30', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('41', '4', '2019-10-09 21:32:39', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('42', '4', '2019-10-09 21:33:27', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('43', '4', '2019-10-09 21:33:50', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('44', '3', '2019-10-20 16:02:53', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('45', '3', '2019-10-23 21:20:55', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('46', '3', '2019-10-27 21:41:45', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('47', '3', '2019-11-09 16:44:57', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('48', '3', '2019-11-09 16:46:56', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('49', '3', '2019-11-09 16:49:55', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('50', '3', '2019-11-23 14:17:16', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('51', '6', '2019-11-23 14:52:30', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('52', '3', '2019-11-23 15:07:24', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('53', '3', '2019-11-30 21:25:30', '192.168.3.185', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('54', '3', '2019-11-30 21:27:54', '192.168.3.185', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('55', '3', '2019-12-28 15:23:01', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('56', '3', '2020-01-01 15:21:46', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('57', '3', '2020-01-04 16:00:54', '192.168.3.185', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('58', '3', '2020-02-01 15:05:19', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('59', '3', '2020-02-01 15:36:05', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('60', '3', '2020-02-01 15:36:36', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('61', '3', '2020-02-01 15:37:30', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('62', '3', '2020-02-01 15:37:46', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('63', '3', '2020-02-01 15:38:20', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('64', '3', '2020-02-01 15:38:33', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('65', '3', '2020-02-01 15:39:06', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('66', '3', '2020-02-01 15:41:31', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('67', '3', '2020-02-01 15:43:17', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('68', '3', '2020-02-01 15:44:34', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('69', '3', '2020-02-01 15:45:10', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('70', '3', '2020-02-01 15:46:04', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('71', '3', '2020-02-01 15:48:33', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('72', '3', '2020-02-01 16:00:07', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('73', '3', '2020-02-01 16:07:25', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('74', '3', '2020-02-01 16:08:22', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('75', '3', '2020-02-02 15:28:13', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('76', '3', '2020-02-02 15:44:37', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('77', '3', '2020-02-02 15:45:25', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('78', '3', '2020-02-02 15:52:32', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('79', '3', '2020-02-02 15:53:44', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('80', '3', '2020-02-02 15:54:36', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('81', '3', '2020-02-02 16:01:00', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('82', '3', '2020-02-02 16:05:19', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('83', '3', '2020-02-02 16:06:31', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('84', '3', '2020-02-02 16:17:26', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('85', '3', '2020-02-02 16:18:45', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('86', '3', '2020-02-02 16:19:05', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('87', '3', '2020-02-02 16:19:23', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('88', '3', '2020-02-02 16:22:27', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('89', '3', '2020-02-02 16:23:30', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('90', '3', '2020-02-02 16:23:48', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('91', '3', '2020-02-02 16:24:38', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('92', '3', '2020-02-02 16:25:22', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('93', '3', '2020-02-02 16:26:19', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('94', '3', '2020-02-02 16:26:31', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('95', '3', '2020-02-02 16:27:08', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('96', '3', '2020-02-02 16:31:02', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('97', '3', '2020-02-02 16:31:08', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('98', '3', '2020-02-02 16:31:25', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('99', '3', '2020-02-02 16:31:50', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('100', '3', '2020-02-02 16:33:22', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('101', '3', '2020-02-02 16:33:41', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('102', '3', '2020-02-02 16:34:58', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('103', '3', '2020-02-02 16:38:42', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('104', '3', '2020-02-02 16:39:41', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('105', '3', '2020-02-02 16:42:22', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('106', '3', '2020-02-02 16:46:21', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('107', '3', '2020-02-02 16:50:23', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('108', '3', '2020-02-02 16:51:11', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('109', '3', '2020-02-02 16:51:22', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('110', '3', '2020-02-02 16:52:00', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('111', '3', '2020-02-02 17:01:05', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('112', '3', '2020-02-03 10:43:22', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('113', '3', '2020-02-03 10:45:29', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('114', '3', '2020-02-03 10:46:33', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('115', '3', '2020-02-03 10:54:33', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('116', '3', '2020-02-03 14:24:47', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('117', '3', '2020-02-03 14:25:38', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('118', '5', '2020-02-03 15:22:28', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('119', '5', '2020-02-03 15:23:00', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('120', '5', '2020-02-03 15:24:29', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('121', '3', '2020-02-03 15:24:50', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('122', '5', '2020-02-03 15:27:18', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('123', '3', '2020-02-03 15:27:33', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('124', '3', '2020-02-03 15:29:06', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('125', '5', '2020-02-03 15:33:25', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('126', '3', '2020-02-03 15:33:51', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('127', '1', '2020-02-03 15:34:35', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('128', '3', '2020-02-03 15:34:47', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('129', '3', '2020-02-04 14:14:46', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('130', '3', '2020-02-05 10:33:35', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('131', '3', '2020-02-05 10:36:21', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('132', '3', '2020-02-05 16:34:37', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('133', '4', '2020-02-05 16:58:37', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('134', '3', '2020-02-05 16:59:03', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('135', '3', '2020-02-06 10:25:02', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('136', '3', '2020-02-07 14:34:34', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('137', '3', '2020-02-07 14:36:20', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('138', '1', '2020-02-07 14:43:34', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('139', '3', '2020-02-07 15:18:06', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('140', '3', '2020-02-07 15:20:07', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('141', '3', '2020-02-07 15:22:20', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('142', '3', '2020-02-07 15:22:28', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('143', '3', '2020-02-07 15:55:11', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('144', '3', '2020-02-07 15:56:04', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('145', '3', '2020-02-07 15:58:49', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('146', '6', '2020-02-07 16:16:21', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('147', '7', '2020-02-07 16:16:37', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('148', '3', '2020-02-07 16:18:39', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('149', '7', '2020-02-07 16:20:06', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('150', '3', '2020-02-07 16:20:44', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('151', '3', '2020-02-07 16:32:31', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('152', '3', '2020-02-07 19:32:34', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('153', '3', '2020-02-07 19:32:48', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('154', '3', '2020-02-07 19:33:01', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('155', '3', '2020-02-07 19:33:06', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('156', '3', '2020-02-07 19:33:21', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('157', '3', '2020-02-07 19:35:33', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('158', '3', '2020-02-07 19:37:10', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('159', '3', '2020-02-07 19:37:14', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('160', '3', '2020-02-07 19:37:25', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('161', '3', '2020-02-07 19:45:41', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('162', '3', '2020-02-07 19:47:45', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('163', '3', '2020-02-07 20:02:25', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('164', '6', '2020-02-07 20:10:55', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('165', '6', '2020-02-07 20:11:02', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('166', '6', '2020-02-07 20:13:44', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('167', '6', '2020-02-07 20:17:14', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('168', '3', '2020-02-07 20:17:44', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('169', '6', '2020-02-07 20:18:13', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('170', '3', '2020-02-10 10:28:14', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('171', '3', '2020-02-10 10:45:15', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('172', '3', '2020-02-10 10:57:46', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('173', '3', '2020-02-10 10:59:06', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('174', '3', '2020-02-10 11:04:19', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('175', '3', '2020-02-10 11:05:55', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('176', '3', '2020-02-10 11:06:45', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('177', '3', '2020-02-10 11:07:41', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('178', '3', '2020-02-10 11:08:13', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('179', '3', '2020-02-10 11:10:02', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('180', '6', '2020-02-10 14:25:17', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('181', '6', '2020-02-10 14:29:14', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('182', '3', '2020-02-10 16:09:16', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('183', '3', '2020-02-20 14:39:19', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('184', '8', '2020-02-20 17:14:58', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('185', '8', '2020-02-20 17:17:04', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('186', '8', '2020-02-20 17:17:42', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('187', '8', '2020-02-21 10:26:56', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('188', '8', '2020-02-21 10:28:54', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('189', '8', '2020-02-21 10:32:25', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('190', '8', '2020-02-21 10:33:41', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('191', '8', '2020-02-21 10:35:58', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('192', '8', '2020-02-21 10:36:49', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('193', '3', '2020-02-21 11:10:11', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('194', '3', '2020-02-25 16:11:13', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('195', '3', '2020-02-25 16:46:29', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('196', '3', '2020-10-09 18:06:56', '127.0.0.1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('197', '3', '2020-10-09 18:07:40', '127.0.0.1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('198', '3', '2020-10-11 17:26:42', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('199', '3', '2020-10-11 17:44:10', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('200', '3', '2020-10-11 17:45:04', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('201', '7', '2020-10-11 17:46:46', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('202', '3', '2020-10-11 17:46:56', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('203', '3', '2020-10-11 17:47:11', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('204', '3', '2020-10-12 01:02:24', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('205', '3', '2020-10-14 06:57:00', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('206', '3', '2020-10-17 08:22:52', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('207', '3', '2020-10-17 13:40:38', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('208', '3', '2020-10-17 18:45:35', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('209', '3', '2020-10-18 06:19:47', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('210', '3', '2020-10-18 10:24:10', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('211', '1', '2020-10-18 10:24:39', '0:0:0:0:0:0:0:1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('212', '3', '2020-10-19 06:53:08', '127.0.0.1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('213', '3', '2020-10-19 07:01:27', '127.0.0.1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('214', '3', '2020-10-19 07:01:42', '127.0.0.1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('215', '3', '2020-10-19 07:01:55', '127.0.0.1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('216', '1', '2020-10-19 09:20:53', '127.0.0.1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('217', '3', '2020-10-19 15:01:26', '127.0.0.1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('218', '3', '2020-10-19 16:40:15', '127.0.0.1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('219', '10', '2020-10-19 17:08:58', '127.0.0.1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('220', '1', '2020-10-20 07:08:12', '127.0.0.1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('221', '10', '2020-10-20 07:47:20', '127.0.0.1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('222', '3', '2020-10-20 07:48:09', '127.0.0.1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('223', '3', '2020-10-20 07:53:46', '127.0.0.1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('224', '3', '2020-10-20 07:54:46', '127.0.0.1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('225', '3', '2020-10-20 08:12:27', '127.0.0.1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('226', '3', '2020-10-20 13:18:23', '127.0.0.1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('227', '3', '2020-10-20 17:25:29', '127.0.0.1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('228', '1', '2020-10-20 17:44:41', '127.0.0.1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('229', '3', '2020-10-20 17:45:06', '127.0.0.1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('230', '3', '2020-10-22 16:48:50', '127.0.0.1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('231', '3', '2020-10-22 16:50:46', '127.0.0.1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('232', '3', '2020-10-22 16:57:07', '127.0.0.1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('233', '3', '2020-10-22 16:58:32', '127.0.0.1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('234', '3', '2020-10-23 07:12:00', '127.0.0.1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('235', '3', '2020-10-23 07:54:46', '127.0.0.1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('236', '3', '2020-10-23 07:56:51', '127.0.0.1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('237', '3', '2020-10-23 10:28:40', '127.0.0.1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('238', '3', '2020-10-27 03:28:32', '127.0.0.1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('239', '3', '2020-10-27 13:16:46', '127.0.0.1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('240', '3', '2020-10-27 14:13:00', '127.0.0.1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('241', '3', '2020-10-27 14:33:49', '127.0.0.1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('242', '3', '2020-10-27 14:48:46', '127.0.0.1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('243', '3', '2020-10-27 14:48:55', '127.0.0.1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('244', '3', '2020-10-27 15:09:48', '127.0.0.1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('245', '3', '2020-10-27 15:09:50', '127.0.0.1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('246', '3', '2020-10-27 15:09:51', '127.0.0.1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('247', '3', '2020-10-27 15:09:52', '127.0.0.1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('248', '3', '2020-10-27 15:09:55', '127.0.0.1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('249', '3', '2020-10-27 15:09:57', '127.0.0.1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('250', '3', '2020-10-27 15:10:52', '127.0.0.1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('251', '3', '2020-10-27 15:12:00', '127.0.0.1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('252', '3', '2020-10-27 15:12:46', '127.0.0.1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('253', '1', '2020-10-27 15:18:26', '127.0.0.1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('254', '1', '2020-10-27 15:30:20', '127.0.0.1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('255', '1', '2020-10-27 15:32:25', '127.0.0.1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('256', '1', '2020-10-27 15:32:59', '127.0.0.1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('257', '3', '2020-10-27 15:38:21', '127.0.0.1', null, null);
INSERT INTO `ums_admin_login_log` VALUES ('258', '3', '2020-10-27 15:48:23', '127.0.0.1', null, null);

-- ----------------------------
-- Table structure for ums_admin_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `ums_admin_role_relation`;
CREATE TABLE `ums_admin_role_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `admin_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8 COMMENT='后台用户和角色关系表';

-- ----------------------------
-- Records of ums_admin_role_relation
-- ----------------------------
INSERT INTO `ums_admin_role_relation` VALUES ('26', '3', '5');
INSERT INTO `ums_admin_role_relation` VALUES ('27', '6', '1');
INSERT INTO `ums_admin_role_relation` VALUES ('30', '4', '5');
INSERT INTO `ums_admin_role_relation` VALUES ('31', '1', '9');
INSERT INTO `ums_admin_role_relation` VALUES ('32', '7', '2');
INSERT INTO `ums_admin_role_relation` VALUES ('33', '10', '2');

-- ----------------------------
-- Table structure for ums_menu
-- ----------------------------
DROP TABLE IF EXISTS `ums_menu`;
CREATE TABLE `ums_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父级ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `title` varchar(100) DEFAULT NULL COMMENT '菜单名称',
  `level` int(4) DEFAULT NULL COMMENT '菜单级数',
  `sort` int(4) DEFAULT NULL COMMENT '菜单排序',
  `name` varchar(100) DEFAULT NULL COMMENT '前端名称',
  `icon` varchar(200) DEFAULT NULL COMMENT '前端图标',
  `hidden` int(1) DEFAULT NULL COMMENT '前端隐藏',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8 COMMENT='后台菜单表';

-- ----------------------------
-- Records of ums_menu
-- ----------------------------
INSERT INTO `ums_menu` VALUES ('7', '0', '2020-02-02 16:54:07', '订单管理', '0', '0', 'oms', 'order', '0');
INSERT INTO `ums_menu` VALUES ('8', '7', '2020-02-02 16:55:18', '订单列表', '1', '0', 'order', 'order-list', '0');
INSERT INTO `ums_menu` VALUES ('21', '0', '2020-02-07 16:29:13', '权限管理', '0', '-1', 'ums', 'ums', '0');
INSERT INTO `ums_menu` VALUES ('22', '21', '2020-02-07 16:29:51', '用户列表', '1', '0', 'admin', 'ums-admin', '0');
INSERT INTO `ums_menu` VALUES ('23', '21', '2020-02-07 16:30:13', '角色列表', '1', '0', 'role', 'ums-role', '0');
INSERT INTO `ums_menu` VALUES ('24', '21', '2020-02-07 16:30:53', '菜单列表', '1', '0', 'menu', 'ums-menu', '0');
INSERT INTO `ums_menu` VALUES ('25', '21', '2020-02-07 16:31:13', '资源列表', '1', '0', 'resource', 'ums-resource', '0');
INSERT INTO `ums_menu` VALUES ('26', '26', '2020-10-17 08:30:25', '房间', '1', '0', 'room', 'room', '0');
INSERT INTO `ums_menu` VALUES ('27', '26', '2020-10-17 08:32:21', '房间列表', '1', '0', 'room-list', 'room', '0');
INSERT INTO `ums_menu` VALUES ('28', '0', '2020-10-17 08:37:39', '房间管理', '0', '0', 'rms', 'room', '0');
INSERT INTO `ums_menu` VALUES ('29', '28', '2020-10-17 08:45:47', '房间列表', '1', '0', 'room', 'room', '0');
INSERT INTO `ums_menu` VALUES ('30', '28', '2020-10-17 10:36:22', '创建房间', '1', '0', 'addRoom', 'room', '0');
INSERT INTO `ums_menu` VALUES ('35', '21', '2020-10-20 10:49:52', 'test', '1', '0', 'test', 'room', '0');
INSERT INTO `ums_menu` VALUES ('40', '0', '2020-10-23 12:50:46', '入住管理', '0', '0', 'cms', 'room', '0');
INSERT INTO `ums_menu` VALUES ('41', '40', '2020-10-23 12:52:43', '入住与退房', '1', '0', 'check', 'room', '0');
INSERT INTO `ums_menu` VALUES ('42', '40', '2020-10-27 13:11:32', '登记列表', '1', '1', 'checkList', 'room', '0');

-- ----------------------------
-- Table structure for ums_resource
-- ----------------------------
DROP TABLE IF EXISTS `ums_resource`;
CREATE TABLE `ums_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `name` varchar(200) DEFAULT NULL COMMENT '资源名称',
  `url` varchar(200) DEFAULT NULL COMMENT '资源URL',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `category_id` bigint(20) DEFAULT NULL COMMENT '资源分类ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8 COMMENT='后台资源表';

-- ----------------------------
-- Records of ums_resource
-- ----------------------------
INSERT INTO `ums_resource` VALUES ('25', '2020-02-07 16:47:34', '后台用户管理', '/admin/**', '', '4');
INSERT INTO `ums_resource` VALUES ('26', '2020-02-07 16:48:24', '后台用户角色管理', '/role/**', '', '4');
INSERT INTO `ums_resource` VALUES ('27', '2020-02-07 16:48:48', '后台菜单管理', '/menu/**', '', '4');
INSERT INTO `ums_resource` VALUES ('28', '2020-02-07 16:49:18', '后台资源分类管理', '/resourceCategory/**', '', '4');
INSERT INTO `ums_resource` VALUES ('29', '2020-02-07 16:49:45', '后台资源管理', '/resource/**', '', '4');
INSERT INTO `ums_resource` VALUES ('31', '2020-10-17 08:35:02', '房间管理', '/room/**', '房间管理', '8');
INSERT INTO `ums_resource` VALUES ('34', '2020-10-22 16:41:31', '订单管理', '/order/**', '', '2');
INSERT INTO `ums_resource` VALUES ('36', '2020-10-27 14:40:33', '前台资源', '/front/**', '前台资源', '6');

-- ----------------------------
-- Table structure for ums_resource_category
-- ----------------------------
DROP TABLE IF EXISTS `ums_resource_category`;
CREATE TABLE `ums_resource_category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `name` varchar(200) DEFAULT NULL COMMENT '分类名称',
  `sort` int(4) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='资源分类表';

-- ----------------------------
-- Records of ums_resource_category
-- ----------------------------
INSERT INTO `ums_resource_category` VALUES ('2', '2020-02-05 10:22:34', '订单模块', '0');
INSERT INTO `ums_resource_category` VALUES ('4', '2020-02-05 10:23:04', '权限模块', '1');
INSERT INTO `ums_resource_category` VALUES ('5', '2020-02-07 16:34:27', '内容模块', '0');
INSERT INTO `ums_resource_category` VALUES ('6', '2020-02-07 16:35:49', '其他模块', '0');
INSERT INTO `ums_resource_category` VALUES ('8', '2020-10-17 08:34:30', '房间模块', '1');

-- ----------------------------
-- Table structure for ums_role
-- ----------------------------
DROP TABLE IF EXISTS `ums_role`;
CREATE TABLE `ums_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `admin_count` int(11) DEFAULT NULL COMMENT '后台用户数量',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `status` int(1) DEFAULT '1' COMMENT '启用状态：0->禁用；1->启用',
  `sort` int(11) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='后台用户角色表';

-- ----------------------------
-- Records of ums_role
-- ----------------------------
INSERT INTO `ums_role` VALUES ('2', '柜台服务员', '柜台服务员', '0', '2018-09-30 15:53:45', '1', '0');
INSERT INTO `ums_role` VALUES ('5', '超级管理员', '拥有所有查看和操作功能', '0', '2020-02-02 15:11:05', '1', '0');
INSERT INTO `ums_role` VALUES ('9', '普通用户', '普通用户', '0', '2020-10-18 10:22:51', '1', '0');

-- ----------------------------
-- Table structure for ums_role_menu_relation
-- ----------------------------
DROP TABLE IF EXISTS `ums_role_menu_relation`;
CREATE TABLE `ums_role_menu_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=683 DEFAULT CHARSET=utf8 COMMENT='后台角色菜单关系表';

-- ----------------------------
-- Records of ums_role_menu_relation
-- ----------------------------
INSERT INTO `ums_role_menu_relation` VALUES ('661', '5', '7');
INSERT INTO `ums_role_menu_relation` VALUES ('662', '5', '8');
INSERT INTO `ums_role_menu_relation` VALUES ('663', '5', '21');
INSERT INTO `ums_role_menu_relation` VALUES ('664', '5', '22');
INSERT INTO `ums_role_menu_relation` VALUES ('665', '5', '23');
INSERT INTO `ums_role_menu_relation` VALUES ('666', '5', '24');
INSERT INTO `ums_role_menu_relation` VALUES ('667', '5', '25');
INSERT INTO `ums_role_menu_relation` VALUES ('668', '5', '35');
INSERT INTO `ums_role_menu_relation` VALUES ('669', '5', '28');
INSERT INTO `ums_role_menu_relation` VALUES ('670', '5', '29');
INSERT INTO `ums_role_menu_relation` VALUES ('671', '5', '30');
INSERT INTO `ums_role_menu_relation` VALUES ('672', '5', '40');
INSERT INTO `ums_role_menu_relation` VALUES ('673', '5', '41');
INSERT INTO `ums_role_menu_relation` VALUES ('674', '5', '42');
INSERT INTO `ums_role_menu_relation` VALUES ('675', '2', '7');
INSERT INTO `ums_role_menu_relation` VALUES ('676', '2', '8');
INSERT INTO `ums_role_menu_relation` VALUES ('677', '2', '28');
INSERT INTO `ums_role_menu_relation` VALUES ('678', '2', '29');
INSERT INTO `ums_role_menu_relation` VALUES ('679', '2', '30');
INSERT INTO `ums_role_menu_relation` VALUES ('680', '2', '40');
INSERT INTO `ums_role_menu_relation` VALUES ('681', '2', '41');
INSERT INTO `ums_role_menu_relation` VALUES ('682', '2', '42');

-- ----------------------------
-- Table structure for ums_role_resource_relation
-- ----------------------------
DROP TABLE IF EXISTS `ums_role_resource_relation`;
CREATE TABLE `ums_role_resource_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `resource_id` bigint(20) DEFAULT NULL COMMENT '资源ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=420 DEFAULT CHARSET=utf8 COMMENT='后台角色资源关系表';

-- ----------------------------
-- Records of ums_role_resource_relation
-- ----------------------------
INSERT INTO `ums_role_resource_relation` VALUES ('411', '5', '25');
INSERT INTO `ums_role_resource_relation` VALUES ('412', '5', '26');
INSERT INTO `ums_role_resource_relation` VALUES ('413', '5', '27');
INSERT INTO `ums_role_resource_relation` VALUES ('414', '5', '28');
INSERT INTO `ums_role_resource_relation` VALUES ('415', '5', '29');
INSERT INTO `ums_role_resource_relation` VALUES ('416', '5', '31');
INSERT INTO `ums_role_resource_relation` VALUES ('417', '5', '34');
INSERT INTO `ums_role_resource_relation` VALUES ('418', '5', '36');
INSERT INTO `ums_role_resource_relation` VALUES ('419', '9', '36');
