-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.7.19-log - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出  表 py_money.menu0 结构
CREATE TABLE IF NOT EXISTS `menu0` (
  `id` int(11) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  py_money.menu0 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `menu0` DISABLE KEYS */;
INSERT INTO `menu0` (`id`, `name`) VALUES
	(1, '世界');
/*!40000 ALTER TABLE `menu0` ENABLE KEYS */;

-- 导出  表 py_money.menu1 结构
CREATE TABLE IF NOT EXISTS `menu1` (
  `id` int(11) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  py_money.menu1 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `menu1` DISABLE KEYS */;
INSERT INTO `menu1` (`id`, `name`, `parent_id`) VALUES
	(1, '亚洲', 1),
	(2, '美洲', 1),
	(3, '欧洲', 1);
/*!40000 ALTER TABLE `menu1` ENABLE KEYS */;

-- 导出  表 py_money.menu2 结构
CREATE TABLE IF NOT EXISTS `menu2` (
  `id` int(11) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  py_money.menu2 的数据：~8 rows (大约)
/*!40000 ALTER TABLE `menu2` DISABLE KEYS */;
INSERT INTO `menu2` (`id`, `name`, `parent_id`) VALUES
	(1, '中国', 1),
	(2, '韩国', 1),
	(4, '美国', 2),
	(3, '日本', 1),
	(5, '加拿大', 2),
	(6, '墨西哥', 2),
	(7, 'n1', 2),
	(8, '英国', 3),
	(9, '法国', 3);
/*!40000 ALTER TABLE `menu2` ENABLE KEYS */;

-- 导出  表 py_money.menu3 结构
CREATE TABLE IF NOT EXISTS `menu3` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- 正在导出表  py_money.menu3 的数据：~14 rows (大约)
/*!40000 ALTER TABLE `menu3` DISABLE KEYS */;
INSERT INTO `menu3` (`id`, `name`, `parent_id`) VALUES
	(1, '澳门', 1),
	(2, '香港', 1),
	(3, '首尔', 2),
	(4, '釜山', 2),
	(5, '纽约', 4),
	(6, '华盛顿', 4),
	(7, '安徽', 1),
	(8, '长崎', 3),
	(9, '东京', 3),
	(10, '多伦多', 5),
	(11, '渥太华', 6),
	(12, '渥太华1', 6),
	(13, '巴黎', 9),
	(14, '伦敦', 8),
	(15, '伦敦1', 8);
/*!40000 ALTER TABLE `menu3` ENABLE KEYS */;

-- 导出  表 py_money.menu4 结构
CREATE TABLE IF NOT EXISTS `menu4` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  py_money.menu4 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `menu4` DISABLE KEYS */;
/*!40000 ALTER TABLE `menu4` ENABLE KEYS */;

-- 导出  表 py_money.menu5 结构
CREATE TABLE IF NOT EXISTS `menu5` (
  `id` int(11) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  py_money.menu5 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `menu5` DISABLE KEYS */;
/*!40000 ALTER TABLE `menu5` ENABLE KEYS */;

-- 导出  表 py_money.py_process 结构
CREATE TABLE IF NOT EXISTS `py_process` (
  `process_id` int(11) NOT NULL AUTO_INCREMENT,
  `process_code` varchar(40) NOT NULL COMMENT '任务名',
  `process_name` varchar(40) NOT NULL,
  `process_remark` varchar(50) NOT NULL COMMENT '任务描述',
  `process_status` char(2) NOT NULL COMMENT '任务开关，on不可用，of可用，（目前该字段类似于disable）',
  `disable` tinyint(1) NOT NULL COMMENT '是否可用，1 可用，0 不可用',
  `process_cron` varchar(50) DEFAULT NULL COMMENT '定时任务cron，由空格分隔的7部分组成',
  `cron_calibration` tinyint(1) NOT NULL DEFAULT '0' COMMENT '临时定时任务校准，1 校准cron值，2 暂停该cron，3解暂停， 4 移除该cron，5  移除并disable=0，0 不操作',
  `every_day_start_flag` tinyint(1) NOT NULL DEFAULT '0' COMMENT '每天通过主任务启动标记，0不需要，1需要主任务来启动',
  `execute_max` int(11) NOT NULL DEFAULT '1' COMMENT '每天任务执行的最大次数',
  `process_limittime` int(11) NOT NULL DEFAULT '0' COMMENT '任务限制执行秒数。',
  PRIMARY KEY (`process_id`),
  UNIQUE KEY `process_type_process_code` (`process_code`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='py爬虫进程明细表（0级菜单py_process_m0_item,1级是py_process_m1_type,2级是py_process_m2_process)';

-- 正在导出表  py_money.py_process 的数据：~3 rows (大约)
/*!40000 ALTER TABLE `py_process` DISABLE KEYS */;
INSERT INTO `py_process` (`process_id`, `process_code`, `process_name`, `process_remark`, `process_status`, `disable`, `process_cron`, `cron_calibration`, `every_day_start_flag`, `execute_max`, `process_limittime`) VALUES
	(1, 'login_163', '登录163邮箱', '通过selenium和xpath登录163邮箱', 'of', 1, '0/20 * 0-23 * * * *', 0, 0, 5, 400),
	(2, 'login_139', '登录139邮箱', '通过selenium和xpath登录139邮箱', 'of', 1, '0/20 * 0-23 * * * *', 0, 0, 7, 400),
	(3, 'main_zz', '主定时，不停止', '给任务每天不停止，在一定时间会开启其它任务', 'of', 1, '0/20 * 0-23 * * * *', 0, 0, 1, 0);
/*!40000 ALTER TABLE `py_process` ENABLE KEYS */;

-- 导出  表 py_money.py_process_everyday 结构
CREATE TABLE IF NOT EXISTS `py_process_everyday` (
  `day_id` varchar(10) DEFAULT NULL,
  `process_code` varchar(40) DEFAULT NULL,
  `process_status` char(1) DEFAULT NULL COMMENT 'C 成功；D 失败,置D重跑；E报错； P 正在运行；N 还没有运行；T 其它',
  `status_remark` varchar(150) DEFAULT NULL COMMENT '状态描述',
  `start_time` datetime DEFAULT NULL COMMENT '开始时间',
  `end_time` datetime DEFAULT NULL COMMENT '结束时间',
  `action_flag` varchar(50) DEFAULT NULL COMMENT '一个暂时用不到的标记',
  `execute_count` int(11) DEFAULT NULL COMMENT '执行计数',
  KEY `day_id` (`day_id`),
  KEY `process_code` (`process_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='每天进程监控情况';

-- 正在导出表  py_money.py_process_everyday 的数据：~42 rows (大约)
/*!40000 ALTER TABLE `py_process_everyday` DISABLE KEYS */;
INSERT INTO `py_process_everyday` (`day_id`, `process_code`, `process_status`, `status_remark`, `start_time`, `end_time`, `action_flag`, `execute_count`) VALUES
	('20190929', 'login_163', 'C', NULL, '2019-09-29 17:57:50', '2019-09-29 17:58:05', NULL, 1),
	('20191008', 'login_139', 'C', NULL, '2019-10-08 15:18:00', '2019-10-08 15:18:09', NULL, 1),
	('20191112', 'login_163', 'C', NULL, '2019-11-12 18:12:00', '2019-11-12 18:12:20', NULL, 1),
	('20191112', 'login_139', 'C', NULL, '2019-11-12 18:12:00', '2019-11-12 18:12:09', NULL, 1),
	('20191113', 'login_163', 'C', NULL, '2019-11-13 09:55:45', '2019-11-13 09:56:00', NULL, 1),
	('20191113', 'login_139', 'C', NULL, '2019-11-13 09:56:00', '2019-11-13 09:56:09', NULL, 1),
	('20191114', 'login_139', 'C', NULL, NULL, NULL, NULL, 0),
	('20191114', 'main_zz', 'N', NULL, NULL, NULL, NULL, 0),
	('20191114', 'login_163', 'N', NULL, NULL, NULL, NULL, 0),
	('20191115', 'main_zz', 'C', NULL, '2019-11-15 18:06:30', '2019-11-15 18:06:30', NULL, 1),
	('20191115', 'login_139', 'C', NULL, '2019-11-15 18:06:40', '2019-11-15 18:07:08', NULL, 3),
	('20191115', 'login_163', 'C', NULL, '2019-11-15 18:06:40', '2019-11-15 18:06:59', NULL, 1),
	('20191118', 'login_139', 'C', NULL, '2019-11-18 16:37:20', '2019-11-18 16:37:50', NULL, 3),
	('20191118', 'main_zz', 'C', NULL, '2019-11-18 16:37:40', '2019-11-18 16:37:40', NULL, 1),
	('20191118', 'login_163', 'P', NULL, '2019-11-18 18:03:10', NULL, NULL, 0),
	('20191119', 'login_139', 'C', NULL, '2019-11-19 23:33:30', '2019-11-19 23:34:37', NULL, 3),
	('20191119', 'login_163', 'E', 'login_163 method move_to_element error,par: //*[@class=\'ScapTcha1\']', '2019-11-19 23:33:30', '2019-11-19 23:33:38', NULL, 0),
	('20191119', 'main_zz', 'N', NULL, NULL, NULL, NULL, 0),
	('20191120', 'login_139', 'P', NULL, '2019-11-20 10:06:00', NULL, NULL, 4),
	('20191120', 'login_163', 'E', 'login_163 method move_to_element error,par: //*[@class=\'ScapTcha1\']', '2019-11-20 10:06:00', '2019-11-20 10:06:08', NULL, 0),
	('20191120', 'main_zz', 'C', NULL, '2019-11-20 10:06:00', '2019-11-20 10:06:00', NULL, 1),
	('20191125', 'login_139', 'C', NULL, '2019-11-25 14:44:20', '2019-11-25 14:46:32', NULL, 7),
	('20191125', 'main_zz', 'C', NULL, '2019-11-25 14:44:20', '2019-11-25 14:44:20', NULL, 1),
	('20191125', 'login_163', 'C', NULL, '2019-11-25 15:01:40', '2019-11-25 15:03:15', NULL, 5),
	('20191202', 'login_139', 'D', NULL, '2019-12-02 16:41:00', NULL, NULL, 2),
	('20191202', 'login_163', 'D', NULL, '2019-12-02 16:41:00', NULL, NULL, 2),
	('20191202', 'main_zz', 'C', NULL, '2019-12-02 16:41:00', '2019-12-02 16:41:00', NULL, 1),
	('20191203', 'login_139', 'C', NULL, '2019-12-03 17:42:00', '2019-12-03 17:44:08', NULL, 7),
	('20191203', 'login_163', 'E', 'login_163 method clickEleByCharsKeysValues error,par: //*[@class=\'yidun_bg-img\']', '2019-12-03 17:42:00', '2019-12-03 17:43:41', NULL, 4),
	('20191203', 'main_zz', 'C', NULL, '2019-12-03 17:42:00', '2019-12-03 17:42:00', NULL, 1),
	('20191217', 'login_139', 'E', 'login_139 method url_path error,par:https://mail.10086.cn ', '2019-12-17 17:24:40', '2019-12-17 17:24:46', NULL, 0),
	('20191217', 'login_163', 'P', 'login_163 method url_path error,par:http://mail.163.com/ ', '2019-12-17 17:50:00', '2019-12-17 17:24:46', NULL, 0),
	('20191217', 'main_zz', 'C', NULL, '2019-12-17 16:41:00', '2019-12-17 16:41:00', NULL, 1),
	('20191230', 'login_139', 'E', 'login_139 method load_chrome error,par:../chromedriver ', '2019-12-30 18:27:00', '2019-12-30 18:27:00', NULL, 0),
	('20191230', 'login_163', 'E', 'login_163 method load_chrome error,par:../chromedriver ', '2019-12-30 18:27:00', '2019-12-30 18:27:00', NULL, 0),
	('20191230', 'main_zz', 'C', NULL, '2019-12-30 18:27:00', '2019-12-30 18:27:00', NULL, 1),
	('20200109', 'main_zz', 'C', NULL, '2020-01-09 16:27:20', '2020-01-09 16:27:20', NULL, 1),
	('20200109', 'login_139', 'E', 'login_139 method load_chrome error,par:../chromedriver ', '2020-01-09 16:45:40', '2020-01-09 16:45:40', NULL, 0),
	('20200109', 'login_163', 'E', 'login_163 method load_chrome error,par:../chromedriver ', '2020-01-09 16:54:40', '2020-01-09 16:54:40', NULL, 0),
	('20200110', 'login_163', 'E', 'login_163 method load_chrome error,par:../chromedriver ', '2020-01-10 12:53:00', '2020-01-10 12:53:00', NULL, 0),
	('20200110', 'login_139', 'E', 'login_139 method load_chrome error,par:../chromedriver ', '2020-01-10 12:53:00', '2020-01-10 12:53:00', NULL, 0),
	('20200110', 'main_zz', 'C', NULL, '2020-01-10 12:53:00', '2020-01-10 12:53:00', NULL, 1);
/*!40000 ALTER TABLE `py_process_everyday` ENABLE KEYS */;

-- 导出  表 py_money.py_process_m0_item 结构
CREATE TABLE IF NOT EXISTS `py_process_m0_item` (
  `item_code` varchar(50) NOT NULL COMMENT 'item 编码',
  `item_name` varchar(50) NOT NULL COMMENT 'item 名称',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `order` tinyint(4) NOT NULL DEFAULT '0' COMMENT '排序',
  `disable` tinyint(1) NOT NULL DEFAULT '1' COMMENT '=0：不可用,>=1： 可用，>=2：菜单可用，-1：删除',
  `remark` varchar(50) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`item_code`,`order`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='进程项目（0级菜单py_process_m0_item,1级是py_process_m1_type,2级是py_process_m2_process)';

-- 正在导出表  py_money.py_process_m0_item 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `py_process_m0_item` DISABLE KEYS */;
INSERT INTO `py_process_m0_item` (`item_code`, `item_name`, `create_time`, `order`, `disable`, `remark`) VALUES
	('item_process', '项目进程', '2020-04-13 20:45:03', 1, 2, NULL),
	('item_start', '项目启动', '2020-04-13 20:45:03', 0, 2, NULL);
/*!40000 ALTER TABLE `py_process_m0_item` ENABLE KEYS */;

-- 导出  表 py_money.py_process_m1_type 结构
CREATE TABLE IF NOT EXISTS `py_process_m1_type` (
  `type_code` varchar(50) NOT NULL COMMENT '进程类型编码',
  `item_code` varchar(50) NOT NULL COMMENT '项目编码',
  `name` varchar(50) NOT NULL COMMENT '进程名',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `order` tinyint(4) NOT NULL DEFAULT '0' COMMENT '排序',
  `disable` tinyint(1) NOT NULL DEFAULT '1' COMMENT '=0：不可用,>=1： 可用，>=2：菜单可用，-1：删除',
  `remark` varchar(150) NOT NULL COMMENT '描述',
  PRIMARY KEY (`item_code`,`type_code`,`order`),
  UNIQUE KEY `item_code_type_code` (`item_code`,`type_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='进程类型表（0级菜单py_process_m0_item,1级是py_process_m1_type,2级是py_process_m2_process)';

-- 正在导出表  py_money.py_process_m1_type 的数据：~3 rows (大约)
/*!40000 ALTER TABLE `py_process_m1_type` DISABLE KEYS */;
INSERT INTO `py_process_m1_type` (`type_code`, `item_code`, `name`, `create_time`, `order`, `disable`, `remark`) VALUES
	('login', 'item_process', '登陆进程', '2020-03-23 22:06:06', 1, 2, '登陆相关'),
	('main', 'item_process', '主进程', '2020-03-23 22:05:02', 0, 2, '主进程'),
	('start_py', 'item_start', '启动py', '2020-04-14 11:19:02', 0, 2, '启动py');
/*!40000 ALTER TABLE `py_process_m1_type` ENABLE KEYS */;

-- 导出  表 py_money.py_process_m2_process 结构
CREATE TABLE IF NOT EXISTS `py_process_m2_process` (
  `process_id` int(11) NOT NULL AUTO_INCREMENT,
  `process_code` varchar(40) NOT NULL COMMENT '任务名',
  `type_code` varchar(40) NOT NULL COMMENT '进程类型',
  `process_name` varchar(40) NOT NULL,
  `disable` tinyint(1) NOT NULL DEFAULT '1' COMMENT '=0：不可用,>=1： 可用，>=2：菜单可用，-1：删除',
  `order` tinyint(4) NOT NULL DEFAULT '0' COMMENT '排序',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`process_id`),
  UNIQUE KEY `process_type_process_code` (`type_code`,`process_code`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='py爬虫进程表（0级菜单py_process_m0_item,1级是py_process_m1_type,2级是py_process_m2_process)';

-- 正在导出表  py_money.py_process_m2_process 的数据：~4 rows (大约)
/*!40000 ALTER TABLE `py_process_m2_process` DISABLE KEYS */;
INSERT INTO `py_process_m2_process` (`process_id`, `process_code`, `type_code`, `process_name`, `disable`, `order`, `create_time`) VALUES
	(1, 'login_163', 'login', '登录163邮箱', 1, 0, '2020-04-14 11:31:17'),
	(2, 'login_139', 'login', '登录139邮箱', 1, 1, '2020-04-14 11:31:17'),
	(3, 'main_zz', 'main', '主定时，不停止', 2, 0, '2020-04-14 11:31:17'),
	(4, 'start_py_main', 'start_py', '启动py主进程', 2, 0, '2020-04-14 11:31:17');
/*!40000 ALTER TABLE `py_process_m2_process` ENABLE KEYS */;

-- 导出  表 py_money.py_process_task 结构
CREATE TABLE IF NOT EXISTS `py_process_task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `process_code` varchar(10) DEFAULT NULL COMMENT '任务编码',
  `node_code` varchar(40) DEFAULT NULL COMMENT '节点编码',
  `node_val` varchar(100) DEFAULT NULL COMMENT '节点值',
  `node_ele_xpath` varchar(200) DEFAULT NULL,
  `node_val_remark` varchar(200) DEFAULT NULL,
  `node_order` int(11) DEFAULT NULL COMMENT '节点顺序按照100，200，300，进行设置，防止变更，具有灵活性',
  `task_disable` tinyint(1) DEFAULT NULL COMMENT '任务状态，0 关闭，1 启用，2 暂停，3 其它',
  PRIMARY KEY (`id`),
  KEY `process_code` (`process_code`,`node_code`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COMMENT='python 爬虫进程任务表';

-- 正在导出表  py_money.py_process_task 的数据：~27 rows (大约)
/*!40000 ALTER TABLE `py_process_task` DISABLE KEYS */;
INSERT INTO `py_process_task` (`id`, `process_code`, `node_code`, `node_val`, `node_ele_xpath`, `node_val_remark`, `node_order`, `task_disable`) VALUES
	(1, 'login_163', 'url_path', 'http://mail.163.com/', '', '进入网站', 100, 1),
	(2, 'login_163', 'put_ele_val', 'idiot_jilidan@163.com', '//*[@id=\'account-box\']//div[2]//input', '输入邮箱地址', 200, 1),
	(3, 'login_163', 'put_ele_val', '15856571830zz', '//*[@id=\'login-form\']//div//div[3]//div[2]//input[2]', '输入密码', 300, 1),
	(4, 'login_163', 'load_chrome', '../chromedriver', '', '加载浏览器，获取driver', 90, 1),
	(5, 'login_163', 'switch_frame', NULL, '//iframe[contains(@id,\'x-URS-iframe\')]/@id', '定位到登录frame', 190, 1),
	(6, 'login_163', 'get_html', NULL, NULL, '获取html', 180, 1),
	(7, 'login_163', 'click', '', '//*[@id=\'switchAccountLogin\']', '点击‘密码登陆’', 185, 1),
	(8, 'login_163', 'time_delay', '0.5', NULL, '延迟0.5s，解决网站慢（这里有一个坑，在key_enter后，如果不延迟，会导致move_to_element失败，由于横条还没渲染出来，你move有啥用', 600, 1),
	(9, 'login_163', 'key_enter', NULL, '//*[@id=\'login-form\']//div//div[3]//div[2]//input[2]', '密码框按回车', 500, 1),
	(10, 'login_163', 'get_ele', '', '//*[@class=\'ScapTcha\']', '获取验证码下横条元素', 750, 0),
	(11, 'login_163', 'get_action', '', '', '获取事件驱动', 700, 1),
	(12, 'login_163', 'move_to_element', '', '//*[@class=\'ScapTcha\']', '执行事件：移动到横条元素，触发弹出验证码', 800, 1),
	(13, '人', NULL, NULL, NULL, NULL, NULL, NULL),
	(14, 'login_163', 'time_delay', '0.9', NULL, '延迟加载，不然提示汉字yidun_tips__answer 还没渲染', 900, 1),
	(15, 'login_163', 'get_ele_val', '', '//*[@class=\'yidun_tips__point\']', '获取提示点击的汉字', 1000, 1),
	(16, 'login_163', 'replace', '"\\x01', '', '引号替换成空串，按照\\x01分隔多个传参', 1100, 1),
	(17, 'login_163', 'split', '\\s+', '', '按照1个或者多个空格切割', 1200, 1),
	(18, 'login_163', 'saveImgLocal', 'E:/workfile/python/py_money/img/login_163', '//*[@class=\'yidun_bg-img\']', '保存图片', 1300, 1),
	(19, 'login_163', 'serchImgCharGetCoordinate', '', '', '识别上述图片的文字坐标，返回字典', 1400, 1),
	(20, 'login_163', 'clickEleByCharsKeysValues', '', '//*[@class=\'yidun_bg-img\']', '点击图片元素，通过varString字典内容', 1500, 1),
	(21, 'login_163', 'quit_driver', '', '', '退出driver', 1600, 1),
	(22, 'login_163', 'time_delay', '2', NULL, '延迟', 1550, 1),
	(23, 'login_139', 'load_chrome', '../chromedriver', '', '加载浏览器，获取driver', 90, 1),
	(24, 'login_139', 'url_path', 'https://mail.10086.cn', '', '进入网站', 100, 1),
	(25, 'login_139', 'put_ele_val', '\r\n15736267291@139.com', '//*[@id="txtUser"]', '输入邮箱地址', 200, 1),
	(26, 'login_139', 'put_ele_val', '15856571830zZ', '//*[@id="txtPass"]', '输入密码', 300, 1),
	(27, 'login_139', 'key_enter', NULL, '//*[@id="txtPass"]', '密码框按回车', 500, 1);
/*!40000 ALTER TABLE `py_process_task` ENABLE KEYS */;

-- 导出  表 py_money.py_process_task_node 结构
CREATE TABLE IF NOT EXISTS `py_process_task_node` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `method_type` varchar(50) NOT NULL COMMENT '节点属于的方法集类型',
  `node_type` varchar(50) DEFAULT NULL COMMENT '节点类型',
  `node_code` varchar(40) DEFAULT NULL COMMENT '节点编码',
  `node_name` varchar(40) DEFAULT NULL COMMENT '节点名称',
  `node_remark` varchar(100) DEFAULT NULL COMMENT '描述',
  `node_status` tinyint(1) DEFAULT '1' COMMENT '节点状态，1 生效，0 无效，9 待实现',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `node_code` (`node_code`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8 COMMENT='节点任务定义';

-- 正在导出表  py_money.py_process_task_node 的数据：~22 rows (大约)
/*!40000 ALTER TABLE `py_process_task_node` DISABLE KEYS */;
INSERT INTO `py_process_task_node` (`id`, `method_type`, `node_type`, `node_code`, `node_name`, `node_remark`, `node_status`, `create_time`) VALUES
	(1, 'crawler_selenium_node_method', NULL, 'url_path', 'url路径', NULL, 1, '2019-09-19 18:07:43'),
	(2, 'crawler_selenium_node_method', 'action', 'click', '点击', NULL, 1, '2019-09-19 18:07:43'),
	(3, 'crawler_selenium_node_method', 'element_ope', 'get_ele', '获取元素', NULL, 1, '2019-09-19 18:07:43'),
	(4, 'crawler_selenium_node_method', 'element_ope', 'get_ele_val', '获取元素值', NULL, 1, '2019-09-19 18:07:43'),
	(5, 'crawler_selenium_node_method', 'element_ope', 'put_ele_val', '元素赋值', NULL, 1, '2019-09-19 18:07:43'),
	(6, 'crawler_selenium_node_method', 'time_ope', 'time_delay', '延迟', NULL, 1, '2019-09-19 18:07:43'),
	(7, 'crawler_selenium_node_method', '', 'get_local', '获取位置', '', 1, '2019-09-19 18:07:43'),
	(8, 'crawler_selenium_node_method', 'action', 'get_action', '获取事件操作', NULL, 1, '2019-09-19 18:07:43'),
	(9, 'crawler_selenium_node_method', NULL, 'do_action', '执行事件', '此事件不好集成，暂时还是分开', 1, '2019-09-19 18:07:43'),
	(10, 'crawler_selenium_node_method', 'deal_data', 'deal_ele_data', '处理元素数据', '处理元素数据，针对传入的ele', 1, '2019-09-19 18:07:43'),
	(11, 'crawler_selenium_node_method', 'base_sys', 'load_chrome', '加载浏览器', NULL, 1, '2019-09-19 18:07:43'),
	(12, 'crawler_selenium_node_method', 'element_ope', 'switch_frame', '切换frame', '0 default_content(),1 parent_frame(),oth ', 1, '2019-09-19 18:07:43'),
	(13, 'crawler_selenium_node_method', 'element_ope', 'get_html', '获取页面', NULL, 1, '2019-09-19 18:07:43'),
	(14, 'crawler_selenium_node_method', 'action', 'key_enter', '在输入框回车', NULL, 1, '2019-09-19 18:07:43'),
	(15, 'crawler_selenium_node_method', 'action', 'move_to_element', '移动鼠标', NULL, 1, '2019-09-19 18:07:43'),
	(16, 'crawler_selenium_node_method', 'deal_data', 'replace', '替换', '按照\\x01分隔多个传参', 1, '2019-09-25 14:21:27'),
	(17, 'crawler_selenium_node_method', 'deal_data', 'split', '切割', '按照传入node_val 切割', 1, '2019-09-25 14:21:27'),
	(18, 'crawler_selenium_node_method', 'element_ope', 'saveImgLocal', '保存图片到本地', NULL, 1, '2019-09-19 18:07:43'),
	(19, 'crawler_selenium_node_method', 'deal_data', 'distinguishImgGetCharCoordinate', '识别图片，返回文字中心坐标', '', 9, '2019-09-25 14:21:27'),
	(20, 'crawler_selenium_node_method', 'deal_data', 'serchImgCharGetCoordinate', '查找图片中对应文字，返回坐标', '', 9, '2019-09-25 14:21:27'),
	(21, 'crawler_selenium_node_method', 'deal_data', 'clickEleByCharsKeysValues', '通过字典内容，点击对应图片坐标', '', 9, '2019-09-25 14:21:27'),
	(23, 'crawler_selenium_node_method', 'action', 'quit_driver', '退出driver', '', 1, '2019-09-25 14:21:27');
/*!40000 ALTER TABLE `py_process_task_node` ENABLE KEYS */;

-- 导出  表 py_money.py_process_task_node_assist 结构
CREATE TABLE IF NOT EXISTS `py_process_task_node_assist` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(50) DEFAULT NULL COMMENT 'point类型',
  `node_code` varchar(40) DEFAULT NULL COMMENT '任务节点',
  `node_point` varchar(40) DEFAULT NULL COMMENT '节点的组成点',
  `node_point_val` varchar(200) DEFAULT NULL COMMENT '组成点值',
  `point_remark` varchar(40) DEFAULT NULL COMMENT '组成点描述',
  `status` tinyint(1) DEFAULT NULL COMMENT '1 启用，0 弃用，2暂时保留，暂时不用',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `node_code_node_point` (`node_code`,`node_point`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='任务节点辅助表。---弃用_20190926';

-- 正在导出表  py_money.py_process_task_node_assist 的数据：~6 rows (大约)
/*!40000 ALTER TABLE `py_process_task_node_assist` DISABLE KEYS */;
INSERT INTO `py_process_task_node_assist` (`id`, `type`, `node_code`, `node_point`, `node_point_val`, `point_remark`, `status`, `create_time`) VALUES
	(1, 'general', 'do_action', 'move_to_element', NULL, '移动到该元素', 1, '2019-09-19 18:09:09'),
	(2, 'general', 'do_action', 'perform', NULL, 'action统一提交', 1, '2019-09-19 18:09:38'),
	(3, 'dealdata_regxp', '', '', NULL, '', 1, '2019-09-19 21:13:40'),
	(4, 'dealdata_py_small', 'deal_data', 'del_syh', NULL, '删除引号', 1, '2019-09-19 21:13:41'),
	(5, 'dealdata_py_small', 'deal_ele_data', 'del_syh', NULL, '删除双引号', 1, '2019-09-19 21:13:40'),
	(6, 'dealdata_py_small', 'deal_data', 'del_split', '1', '切割', 1, '2019-09-19 21:13:41');
/*!40000 ALTER TABLE `py_process_task_node_assist` ENABLE KEYS */;

-- 导出  表 py_money.py_style 结构
CREATE TABLE IF NOT EXISTS `py_style` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `type` varchar(25) NOT NULL COMMENT '类型。用来区分',
  `code` varchar(25) NOT NULL COMMENT '编码',
  `name` varchar(25) NOT NULL COMMENT '名称',
  `style` varchar(50) DEFAULT NULL COMMENT '风格',
  `icon_addr` varchar(250) DEFAULT NULL COMMENT 'icon地址，如某个图片全路径',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `diable` tinyint(4) NOT NULL DEFAULT '1' COMMENT '是否可用，1 ：可用，0：不可用',
  PRIMARY KEY (`id`),
  UNIQUE KEY `inx-type-code` (`type`,`code`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='风格，标签，样式表';

-- 正在导出表  py_money.py_style 的数据：~2 rows (大约)
/*!40000 ALTER TABLE `py_style` DISABLE KEYS */;
INSERT INTO `py_style` (`id`, `type`, `code`, `name`, `style`, `icon_addr`, `create_time`, `diable`) VALUES
	(1, 'icon_base', 'next-mark-right_1_16', '右圆箭头', NULL, '/img/i1/next-mark-right_1_16.png', '2020-04-13 20:11:54', 1),
	(4, 'icon_base', 'next-turn-share_1_16', '右箭头', NULL, '/img/i1/next-turn-share_1_16.png', '2020-04-13 20:11:54', 1);
/*!40000 ALTER TABLE `py_style` ENABLE KEYS */;

-- 导出  表 py_money.py_style_tooth 结构
CREATE TABLE IF NOT EXISTS `py_style_tooth` (
  `type` varchar(50) NOT NULL COMMENT '分类，防止重复',
  `item_code` varchar(50) NOT NULL COMMENT '项目编码',
  `style_code` varchar(50) NOT NULL COMMENT 'style 编码',
  `is_expend` tinyint(1) NOT NULL DEFAULT '1' COMMENT '1：扩展，0：不扩展',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `disable` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否启用，1：启用，0：不启用',
  UNIQUE KEY `type_item_code_style_code` (`type`,`item_code`,`style_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='关系到某些菜单等的风格';

-- 正在导出表  py_money.py_style_tooth 的数据：~9 rows (大约)
/*!40000 ALTER TABLE `py_style_tooth` DISABLE KEYS */;
INSERT INTO `py_style_tooth` (`type`, `item_code`, `style_code`, `is_expend`, `create_time`, `disable`) VALUES
	('main_m0', 'item_process', 'next-mark-right_1_16', 1, '2020-04-13 20:26:12', 1),
	('main_m0', 'item_start', 'next-mark-right_1_16', 1, '2020-04-13 20:26:12', 1),
	('main_m1', 'login', 'next-mark-right_1_16', 1, '2020-04-13 20:26:12', 1),
	('main_m1', 'main', 'next-mark-right_1_16', 0, '2020-04-13 20:26:12', 1),
	('main_m1', 'start_py', 'next-mark-right_1_16', 0, '2020-04-13 20:26:12', 1),
	('main_m2', 'login_139', 'next-turn-share_1_16', 1, '2020-04-13 20:26:12', 1),
	('main_m2', 'login_163', 'next-turn-share_1_16', 1, '2020-04-13 20:26:12', 1),
	('main_m2', 'main_zz', 'next-turn-share_1_16', 1, '2020-04-13 20:26:12', 1),
	('main_m2', 'start_py_main', 'next-turn-share_1_16', 0, '2020-04-13 20:26:12', 1);
/*!40000 ALTER TABLE `py_style_tooth` ENABLE KEYS */;

-- 导出  表 py_money.t1 结构
CREATE TABLE IF NOT EXISTS `t1` (
  `id` int(11) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `flag` varchar(50) DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  py_money.t1 的数据：~7 rows (大约)
/*!40000 ALTER TABLE `t1` DISABLE KEYS */;
INSERT INTO `t1` (`id`, `name`, `flag`) VALUES
	(1, 'aabb', '1'),
	(2, 'aabb', '1'),
	(3, 'tt', '3'),
	(3, 'c', '2'),
	(3, 'd', '6'),
	(2, 'd', '4'),
	(2, 'd', '444'),
	(3, 'tt', '333');
/*!40000 ALTER TABLE `t1` ENABLE KEYS */;

-- 导出  表 py_money.ttaa 结构
CREATE TABLE IF NOT EXISTS `ttaa` (
  `id` int(11) DEFAULT NULL,
  `aa` varchar(50) DEFAULT NULL,
  `c` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  py_money.ttaa 的数据：~3 rows (大约)
/*!40000 ALTER TABLE `ttaa` DISABLE KEYS */;
INSERT INTO `ttaa` (`id`, `aa`, `c`) VALUES
	(4, 'a', NULL),
	(5, 'b', NULL),
	(6, 'c', NULL);
/*!40000 ALTER TABLE `ttaa` ENABLE KEYS */;

-- 导出  表 py_money.user1 结构
CREATE TABLE IF NOT EXISTS `user1` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` char(10) NOT NULL,
  `age` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- 正在导出表  py_money.user1 的数据：~3 rows (大约)
/*!40000 ALTER TABLE `user1` DISABLE KEYS */;
INSERT INTO `user1` (`id`, `name`, `age`) VALUES
	(4, 'a', 1),
	(5, 'b', 1),
	(6, 'c', 1);
/*!40000 ALTER TABLE `user1` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
