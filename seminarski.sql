/*
SQLyog Ultimate v10.00 Beta1
MySQL - 5.5.5-10.4.17-MariaDB : Database - seminarski
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`seminarski` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `seminarski`;

/*Table structure for table `admin` */

DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `adminID` bigint(55) NOT NULL AUTO_INCREMENT,
  `email` varchar(55) DEFAULT NULL,
  `password` varchar(55) DEFAULT NULL,
  PRIMARY KEY (`adminID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

/*Data for the table `admin` */

insert  into `admin`(`adminID`,`email`,`password`) values (1,'sinisavlajic@fon.rs','123'),(2,'olivera@fon.rs','123'),(3,'admin','admin'),(4,'admin2','admin2');

/*Table structure for table `plan` */

DROP TABLE IF EXISTS `plan`;

CREATE TABLE `plan` (
  `studentID` bigint(20) NOT NULL,
  `subjectID` bigint(20) NOT NULL,
  `dateCreated` date DEFAULT NULL,
  `allitems` varchar(55) DEFAULT NULL,
  `completedItems` varchar(55) DEFAULT NULL,
  PRIMARY KEY (`studentID`,`subjectID`),
  KEY `fk_subject` (`subjectID`),
  CONSTRAINT `fk_student` FOREIGN KEY (`studentID`) REFERENCES `student` (`studentID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_subject` FOREIGN KEY (`subjectID`) REFERENCES `subject` (`subjectID`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `plan` */

/*Table structure for table `planitem` */

DROP TABLE IF EXISTS `planitem`;

CREATE TABLE `planitem` (
  `subjectID` bigint(55) NOT NULL,
  `studentID` bigint(55) NOT NULL,
  `planItemID` bigint(55) NOT NULL AUTO_INCREMENT,
  `comment` varchar(55) DEFAULT NULL,
  `deadline` date DEFAULT NULL,
  `completed` tinyint(4) DEFAULT NULL,
  `projectID` bigint(55) DEFAULT NULL,
  `subjectProjectID` bigint(55) DEFAULT NULL,
  PRIMARY KEY (`subjectID`,`studentID`,`planItemID`),
  KEY `planItemID` (`planItemID`),
  KEY `fk_studentID` (`studentID`),
  KEY `fk_projectID` (`projectID`),
  KEY `fk_subjectProject` (`subjectProjectID`),
  CONSTRAINT `fk_projectID` FOREIGN KEY (`projectID`) REFERENCES `project` (`projectID`) ON UPDATE CASCADE,
  CONSTRAINT `fk_studentID` FOREIGN KEY (`studentID`) REFERENCES `plan` (`studentID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_subjectID` FOREIGN KEY (`subjectID`) REFERENCES `plan` (`subjectID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_subjectProject` FOREIGN KEY (`subjectProjectID`) REFERENCES `project` (`subjectID`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8mb4;

/*Data for the table `planitem` */

/*Table structure for table `project` */

DROP TABLE IF EXISTS `project`;

CREATE TABLE `project` (
  `projectID` bigint(55) NOT NULL AUTO_INCREMENT,
  `name` varchar(55) DEFAULT NULL,
  `description` varchar(55) DEFAULT NULL,
  `deadline` date DEFAULT NULL,
  `maxPoints` int(55) DEFAULT NULL,
  `subjectID` bigint(55) NOT NULL,
  PRIMARY KEY (`projectID`,`subjectID`),
  KEY `fk_predmetID` (`subjectID`),
  CONSTRAINT `fk_predmetID` FOREIGN KEY (`subjectID`) REFERENCES `subject` (`subjectID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;

/*Data for the table `project` */

insert  into `project`(`projectID`,`name`,`description`,`deadline`,`maxPoints`,`subjectID`) values (11,'Kolokvijum1','Radi se 2h, prvih 7 nedelja','2020-03-03',25,11),(12,'Kolokvijum2','Radi se 2h, od 7-13 vezbi','2021-03-06',25,11),(13,'Usmeni','2 pitanja iz oba dela ','2020-02-07',50,11),(14,'Dodatni Bodovi','Aktivnot na casu','2020-03-07',10,11),(15,'sd','sd','2021-02-02',233,11);

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `studentID` bigint(55) NOT NULL AUTO_INCREMENT,
  `index` varchar(55) DEFAULT NULL,
  `firstName` varchar(55) DEFAULT NULL,
  `lastName` varchar(55) DEFAULT NULL,
  `email` varchar(55) DEFAULT NULL,
  `password` varchar(55) DEFAULT NULL,
  `phoneNumber` varchar(55) DEFAULT NULL,
  PRIMARY KEY (`studentID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

/*Data for the table `student` */

insert  into `student`(`studentID`,`index`,`firstName`,`lastName`,`email`,`password`,`phoneNumber`) values (1,'105/16','Vladimir','Lekovac','vlekovac@hotmail.com','123','0691560308'),(2,'255/16','Aleksa','Stanojkovic','student','student','123');

/*Table structure for table `subject` */

DROP TABLE IF EXISTS `subject`;

CREATE TABLE `subject` (
  `subjectID` bigint(55) NOT NULL AUTO_INCREMENT,
  `name` varchar(55) DEFAULT NULL,
  `description` varchar(55) DEFAULT NULL,
  `semestar` int(55) DEFAULT NULL,
  `espb` int(55) DEFAULT NULL,
  `adminID` bigint(55) DEFAULT NULL,
  PRIMARY KEY (`subjectID`),
  KEY `fk_adminID` (`adminID`),
  CONSTRAINT `fk_adminID` FOREIGN KEY (`adminID`) REFERENCES `admin` (`adminID`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4;

/*Data for the table `subject` */

insert  into `subject`(`subjectID`,`name`,`description`,`semestar`,`espb`,`adminID`) values (11,'Matematika1','Pismeni(2 kolokvijuma) + Usmeni',1,6,3);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
