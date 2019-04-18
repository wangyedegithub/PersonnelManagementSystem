/*
SQLyog Ultimate v12.08 (32 bit)
MySQL - 5.5.57 : Database - personnelmanagementsystem
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`personnelmanagementsystem` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `personnelmanagementsystem`;

/*Table structure for table `dept_inf` */

DROP TABLE IF EXISTS `dept_inf`;

CREATE TABLE `dept_inf` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(50) NOT NULL,
  `REMARK` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `dept_inf` */

insert  into `dept_inf`(`ID`,`NAME`,`REMARK`) values (1,'技术部','技术部1'),(2,'运营部','运营部1'),(3,'财务部','财务部'),(5,'总公办','总公办'),(10,'测试部','测试'),(11,'人事部','人事部');

/*Table structure for table `document_inf` */

DROP TABLE IF EXISTS `document_inf`;

CREATE TABLE `document_inf` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TITLE` varchar(50) NOT NULL,
  `filename` varchar(300) NOT NULL,
  `REMARK` varchar(300) DEFAULT NULL,
  `CREATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `USER_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_DOCUMENT_USER` (`USER_ID`),
  CONSTRAINT `FK_DOCUMENT_USER` FOREIGN KEY (`USER_ID`) REFERENCES `user_inf` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*Data for the table `document_inf` */

insert  into `document_inf`(`ID`,`TITLE`,`filename`,`REMARK`,`CREATE_DATE`,`USER_ID`) values (2,'物质的人','yu.jpg','好好学习天天向上1','2018-07-27 16:50:00',7),(4,'虎   牙','AppMarket.exe','一款类容丰富的直播软件','2018-07-29 09:12:53',14),(5,'222222','密码.txt',NULL,'2019-02-23 19:09:09',7);

/*Table structure for table `employee_inf` */

DROP TABLE IF EXISTS `employee_inf`;

CREATE TABLE `employee_inf` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DEPT_ID` int(11) NOT NULL,
  `JOB_ID` int(11) NOT NULL,
  `NAME` varchar(20) NOT NULL,
  `CARD_ID` varchar(18) NOT NULL,
  `ADDRESS` varchar(50) NOT NULL,
  `POST_CODE` varchar(50) DEFAULT NULL,
  `TEL` varchar(16) DEFAULT NULL,
  `PHONE` varchar(11) NOT NULL,
  `QQ_NUM` varchar(10) DEFAULT NULL,
  `EMAIL` varchar(50) NOT NULL,
  `SEX` int(11) NOT NULL DEFAULT '1',
  `PARTY` varchar(10) DEFAULT NULL,
  `BIRTHDAY` datetime DEFAULT NULL,
  `RACE` varchar(100) DEFAULT NULL,
  `EDUCATION` varchar(10) DEFAULT NULL,
  `SPECIALITY` varchar(20) DEFAULT NULL,
  `HOBBY` varchar(100) DEFAULT NULL,
  `REMARK` varchar(500) DEFAULT NULL,
  `CREATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  KEY `FK_EMP_DEPT` (`DEPT_ID`),
  KEY `FK_EMP_JOB` (`JOB_ID`),
  CONSTRAINT `FK_EMP_DEPT` FOREIGN KEY (`DEPT_ID`) REFERENCES `dept_inf` (`ID`),
  CONSTRAINT `FK_EMP_JOB` FOREIGN KEY (`JOB_ID`) REFERENCES `job_inf` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `employee_inf` */

