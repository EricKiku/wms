/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 8.0.28 : Database - wms
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`wms` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `wms`;

/*Table structure for table `inku` */

DROP TABLE IF EXISTS `inku`;

CREATE TABLE `inku` (
  `in_id` int NOT NULL AUTO_INCREMENT COMMENT '入库id',
  `in_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '入库编号，相同编号为同一次入库',
  `in_type` varchar(10) DEFAULT NULL COMMENT '入库类型',
  `in_warehouse` int DEFAULT NULL COMMENT '入库仓库',
  `p_id` int DEFAULT NULL COMMENT '产品id',
  `in_amount` int DEFAULT '0' COMMENT '本产品本次入库数量',
  `in_date` varchar(10) DEFAULT NULL COMMENT '入库时间',
  `u_id` int DEFAULT NULL COMMENT '入库人员',
  `in_img` varchar(100) DEFAULT NULL COMMENT '入库图片',
  PRIMARY KEY (`in_id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `inku` */

insert  into `inku`(`in_id`,`in_code`,`in_type`,`in_warehouse`,`p_id`,`in_amount`,`in_date`,`u_id`,`in_img`) values (21,'1708423303449','采购入库',7,43,1,'2024-02-20',1,NULL),(22,'1708423351026','采购入库',7,43,1,'2024-02-20',1,NULL),(23,'1708423352330','采购入库',7,43,1,'2024-02-20',1,NULL),(24,'1708423373115','采购入库',7,43,1,'2024-02-20',1,NULL),(25,'1708423395122','采购入库',7,43,1,'2024-02-20',1,NULL),(26,'1708423475442','采购入库',7,43,10,'2024-02-20',1,NULL),(27,'1708423671993','采购入库',7,43,10,'2024-02-20',1,NULL),(28,'1708423699600','采购入库',7,43,10,'2024-02-20',1,NULL),(29,'1708423754313','采购入库',7,43,10,'2024-02-20',1,NULL),(30,'1708423836204','采购入库',8,43,10,'2024-02-20',1,NULL),(31,'1708423856400','采购入库',7,44,1,'2024-02-20',1,NULL),(32,'1708423863130','采购入库',7,44,1,'2024-02-20',1,NULL),(33,'1708427540868','采购入库',7,43,100,'2024-02-20',1,NULL),(34,'1708428761602','借入入库',9,45,100,'2024-02-20',1,NULL);

/*Table structure for table `inventory` */

DROP TABLE IF EXISTS `inventory`;

CREATE TABLE `inventory` (
  `i_id` int NOT NULL AUTO_INCREMENT,
  `p_id` int NOT NULL,
  `w_id` int NOT NULL,
  `i_amount` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`i_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `inventory` */

insert  into `inventory`(`i_id`,`p_id`,`w_id`,`i_amount`) values (1,43,7,100),(2,43,8,10),(3,44,7,2),(4,45,9,100);

/*Table structure for table `outku` */

DROP TABLE IF EXISTS `outku`;

CREATE TABLE `outku` (
  `out_id` int NOT NULL AUTO_INCREMENT,
  `out_code` varchar(20) DEFAULT NULL,
  `out_type` varchar(10) DEFAULT NULL,
  `out_warehouse` int DEFAULT NULL,
  `out_address` varchar(50) DEFAULT NULL,
  `p_id` int DEFAULT NULL,
  `out_amount` int DEFAULT '0',
  `out_date` varchar(10) DEFAULT NULL,
  `u_id` int DEFAULT NULL,
  `out_img` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`out_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `outku` */

insert  into `outku`(`out_id`,`out_code`,`out_type`,`out_warehouse`,`out_address`,`p_id`,`out_amount`,`out_date`,`u_id`,`out_img`) values (4,'1708427223110','销售出库',7,'XXX',43,10,'2024-02-20',1,NULL),(5,'1708427434572','销售出库',7,'',43,30,'2024-02-20',1,NULL);

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `p_id` int NOT NULL AUTO_INCREMENT COMMENT '产品id',
  `p_prop` varchar(4) NOT NULL DEFAULT '成品' COMMENT '产品属性：成品/零件',
  `p_type` varchar(20) DEFAULT NULL COMMENT '产品类型',
  `p_name` varchar(20) DEFAULT NULL COMMENT '产品名称',
  `p_brand` varchar(20) DEFAULT NULL COMMENT '产品品牌',
  `p_model` varchar(20) DEFAULT NULL COMMENT '型号',
  `p_unit` varchar(5) DEFAULT '个' COMMENT '单位',
  `p_cost` int DEFAULT NULL COMMENT '成本',
  `p_price` int DEFAULT NULL COMMENT '销售价格',
  `p_img` varchar(100) DEFAULT NULL COMMENT '产品图片',
  PRIMARY KEY (`p_id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `product` */

insert  into `product`(`p_id`,`p_prop`,`p_type`,`p_name`,`p_brand`,`p_model`,`p_unit`,`p_cost`,`p_price`,`p_img`) values (43,'成品','电子设备','鼠标','XXX','XXX','个',30,50,NULL),(44,'成品','电子设备','键盘','狼途','LT1','个',45,85,NULL),(45,'零件','电子设备','USB数据线','XX','XX','件',10,20,NULL);

/*Table structure for table `rule` */

DROP TABLE IF EXISTS `rule`;

CREATE TABLE `rule` (
  `u_id` int NOT NULL AUTO_INCREMENT COMMENT '用户uid',
  `u_name` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户name',
  `u_account` varchar(20) NOT NULL,
  `u_pwd` varchar(20) NOT NULL,
  `u_rule` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'admin' COMMENT '用户规则权限',
  `u_menu` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '12345' COMMENT '用户菜单',
  PRIMARY KEY (`u_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `rule` */

insert  into `rule`(`u_id`,`u_name`,`u_account`,`u_pwd`,`u_rule`,`u_menu`) values (1,'adminer1','admin','admin','admin','12345'),(2,'车梦泽','cmz','cmz','user','1235'),(3,'谢秋颖','xqy','xqy','user','1235'),(4,'小鸭子','xyz','xyz','user','1235'),(6,'奥特曼','atm','atm','admin','12345');

/*Table structure for table `warehouse` */

DROP TABLE IF EXISTS `warehouse`;

CREATE TABLE `warehouse` (
  `w_id` int NOT NULL AUTO_INCREMENT COMMENT '仓库id',
  `w_name` varchar(10) NOT NULL DEFAULT '仓库' COMMENT '仓库名',
  `w_address` varchar(30) DEFAULT NULL COMMENT '仓库地址',
  `w_capacity` int DEFAULT NULL COMMENT '仓库容量-立方',
  `w_state` varchar(5) NOT NULL DEFAULT 'true' COMMENT '仓库状态；true：启用，false：停用',
  `w_charge` int DEFAULT NULL COMMENT '仓库负责人id',
  `w_inventory` int DEFAULT '0' COMMENT '仓库库存',
  PRIMARY KEY (`w_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `warehouse` */

insert  into `warehouse`(`w_id`,`w_name`,`w_address`,`w_capacity`,`w_state`,`w_charge`,`w_inventory`) values (7,'北京仓A','北京XXX',100,'true',1,0),(8,'北京仓B','北京XXX',200,'true',1,0),(9,'邯郸仓1A','邯郸XXX',200,'true',1,0);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
