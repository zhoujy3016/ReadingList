/*
SQLyog Community v13.0.1 (64 bit)
MySQL - 5.7.20-log : Database - dbbook
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`dbbook` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `dbbook`;

/*Table structure for table `book` */

DROP TABLE IF EXISTS `book`;

CREATE TABLE `book` (
  `id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `reader` varchar(20) DEFAULT NULL,
  `isbn` varchar(20) DEFAULT NULL,
  `title` varchar(20) DEFAULT NULL,
  `author` varchar(20) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  `publish_id` int(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `book` */

insert  into `book`(`id`,`reader`,`isbn`,`title`,`author`,`description`,`publish_id`) values 
(0000000001,NULL,'1123232923','他改变了中国','江泽民','测试测试',1),
(0000000002,NULL,'54546532356','Spring boot实战','Craig Walls','实战经典书籍',2),
(0000000003,NULL,'5654871103235','Spring Cloud实战','Cook','这本书很棒！',3),
(0000000004,NULL,'978-7-121-32825-1','spring boot 2精髓','李家智','好书',4),
(0000000005,NULL,'978-7-121-28208-9','Java EE开发的颠覆者','王云飞','也是本好书啊',5),
(0000000006,NULL,'978-7-121-33390-3','Spring MVC MyBatis开发','主要光','',6),
(0000000007,NULL,'978-7-121-32362-1','高性能SQL调优精要与案例分析','闫书清','',7);

/*Table structure for table `publish` */

DROP TABLE IF EXISTS `publish`;

CREATE TABLE `publish` (
  `publish_id` int(255) NOT NULL AUTO_INCREMENT,
  `publish_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`publish_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `publish` */

insert  into `publish`(`publish_id`,`publish_name`) values 
(1,'中国人民出版社'),
(2,'人民邮电出版社'),
(3,'电子工业出版社'),
(4,'中国工业出版社'),
(5,'新西兰工业出版社'),
(6,'朝鲜劳动党工业出版社'),
(7,'加拿大工业出版社'),
(8,'朝鲜工人出版社'),
(9,'朝鲜劳动党出版社'),
(10,'大连高新园区出版社'),
(11,'沙河口区出版社');

/*Table structure for table `sys_dict` */

DROP TABLE IF EXISTS `sys_dict`;

CREATE TABLE `sys_dict` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL COMMENT '字典名称',
  `type` varchar(100) NOT NULL COMMENT '字典类型',
  `code` varchar(100) NOT NULL COMMENT '字典码',
  `value` varchar(1000) NOT NULL COMMENT '字典值',
  `order_num` int(11) DEFAULT '0' COMMENT '排序',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `del_flag` tinyint(4) DEFAULT '0' COMMENT '删除标记  -1：已删除  0：正常',
  PRIMARY KEY (`id`),
  UNIQUE KEY `type` (`type`,`code`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='数据字典表';

/*Data for the table `sys_dict` */

insert  into `sys_dict`(`id`,`name`,`type`,`code`,`value`,`order_num`,`remark`,`del_flag`) values 
(1,'性别','sex','0','女',0,NULL,0),
(2,'性别','sex','1','男',1,NULL,0),
(3,'性别','sex','2','未知',3,NULL,0),
(4,'民族','mz','1','汉族',1,NULL,0),
(5,'民族','mz','2','满族',2,NULL,0),
(6,'民族','mz','3','朝鲜族',3,NULL,0),
(7,'地区','area','1','沈阳',0,NULL,0),
(8,'地区','area','2','大连',1,NULL,0),
(9,'地区','area','3','丹东',2,NULL,0),
(13,'出版社','pub','1','电子工业出版社',1,NULL,0),
(14,'出版社','pub','2','朝鲜劳动党出版社',2,NULL,0),
(15,'出版社','pub','3','工人出版社',3,NULL,0);

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`name`) values 
(1,'ROLE_ADMIN'),
(2,'ROLE_USER');

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`username`,`password`) values 
(1,'zhoujy','123456'),
(2,'admin','admin');

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `user_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`user_id`,`role_id`) values 
(2,1),
(1,2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