insert  into `employee_inf`(`ID`,`DEPT_ID`,`JOB_ID`,`NAME`,`CARD_ID`,`ADDRESS`,`POST_CODE`,`TEL`,`PHONE`,`QQ_NUM`,`EMAIL`,`SEX`,`PARTY`,`BIRTHDAY`,`RACE`,`EDUCATION`,`SPECIALITY`,`HOBBY`,`REMARK`,`CREATE_DATE`) values (1,3,6,'玛利亚','420621199611200639','广州天河','510000','020-77777777','13902001111','36750066','251425887@qq.com',2,'党员','2018-07-12 00:00:00','满','本科','美声','唱歌','四大天王','2016-03-14 11:35:18'),(2,2,1,'杰克','22623','43234','42427424','42242','4247242','42424','251425887@qq.com',2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2016-03-14 11:35:18'),(3,1,2,'bb','432801197711251038','广州','510000','020-99999999','13907351532','36750064','36750064@qq.com',1,'党员','1977-11-25 00:00:00','汉','本科','计算机','爬山','无','2016-07-14 09:54:52'),(4,1,1,'技术部','420621199611200639','湖北','441100','264587','15972568485','1968685482','1968685482@qq.com',1,'党员','1996-11-20 00:00:00','汉','高中','it','无','技术部1','2018-07-26 23:02:05'),(7,1,1,'刘佳瑞','420621199810200539','湖北','440000','264587','15385268485','35486789','1968789482@qq.com',1,'群众','2018-07-12 00:00:00','苗','高中','it','111111','11111','2018-07-29 09:03:51');

/*Table structure for table `job_inf` */

DROP TABLE IF EXISTS `job_inf`;

CREATE TABLE `job_inf` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `NAME` varchar(50) NOT NULL,
  `REMARK` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

/*Data for the table `job_inf` */

insert  into `job_inf`(`ID`,`NAME`,`REMARK`) values (1,'职员','职员1'),(2,'Java开发工程师','Java开发工程师'),(3,'Java中级开发工程师','Java中级开发工程师'),(4,'Java高级开发工程师','Java高级开发工程师'),(5,'系统管理员','系统管理员'),(6,'架构师','架构师'),(7,'主管','主管'),(8,'经理','经理'),(9,'总经理','总经理'),(10,'司机','司机'),(11,'扫地僧','扫地');

/*Table structure for table `notice_inf` */

DROP TABLE IF EXISTS `notice_inf`;

CREATE TABLE `notice_inf` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `TITLE` varchar(50) NOT NULL,
  `CONTENT` text NOT NULL,
  `CREATE_DATE` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `USER_ID` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_NOTICE_USER` (`USER_ID`),
  CONSTRAINT `FK_NOTICE_USER` FOREIGN KEY (`USER_ID`) REFERENCES `user_inf` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `notice_inf` */

insert  into `notice_inf`(`ID`,`TITLE`,`CONTENT`,`CREATE_DATE`,`USER_ID`) values (1,'物质的人2','22222','2018-07-27 12:37:43',14),(3,'学  习','haohaoxuexi天天向上','2018-07-29 09:07:36',14);

/*Table structure for table `user_inf` */

DROP TABLE IF EXISTS `user_inf`;

CREATE TABLE `user_inf` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `loginname` varchar(20) NOT NULL,
  `PASSWORD` varchar(16) NOT NULL,
  `STATUS` int(11) NOT NULL DEFAULT '1',
  `createdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `username` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

/*Data for the table `user_inf` */

insert  into `user_inf`(`ID`,`loginname`,`PASSWORD`,`STATUS`,`createdate`,`username`) values (1,'admin','123456',4,'2016-03-12 09:34:28','超级管理员'),(2,'tom','123456',2,'2018-07-24 21:18:31','超级管理员'),(7,'jack','123456',1,'2018-07-25 09:49:35','make'),(9,'bcd','123456',1,'2018-07-25 15:19:23','abc'),(10,'mali','123456',1,'2018-07-25 15:20:12','mali'),(13,'玛利亚','123456',1,'2018-07-26 10:12:37','管理员'),(14,'玛丽','123456',1,'2018-07-26 10:14:18','管理员'),(15,'Jack1','123456',1,'2018-07-27 11:04:18','thinkgem'),(16,'bcdtomxun','123456',2,'2018-07-29 08:53:57','tomxun');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
