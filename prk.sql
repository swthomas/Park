-- MySQL dump 10.13  Distrib 5.6.35, for osx10.9 (x86_64)
--
-- Host: localhost    Database: prk
-- ------------------------------------------------------
-- Server version	5.6.35

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `lister`
--

DROP TABLE IF EXISTS `lister`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lister` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `socialSecurity` int(11) NOT NULL,
  `paypalAccount` varchar(45) NOT NULL,
  `userId` int(11) NOT NULL,
  `parkingSpotId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `socialSecurity_UNIQUE` (`socialSecurity`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `parkingSpotId_UNIQUE` (`parkingSpotId`),
  KEY `fk_lister_user1_idx` (`userId`),
  CONSTRAINT `fk_lister_user1` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lister`
--

LOCK TABLES `lister` WRITE;
/*!40000 ALTER TABLE `lister` DISABLE KEYS */;
INSERT INTO `lister` VALUES (1,111223333,'',1,1);
/*!40000 ALTER TABLE `lister` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `listeraddress`
--

DROP TABLE IF EXISTS `listeraddress`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `listeraddress` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `street` varchar(45) NOT NULL,
  `street2` varchar(45) DEFAULT NULL,
  `postalCode` int(11) NOT NULL,
  `city` varchar(45) NOT NULL,
  `state` varchar(45) NOT NULL,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  `listerId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_listerAddress_lister1_idx` (`listerId`),
  CONSTRAINT `fk_listerAddress_lister1` FOREIGN KEY (`listerId`) REFERENCES `lister` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `listeraddress`
--

LOCK TABLES `listeraddress` WRITE;
/*!40000 ALTER TABLE `listeraddress` DISABLE KEYS */;
INSERT INTO `listeraddress` VALUES (1,'111 1st st.',NULL,809231,'Denver','CO',0,0,1);
/*!40000 ALTER TABLE `listeraddress` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parkingsensor`
--

DROP TABLE IF EXISTS `parkingsensor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parkingsensor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `occupied` tinyint(1) NOT NULL DEFAULT '0',
  `parkingSpotId` int(11) NOT NULL,
  `parkingTagId` int(11) DEFAULT NULL,
  `serialNumber` varchar(25) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `parkingSensorId_UNIQUE` (`id`),
  KEY `fk_parkingSensor_parkingSpot1_idx` (`parkingSpotId`),
  KEY `fk_parkingSensor_parkingTag1_idx` (`parkingTagId`),
  CONSTRAINT `fk_parkingSensor_parkingSpot1` FOREIGN KEY (`parkingSpotId`) REFERENCES `parkingspot` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_parkingSensor_parkingTag1` FOREIGN KEY (`parkingTagId`) REFERENCES `parkingtag` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parkingsensor`
--

LOCK TABLES `parkingsensor` WRITE;
/*!40000 ALTER TABLE `parkingsensor` DISABLE KEYS */;
INSERT INTO `parkingsensor` VALUES (1,0,1,1,'111111111'),(2,0,2,NULL,'1234'),(3,0,3,NULL,'2345sdfg'),(4,0,4,NULL,'2345weg'),(5,0,5,NULL,'3456sdfg'),(6,0,6,NULL,'234sdf'),(7,0,7,NULL,'sdf2345'),(8,0,8,NULL,'sdf4sdf'),(9,0,9,NULL,'234sdf234'),(10,0,10,NULL,'asd1342');
/*!40000 ALTER TABLE `parkingsensor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parkingspot`
--

DROP TABLE IF EXISTS `parkingspot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parkingspot` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(45) NOT NULL,
  `rate` double NOT NULL,
  `listerId` int(11) NOT NULL,
  `parkingSpotAddressId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_parkingSpot_lister1_idx` (`listerId`),
  KEY `fk_parkingSpot_parkingSpotAddress1_idx` (`parkingSpotAddressId`),
  CONSTRAINT `fk_parkingSpot_lister1` FOREIGN KEY (`listerId`) REFERENCES `lister` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_parkingSpot_parkingSpotAddress1` FOREIGN KEY (`parkingSpotAddressId`) REFERENCES `parkingspotaddress` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parkingspot`
--

LOCK TABLES `parkingspot` WRITE;
/*!40000 ALTER TABLE `parkingspot` DISABLE KEYS */;
INSERT INTO `parkingspot` VALUES (1,'Parking spot for your car at my house',2.99,1,1),(2,'Parking spot for your car at my house',2.99,1,2),(3,'Parking spot for your car at my house',2.99,1,3),(4,'Parking spot for your car at my house',2.99,1,4),(5,'Parking spot for your car at my house',2.99,1,5),(6,'Parking spot for your car at my house',4.25,1,6),(7,'Parking spot for your car at my house',4.25,1,7),(8,'Parking spot for your car at my house',5.25,1,8),(9,'Parking spot for your car at my house',10.25,1,9),(10,'Parking spot for your car at my house',10.25,1,10),(11,'Parking spot for your car at my house',11.25,1,11);
/*!40000 ALTER TABLE `parkingspot` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parkingspotaddress`
--

DROP TABLE IF EXISTS `parkingspotaddress`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parkingspotaddress` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `street` varchar(45) NOT NULL,
  `street2` varchar(45) DEFAULT NULL,
  `postalCode` int(11) NOT NULL,
  `city` varchar(45) NOT NULL,
  `state` varchar(45) NOT NULL,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parkingspotaddress`
--

LOCK TABLES `parkingspotaddress` WRITE;
/*!40000 ALTER TABLE `parkingspotaddress` DISABLE KEYS */;
INSERT INTO `parkingspotaddress` VALUES (1,'32 S Tejon St.',NULL,80903,'Colorado Springs','CO',38.832697,-104.824003),(2,'20 E Colorado Ave',NULL,80903,'Colorado Springs','CO',38.832504,-104.824278),(3,'17 S Tejon St',NULL,80903,'Colorado Springs','CO',38.833065,104.823034),(4,'21 S Tejon St',NULL,80903,'Colorado Springs','CO',38.832959,-104.823043),(5,'1 S Nevada Ave',NULL,80903,'Colorado Springs','CO',38.832799,-104.821202),(6,'18 S Nevada Ave',NULL,80903,'Colorado Springs','CO',38.833053,-104.822296),(7,'211 E Colorado Ave',NULL,80903,'Colorado Springs','CO',36.155835,-115.151762),(8,'123 S Weber St',NULL,80903,'Colorado Springs','CO',38.831477,-104.819694),(9,'1 S Nevada Ave #110',NULL,80903,'Colorado Springs','CO',38.832799,-104.821202),(10,'117 E Pikes Peak Ave',NULL,80903,'Colorado Springs','CO',38.833416,-104.822589),(11,'14205 E. Coachman Dr.',NULL,80908,'Colorado Springs','CO',39.042988,-104.662546);
/*!40000 ALTER TABLE `parkingspotaddress` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parkingtag`
--

DROP TABLE IF EXISTS `parkingtag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parkingtag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `serialNumber` varchar(45) NOT NULL,
  `userId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `serialNumber_UNIQUE` (`serialNumber`),
  KEY `fk_parkingTag_user1_idx` (`userId`),
  CONSTRAINT `fk_parkingTag_user1` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parkingtag`
--

LOCK TABLES `parkingtag` WRITE;
/*!40000 ALTER TABLE `parkingtag` DISABLE KEYS */;
INSERT INTO `parkingtag` VALUES (1,'1234567890abcd',1);
/*!40000 ALTER TABLE `parkingtag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paymenttolister`
--

DROP TABLE IF EXISTS `paymenttolister`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paymenttolister` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `listerId` int(11) NOT NULL,
  `date` date NOT NULL,
  `amount` double NOT NULL,
  `userPaymentId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_payment_lister1_idx` (`listerId`),
  KEY `fk_paymentToLister_userPayment1_idx` (`userPaymentId`),
  CONSTRAINT `fk_paymentToLister_userPayment1` FOREIGN KEY (`userPaymentId`) REFERENCES `userpayment` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_payment_lister1` FOREIGN KEY (`listerId`) REFERENCES `lister` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paymenttolister`
--

LOCK TABLES `paymenttolister` WRITE;
/*!40000 ALTER TABLE `paymenttolister` DISABLE KEYS */;
INSERT INTO `paymenttolister` VALUES (1,1,'2017-01-01',45.54,1);
/*!40000 ALTER TABLE `paymenttolister` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `photo`
--

DROP TABLE IF EXISTS `photo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `photo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parkingSpotId` int(11) NOT NULL,
  `image` longblob,
  PRIMARY KEY (`id`),
  KEY `fk_photos_parkingSpot1_idx` (`parkingSpotId`),
  CONSTRAINT `fk_photos_parkingSpot1` FOREIGN KEY (`parkingSpotId`) REFERENCES `parkingspot` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `photo`
--

LOCK TABLES `photo` WRITE;
/*!40000 ALTER TABLE `photo` DISABLE KEYS */;
/*!40000 ALTER TABLE `photo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservation`
--

DROP TABLE IF EXISTS `reservation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `reservedFromDate` datetime NOT NULL,
  `reservedToDate` datetime NOT NULL,
  `rate` double NOT NULL,
  `userId` int(11) NOT NULL,
  `vehicleId` int(11) NOT NULL,
  `parkingSpotId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_reservation_user1_idx` (`userId`),
  KEY `fk_reservation_vehicle1_idx` (`vehicleId`),
  KEY `fk_reservation_parkingSpot1_idx` (`parkingSpotId`),
  CONSTRAINT `fk_reservation_parkingSpot1` FOREIGN KEY (`parkingSpotId`) REFERENCES `parkingspot` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_reservation_user1` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_reservation_vehicle1` FOREIGN KEY (`vehicleId`) REFERENCES `vehicle` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservation`
--

LOCK TABLES `reservation` WRITE;
/*!40000 ALTER TABLE `reservation` DISABLE KEYS */;
INSERT INTO `reservation` VALUES (1,'2017-07-01 12:00:00','2017-07-01 02:00:00',2.99,1,1,1),(2,'2017-07-01 03:00:00','2017-07-01 03:15:00',2.99,1,1,1),(3,'2017-01-01 12:00:00','2017-01-01 01:00:00',2.99,1,1,1),(4,'2017-01-01 12:00:00','2017-01-01 12:45:00',2.99,1,1,1),(5,'2017-07-02 14:00:00','2017-07-02 14:16:00',2.99,1,1,2);
/*!40000 ALTER TABLE `reservation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `firstName` varchar(45) DEFAULT NULL,
  `lastName` varchar(45) DEFAULT NULL,
  `phoneNumber` double DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(300) NOT NULL,
  `isLister` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `username_UNIQUE` (`username`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `phoneNumber_UNIQUE` (`phoneNumber`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'Steve','Thompson',1112223333,'steveThompson@sd.com','sthompson','$2a$10$Rpp0ifShCef/yjAsXHzszulI2G0lfRPZ6VZjIwdJ2fnJjLO9qdxc.',1),(2,NULL,NULL,NULL,'test@test.com','test','$2a$10$cAVOXfisnxgr4511XHsVdOJ2Lwq0ZozXH0PdEhqnpQjt8ppazZyIC',0),(3,'steve','c',1,'test2@test.com','test2','$2a$10$2Hozjw1rxhp3u6qWy0KfuORY01isozwYyD5vXGOGeBsAb.djsI3d2',0);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userpayment`
--

DROP TABLE IF EXISTS `userpayment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userpayment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` date NOT NULL,
  `rate` double NOT NULL,
  `parkingSpotId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `amount` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_payments_parkingSpot1_idx` (`parkingSpotId`),
  KEY `fk_userPayment_user1_idx` (`userId`),
  CONSTRAINT `fk_payments_parkingSpot1` FOREIGN KEY (`parkingSpotId`) REFERENCES `parkingspot` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_userPayment_user1` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userpayment`
--

LOCK TABLES `userpayment` WRITE;
/*!40000 ALTER TABLE `userpayment` DISABLE KEYS */;
INSERT INTO `userpayment` VALUES (1,'2019-05-01',2.99,1,1,23.12);
/*!40000 ALTER TABLE `userpayment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicle`
--

DROP TABLE IF EXISTS `vehicle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vehicle` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `year` int(11) NOT NULL,
  `make` varchar(45) NOT NULL,
  `model` varchar(45) NOT NULL,
  `licensePlate` varchar(45) NOT NULL,
  `color` varchar(45) NOT NULL,
  `userId` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `licensePlate_UNIQUE` (`licensePlate`),
  KEY `fk_vehicle_customer1_idx` (`userId`),
  CONSTRAINT `fk_vehicle_customer1` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicle`
--

LOCK TABLES `vehicle` WRITE;
/*!40000 ALTER TABLE `vehicle` DISABLE KEYS */;
INSERT INTO `vehicle` VALUES (1,1981,'Porsche','911 Targa','test111','Red',1),(2,1969,'Chevy','Camaro','test112','Blue',1);
/*!40000 ALTER TABLE `vehicle` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-08-22 20:56:16
