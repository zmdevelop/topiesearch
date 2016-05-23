/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : dmbase

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2016-02-16 17:48:06
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `cms_template_config`
-- ----------------------------
DROP TABLE IF EXISTS `cms_template_config`;
CREATE TABLE `cms_template_config` (
  `site_id` int(12) DEFAULT NULL,
  `channel_id` int(12) DEFAULT NULL,
  `site_template_id` int(12) DEFAULT NULL,
  `channel_template_id` int(12) DEFAULT NULL,
  `content_template_id` int(12) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cms_template_config
-- ----------------------------
INSERT INTO `cms_template_config` VALUES (null, '49', null, '3', '12');
INSERT INTO `cms_template_config` VALUES (null, '50', null, '3', '21');
INSERT INTO `cms_template_config` VALUES (null, '1', null, '3', '9');
INSERT INTO `cms_template_config` VALUES (null, '24', null, '3', '9');
INSERT INTO `cms_template_config` VALUES ('2', null, null, '3', '9');
