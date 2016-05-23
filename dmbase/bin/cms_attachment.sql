/*
Navicat MySQL Data Transfer

Source Server         : mysqllocalhost
Source Server Version : 50619
Source Host           : 127.0.0.1:3306
Source Database       : dmbase

Target Server Type    : MYSQL
Target Server Version : 50619
File Encoding         : 65001

Date: 2016-01-27 17:11:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for cms_attachment
-- ----------------------------
DROP TABLE IF EXISTS `cms_attachment`;
CREATE TABLE `cms_attachment` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `attachment_type` smallint(6) DEFAULT NULL COMMENT '附件类型[0:图片，1:文本]',
  `attachment_name` varchar(128) COLLATE utf8_bin NOT NULL COMMENT '附件名称',
  `attachment_url` varchar(256) COLLATE utf8_bin NOT NULL COMMENT '附件地址',
  `file_size` bigint(20) NOT NULL COMMENT '附件大小',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` int(11) DEFAULT NULL COMMENT '创建用户',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_active` bit(1) NOT NULL DEFAULT b'1' COMMENT '是否有效',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='内容管理附件表';

-- ----------------------------
-- Records of cms_attachment
-- ----------------------------
INSERT INTO `cms_attachment` VALUES ('64', null, 'etruesso.docx', '/html/resource/1450859015856.docx', '245933', '2015-12-23 16:23:35', null, '2016-01-21 22:22:12', '');
INSERT INTO `cms_attachment` VALUES ('65', null, '开发规范.docx', '/html/resource/1450859020106.docx', '30118', '2015-12-23 16:23:40', null, '2016-01-21 22:22:15', '');
INSERT INTO `cms_attachment` VALUES ('66', null, '新建 Microsoft Word 文档 (2).docx', '/html/resource/1450859029868.docx', '17778', '2015-12-23 16:23:49', null, '2016-01-21 22:22:18', '');
INSERT INTO `cms_attachment` VALUES ('67', null, '需求.txt', '/html/resource/1450859030428.txt', '82', '2015-12-23 16:23:50', null, '2016-01-21 22:22:20', '');
INSERT INTO `cms_attachment` VALUES ('68', null, 'note.txt', '/html/resource/1450861059887.txt', '110745', '2015-12-23 16:57:39', null, '2016-01-21 22:22:24', '');
INSERT INTO `cms_attachment` VALUES ('69', null, '33337.png', 'http://localhost:80/html/resource/1450861103762.png', '235558', '2015-12-23 16:58:23', null, '2015-12-23 16:58:23', '');
INSERT INTO `cms_attachment` VALUES ('70', null, 'BBS系统.docx', 'http://127.0.0.1:80/html/resource/1450849752074.docx', '340349', '2015-12-23 16:23:34', null, '2015-12-24 19:08:38', '');
INSERT INTO `cms_attachment` VALUES ('71', null, '00北京市政务服务中心审批业务平台项目-统一行政审批管理平台需求规格说明书V1.0（20150826）.doc', 'http://localhost:80/html/resource/1451283687907.doc', '45212160', '2015-12-28 14:21:28', null, '2015-12-28 14:21:28', '');
INSERT INTO `cms_attachment` VALUES ('72', null, '20-2.flv', 'http://localhost:80/html/resource/1451556439284.flv', '34320155', '2015-12-31 18:07:19', null, '2015-12-31 18:07:19', '');
INSERT INTO `cms_attachment` VALUES ('73', null, '报销说明及明细SBU-PD-BT5415-201512-周佳良(1).xls', 'http://localhost:8090/html/resource/1451839129908.xls', '110080', '2016-01-04 00:38:49', null, '2016-01-04 00:38:49', '');
INSERT INTO `cms_attachment` VALUES ('74', null, 'Chrysanthemum.jpg', '/html/resource/1452049309879.jpg', '879394', '2016-01-06 11:01:49', null, '2016-01-12 16:51:34', '');
INSERT INTO `cms_attachment` VALUES ('75', null, 'Desert.jpg', '/html/resource/1452059763952.jpg', '845941', '2016-01-06 13:56:03', null, '2016-01-12 16:51:38', '');
INSERT INTO `cms_attachment` VALUES ('76', null, 'Tulips.jpg', '/html/resource/1452059778884.jpg', '620888', '2016-01-06 13:56:18', null, '2016-01-12 16:51:43', '');
INSERT INTO `cms_attachment` VALUES ('77', null, 'Jellyfish.jpg', '/html/resource/1452059789747.jpg', '775702', '2016-01-06 13:56:29', null, '2016-01-12 16:51:46', '');
INSERT INTO `cms_attachment` VALUES ('78', null, 'Chrysanthemum.jpg', '/html/resource/1452060388304.jpg', '879394', '2016-01-06 14:06:28', null, '2016-01-12 16:51:50', '');
INSERT INTO `cms_attachment` VALUES ('79', null, 'Desert.jpg', '/html/resource/1452094138856.jpg', '845941', '2016-01-06 23:28:58', null, '2016-01-12 16:51:53', '');
INSERT INTO `cms_attachment` VALUES ('80', null, '【LOL电影天堂www.loldytt.com】G囧.TC1280清晰国语中字.mp4', '/html/resource/1452131626517.mp4', '2409067987', '2016-01-07 09:55:09', null, '2016-01-12 15:22:21', '');
INSERT INTO `cms_attachment` VALUES ('81', null, 'Desert.jpg', '/html/resource/Desert.jpg', '845941', '2016-01-10 23:33:58', null, '2016-01-12 16:52:00', '');
INSERT INTO `cms_attachment` VALUES ('82', null, 'Chrysanthemum.jpg', '/html/resource/Chrysanthemum.jpg', '879394', '2016-01-11 17:12:49', null, '2016-01-12 16:52:04', '');
INSERT INTO `cms_attachment` VALUES ('83', null, 'pic_shop_img3.png', '/html/resource/pic_shop_img3.png', '142780', '2016-01-11 20:25:47', null, '2016-01-12 16:52:09', '');
INSERT INTO `cms_attachment` VALUES ('84', null, 'img2.jpg', '/html/resource/img2.jpg', '164114', '2016-01-11 20:27:33', null, '2016-01-12 16:52:12', '');
INSERT INTO `cms_attachment` VALUES ('85', null, '123jpg.jpg', '/html/resource/123jpg.jpg', '36645', '2016-01-11 20:33:04', null, '2016-01-12 16:52:39', '');
INSERT INTO `cms_attachment` VALUES ('86', null, 'img4.jpg', '/html/resource/img4.jpg', '121561', '2016-01-11 20:33:53', null, '2016-01-12 16:52:42', '');
INSERT INTO `cms_attachment` VALUES ('87', null, '123.jpg', '/html/resource/123.jpg', '53301', '2016-01-11 20:35:57', null, '2016-01-12 16:52:47', '');
INSERT INTO `cms_attachment` VALUES ('88', null, 'img4.jpg', '/html/resource/img4.jpg', '121561', '2016-01-11 20:36:54', null, '2016-01-12 16:52:52', '');
INSERT INTO `cms_attachment` VALUES ('89', null, 'img6.jpg', '/html/resource/img6.jpg', '139510', '2016-01-11 20:45:38', null, '2016-01-12 16:52:54', '');
INSERT INTO `cms_attachment` VALUES ('90', null, 'img4.jpg', '/html/resource/img4.jpg', '121561', '2016-01-11 20:52:11', null, '2016-01-12 16:52:58', '');
INSERT INTO `cms_attachment` VALUES ('91', null, 'Desert.jpg', '/html/resource/Desert.jpg', '845941', '2016-01-14 17:41:54', null, '2016-01-27 17:10:07', '');
INSERT INTO `cms_attachment` VALUES ('92', null, 'Hydrangeas.jpg', '/html/resource/Hydrangeas.jpg', '595284', '2016-01-14 17:50:55', null, '2016-01-27 17:10:13', '');
INSERT INTO `cms_attachment` VALUES ('93', null, 'Desert.jpg', '/html/resource/Desert.jpg', '845941', '2016-01-14 17:51:16', null, '2016-01-27 17:10:18', '');
INSERT INTO `cms_attachment` VALUES ('94', null, 'Lighthouse.jpg', '/html/resource/Lighthouse.jpg', '561276', '2016-01-14 17:52:56', null, '2016-01-27 17:10:20', '');
INSERT INTO `cms_attachment` VALUES ('95', null, 'Desert.jpg', '/html/resource/Desert.jpg', '845941', '2016-01-14 17:53:59', null, '2016-01-27 17:10:24', '');
INSERT INTO `cms_attachment` VALUES ('96', null, 'pentahobi.pdf', '/html/resource/pentahobi.pdf', '2420416', '2016-01-16 10:58:36', null, '2016-01-27 17:10:26', '');
INSERT INTO `cms_attachment` VALUES ('97', null, 'Java编程思想第四版完整中文高清版(免费).pdf', '/html/resource/Java编程思想第四版完整中文高清版(免费).pdf', '2320410', '2016-01-16 10:58:56', null, '2016-01-27 17:10:30', '');
INSERT INTO `cms_attachment` VALUES ('98', null, '经信委前置机建表sql.doc', '/html/resource/经信委前置机建表sql.doc', '79872', '2016-01-16 11:45:00', null, '2016-01-27 17:10:33', '');
INSERT INTO `cms_attachment` VALUES ('99', null, 'jdom.doc', '/html/resource/jdom.doc', '131584', '2016-01-16 14:20:09', null, '2016-01-27 17:10:35', '');
INSERT INTO `cms_attachment` VALUES ('100', null, 'Spring.doc', '/html/resource/Spring.doc', '93696', '2016-01-16 14:30:21', null, '2016-01-27 17:10:38', '');
INSERT INTO `cms_attachment` VALUES ('101', null, 'jdom.doc', '/html/resource/jdom.doc', '131584', '2016-01-16 14:30:27', null, '2016-01-27 17:10:40', '');
INSERT INTO `cms_attachment` VALUES ('102', null, '知识点总结.xls', '/html/resource/知识点总结.xls', '14848', '2016-01-16 14:30:35', null, '2016-01-27 17:10:44', '');
INSERT INTO `cms_attachment` VALUES ('103', null, 'jdom.doc', '/html/resource/jdom.doc', '131584', '2016-01-16 14:30:47', null, '2016-01-27 17:10:46', '');
INSERT INTO `cms_attachment` VALUES ('104', null, '201413022.rar', '/html/resource/201413022.rar', '1714951', '2016-01-16 14:31:02', null, '2016-01-27 17:10:52', '');
INSERT INTO `cms_attachment` VALUES ('105', null, 'jspxcms-5.2.4-release-tomcat.zip', '/html/resource/jspxcms-5.2.4-release-tomcat.zip', '50300848', '2016-01-16 14:31:22', null, '2016-01-27 17:10:58', '');
INSERT INTO `cms_attachment` VALUES ('106', null, 'IMG_20160113_153851.jpg', '/html/resource/IMG_20160113_153851.jpg', '4067786', '2016-01-16 17:50:42', null, '2016-01-27 17:11:02', '');
INSERT INTO `cms_attachment` VALUES ('107', null, 'Hydrangeas.jpg', '/html/resource/1453387015174.jpg', '595284', '2016-01-21 22:36:55', null, '2016-01-21 22:36:55', '');
INSERT INTO `cms_attachment` VALUES ('108', null, 'Chrysanthemum.jpg', '/html/resource/1453387068860.jpg', '879394', '2016-01-21 22:37:48', null, '2016-01-21 22:37:48', '');
INSERT INTO `cms_attachment` VALUES ('109', null, '2015082801-市共享交换平台节点接入 [经信委]-实施工作单.docx', '/html/resource/1453387123676.docx', '24285', '2016-01-21 22:38:43', null, '2016-01-21 22:38:43', '');
INSERT INTO `cms_attachment` VALUES ('110', null, '2015082801-市共享交换平台节点接入 [经信委]-实施工作单.docx', '/html/resource/1453387139048.docx', '24285', '2016-01-21 22:38:59', null, '2016-01-21 22:38:59', '');
