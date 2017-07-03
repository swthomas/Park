-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema prk
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `prk` ;

-- -----------------------------------------------------
-- Schema prk
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `prk` DEFAULT CHARACTER SET utf8 ;
USE `prk` ;

-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `firstName` VARCHAR(45) NOT NULL,
  `lastName` VARCHAR(45) NOT NULL,
  `phoneNumber` DOUBLE NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `isLister` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `phoneNumber_UNIQUE` ON `user` (`phoneNumber` ASC);

CREATE UNIQUE INDEX `email_UNIQUE` ON `user` (`email` ASC);

CREATE UNIQUE INDEX `id_UNIQUE` ON `user` (`id` ASC);

CREATE UNIQUE INDEX `username_UNIQUE` ON `user` (`username` ASC);


-- -----------------------------------------------------
-- Table `lister`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lister` ;

CREATE TABLE IF NOT EXISTS `lister` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `socialSecurity` INT NOT NULL,
  `paypalAccount` VARCHAR(45) NOT NULL,
  `userId` INT NOT NULL,
  `parkingSpotId` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_lister_user1`
    FOREIGN KEY (`userId`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `parkingSpotId_UNIQUE` ON `lister` (`parkingSpotId` ASC);

CREATE UNIQUE INDEX `socialSecurity_UNIQUE` ON `lister` (`socialSecurity` ASC);

CREATE UNIQUE INDEX `id_UNIQUE` ON `lister` (`id` ASC);

CREATE INDEX `fk_lister_user1_idx` ON `lister` (`userId` ASC);


-- -----------------------------------------------------
-- Table `vehicle`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `vehicle` ;

CREATE TABLE IF NOT EXISTS `vehicle` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `year` INT NOT NULL,
  `make` VARCHAR(45) NOT NULL,
  `model` VARCHAR(45) NOT NULL,
  `licensePlate` VARCHAR(45) NOT NULL,
  `color` VARCHAR(45) NOT NULL,
  `userId` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_vehicle_customer1`
    FOREIGN KEY (`userId`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `licensePlate_UNIQUE` ON `vehicle` (`licensePlate` ASC);

CREATE INDEX `fk_vehicle_customer1_idx` ON `vehicle` (`userId` ASC);


-- -----------------------------------------------------
-- Table `parkingSpotAddress`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `parkingSpotAddress` ;

CREATE TABLE IF NOT EXISTS `parkingSpotAddress` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `street` VARCHAR(45) NOT NULL,
  `street2` VARCHAR(45) NULL,
  `postalCode` INT NOT NULL,
  `city` VARCHAR(45) NOT NULL,
  `state` VARCHAR(45) NOT NULL,
  `latitude` DOUBLE NOT NULL,
  `longitude` DOUBLE NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `parkingSpot`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `parkingSpot` ;

CREATE TABLE IF NOT EXISTS `parkingSpot` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(45) NOT NULL,
  `rate` DOUBLE NOT NULL,
  `listerId` INT NOT NULL,
  `parkingSpotAddressId` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_parkingSpot_lister1`
    FOREIGN KEY (`listerId`)
    REFERENCES `lister` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_parkingSpot_parkingSpotAddress1`
    FOREIGN KEY (`parkingSpotAddressId`)
    REFERENCES `parkingSpotAddress` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_parkingSpot_lister1_idx` ON `parkingSpot` (`listerId` ASC);

CREATE INDEX `fk_parkingSpot_parkingSpotAddress1_idx` ON `parkingSpot` (`parkingSpotAddressId` ASC);


-- -----------------------------------------------------
-- Table `userPayment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `userPayment` ;

CREATE TABLE IF NOT EXISTS `userPayment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date` DATE NOT NULL,
  `rate` DOUBLE NOT NULL,
  `parkingSpotId` INT NOT NULL,
  `userId` INT NOT NULL,
  `amount` DOUBLE NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_payments_parkingSpot1`
    FOREIGN KEY (`parkingSpotId`)
    REFERENCES `parkingSpot` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_userPayment_user1`
    FOREIGN KEY (`userId`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_payments_parkingSpot1_idx` ON `userPayment` (`parkingSpotId` ASC);

CREATE INDEX `fk_userPayment_user1_idx` ON `userPayment` (`userId` ASC);


-- -----------------------------------------------------
-- Table `paymentToLister`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `paymentToLister` ;

CREATE TABLE IF NOT EXISTS `paymentToLister` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `listerId` INT NOT NULL,
  `date` DATE NOT NULL,
  `amount` DOUBLE NOT NULL,
  `userPaymentId` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_payment_lister1`
    FOREIGN KEY (`listerId`)
    REFERENCES `lister` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_paymentToLister_userPayment1`
    FOREIGN KEY (`userPaymentId`)
    REFERENCES `userPayment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_payment_lister1_idx` ON `paymentToLister` (`listerId` ASC);

CREATE INDEX `fk_paymentToLister_userPayment1_idx` ON `paymentToLister` (`userPaymentId` ASC);


-- -----------------------------------------------------
-- Table `listerAddress`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `listerAddress` ;

CREATE TABLE IF NOT EXISTS `listerAddress` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `street` VARCHAR(45) NOT NULL,
  `street2` VARCHAR(45) NULL,
  `postalCode` INT NOT NULL,
  `city` VARCHAR(45) NOT NULL,
  `state` VARCHAR(45) NOT NULL,
  `latitude` DOUBLE NOT NULL,
  `longitude` DOUBLE NOT NULL,
  `listerId` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_listerAddress_lister1`
    FOREIGN KEY (`listerId`)
    REFERENCES `lister` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_listerAddress_lister1_idx` ON `listerAddress` (`listerId` ASC);


-- -----------------------------------------------------
-- Table `reservation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `reservation` ;

CREATE TABLE IF NOT EXISTS `reservation` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `reservedFromDate` DATETIME NOT NULL,
  `reservedToDate` DATETIME NOT NULL,
  `rate` DOUBLE NOT NULL,
  `userId` INT NOT NULL,
  `vehicleId` INT NOT NULL,
  `parkingSpotId` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_reservation_user1`
    FOREIGN KEY (`userId`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_reservation_vehicle1`
    FOREIGN KEY (`vehicleId`)
    REFERENCES `vehicle` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_reservation_parkingSpot1`
    FOREIGN KEY (`parkingSpotId`)
    REFERENCES `parkingSpot` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_reservation_user1_idx` ON `reservation` (`userId` ASC);

CREATE INDEX `fk_reservation_vehicle1_idx` ON `reservation` (`vehicleId` ASC);

CREATE INDEX `fk_reservation_parkingSpot1_idx` ON `reservation` (`parkingSpotId` ASC);


-- -----------------------------------------------------
-- Table `parkingTag`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `parkingTag` ;

CREATE TABLE IF NOT EXISTS `parkingTag` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `serialNumber` VARCHAR(45) NOT NULL,
  `userId` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_parkingTag_user1`
    FOREIGN KEY (`userId`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `serialNumber_UNIQUE` ON `parkingTag` (`serialNumber` ASC);

CREATE INDEX `fk_parkingTag_user1_idx` ON `parkingTag` (`userId` ASC);


-- -----------------------------------------------------
-- Table `parkingSensor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `parkingSensor` ;

CREATE TABLE IF NOT EXISTS `parkingSensor` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `occupied` TINYINT(1) NOT NULL DEFAULT 0,
  `parkingSpotId` INT NOT NULL,
  `parkingTagId` INT NULL DEFAULT NULL,
  `serialNumber` VARCHAR(25) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_parkingSensor_parkingSpot1`
    FOREIGN KEY (`parkingSpotId`)
    REFERENCES `parkingSpot` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_parkingSensor_parkingTag1`
    FOREIGN KEY (`parkingTagId`)
    REFERENCES `parkingTag` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `parkingSensorId_UNIQUE` ON `parkingSensor` (`id` ASC);

CREATE INDEX `fk_parkingSensor_parkingSpot1_idx` ON `parkingSensor` (`parkingSpotId` ASC);

CREATE INDEX `fk_parkingSensor_parkingTag1_idx` ON `parkingSensor` (`parkingTagId` ASC);


-- -----------------------------------------------------
-- Table `photo`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `photo` ;

CREATE TABLE IF NOT EXISTS `photo` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `parkingSpotId` INT NOT NULL,
  `image` LONGBLOB NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_photos_parkingSpot1`
    FOREIGN KEY (`parkingSpotId`)
    REFERENCES `parkingSpot` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_photos_parkingSpot1_idx` ON `photo` (`parkingSpotId` ASC);


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `prk`;
INSERT INTO `user` (`id`, `firstName`, `lastName`, `phoneNumber`, `email`, `username`, `password`, `isLister`) VALUES (1, 'Steve', 'Thompson', 1112223333, 'steveThompson@sd.com', 'sthompson', 'password', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `lister`
-- -----------------------------------------------------
START TRANSACTION;
USE `prk`;
INSERT INTO `lister` (`id`, `socialSecurity`, `paypalAccount`, `userId`, `parkingSpotId`) VALUES (1, 111223333, DEFAULT, 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `vehicle`
-- -----------------------------------------------------
START TRANSACTION;
USE `prk`;
INSERT INTO `vehicle` (`id`, `year`, `make`, `model`, `licensePlate`, `color`, `userId`) VALUES (1, 1981, 'Porsche', '911 Targa', 'test111', 'Red', 1);
INSERT INTO `vehicle` (`id`, `year`, `make`, `model`, `licensePlate`, `color`, `userId`) VALUES (2, 1969, 'Chevy', 'Camaro', 'test112', 'Blue', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `parkingSpotAddress`
-- -----------------------------------------------------
START TRANSACTION;
USE `prk`;
INSERT INTO `parkingSpotAddress` (`id`, `street`, `street2`, `postalCode`, `city`, `state`, `latitude`, `longitude`) VALUES (1, '32 S Tejon St.', NULL, 80903, 'Colorado Springs', 'CO', 38.832697, -104.824003);
INSERT INTO `parkingSpotAddress` (`id`, `street`, `street2`, `postalCode`, `city`, `state`, `latitude`, `longitude`) VALUES (2, '20 E Colorado Ave', NULL, 80903, 'Colorado Springs', 'CO', 38.832504, -104.824278);
INSERT INTO `parkingSpotAddress` (`id`, `street`, `street2`, `postalCode`, `city`, `state`, `latitude`, `longitude`) VALUES (3, '17 S Tejon St', NULL, 80903, 'Colorado Springs', 'CO', 38.833065, -104.823034);
INSERT INTO `parkingSpotAddress` (`id`, `street`, `street2`, `postalCode`, `city`, `state`, `latitude`, `longitude`) VALUES (4, '21 S Tejon St', NULL, 80903, 'Colorado Springs', 'CO', 38.832959, -104.823043);
INSERT INTO `parkingSpotAddress` (`id`, `street`, `street2`, `postalCode`, `city`, `state`, `latitude`, `longitude`) VALUES (5, '1 S Nevada Ave', NULL, 80903, 'Colorado Springs', 'CO', 38.832799, -104.821202);
INSERT INTO `parkingSpotAddress` (`id`, `street`, `street2`, `postalCode`, `city`, `state`, `latitude`, `longitude`) VALUES (6, '18 S Nevada Ave', NULL, 80903, 'Colorado Springs', 'CO', 38.833053, -104.822296);
INSERT INTO `parkingSpotAddress` (`id`, `street`, `street2`, `postalCode`, `city`, `state`, `latitude`, `longitude`) VALUES (7, '211 E Colorado Ave', NULL, 80903, 'Colorado Springs', 'CO', 36.155835, -115.151762);
INSERT INTO `parkingSpotAddress` (`id`, `street`, `street2`, `postalCode`, `city`, `state`, `latitude`, `longitude`) VALUES (8, '123 S Weber St', NULL, 80903, 'Colorado Springs', 'CO', 38.831477, -104.819694);
INSERT INTO `parkingSpotAddress` (`id`, `street`, `street2`, `postalCode`, `city`, `state`, `latitude`, `longitude`) VALUES (9, '1 S Nevada Ave #110', NULL, 80903, 'Colorado Springs', 'CO', 38.832799, -104.821202);
INSERT INTO `parkingSpotAddress` (`id`, `street`, `street2`, `postalCode`, `city`, `state`, `latitude`, `longitude`) VALUES (10, '117 E Pikes Peak Ave', NULL, 80903, 'Colorado Springs', 'CO', 38.833416, -104.822589);
INSERT INTO `parkingSpotAddress` (`id`, `street`, `street2`, `postalCode`, `city`, `state`, `latitude`, `longitude`) VALUES (11, '14205 E. Coachman Dr.', NULL, 80908, 'Colorado Springs', 'CO', 39.042988, -104.662546);

COMMIT;


-- -----------------------------------------------------
-- Data for table `parkingSpot`
-- -----------------------------------------------------
START TRANSACTION;
USE `prk`;
INSERT INTO `parkingSpot` (`id`, `description`, `rate`, `listerId`, `parkingSpotAddressId`) VALUES (1, 'Parking spot for your car at my house', 2.99, 1, 1);
INSERT INTO `parkingSpot` (`id`, `description`, `rate`, `listerId`, `parkingSpotAddressId`) VALUES (2, 'Parking spot for your car at my house', 2.99, 1, 2);
INSERT INTO `parkingSpot` (`id`, `description`, `rate`, `listerId`, `parkingSpotAddressId`) VALUES (3, 'Parking spot for your car at my house', 2.99, 1, 3);
INSERT INTO `parkingSpot` (`id`, `description`, `rate`, `listerId`, `parkingSpotAddressId`) VALUES (4, 'Parking spot for your car at my house', 2.99, 1, 4);
INSERT INTO `parkingSpot` (`id`, `description`, `rate`, `listerId`, `parkingSpotAddressId`) VALUES (5, 'Parking spot for your car at my house', 2.99, 1, 5);
INSERT INTO `parkingSpot` (`id`, `description`, `rate`, `listerId`, `parkingSpotAddressId`) VALUES (6, 'Parking spot for your car at my house', 4, 1, 6);
INSERT INTO `parkingSpot` (`id`, `description`, `rate`, `listerId`, `parkingSpotAddressId`) VALUES (7, 'Parking spot for your car at my house', 4, 1, 7);
INSERT INTO `parkingSpot` (`id`, `description`, `rate`, `listerId`, `parkingSpotAddressId`) VALUES (8, 'Parking spot for your car at my house', 5, 1, 8);
INSERT INTO `parkingSpot` (`id`, `description`, `rate`, `listerId`, `parkingSpotAddressId`) VALUES (9, 'Parking spot for your car at my house', 10, 1, 9);
INSERT INTO `parkingSpot` (`id`, `description`, `rate`, `listerId`, `parkingSpotAddressId`) VALUES (10, 'Parking spot for your car at my house', 10, 1, 10);
INSERT INTO `parkingSpot` (`id`, `description`, `rate`, `listerId`, `parkingSpotAddressId`) VALUES (11, 'Parking spot for your car at my house', 11, 1, 11);

COMMIT;


-- -----------------------------------------------------
-- Data for table `userPayment`
-- -----------------------------------------------------
START TRANSACTION;
USE `prk`;
INSERT INTO `userPayment` (`id`, `date`, `rate`, `parkingSpotId`, `userId`, `amount`) VALUES (1, '2019-05-01', 2.99, 1, 1, 23.12);

COMMIT;


-- -----------------------------------------------------
-- Data for table `paymentToLister`
-- -----------------------------------------------------
START TRANSACTION;
USE `prk`;
INSERT INTO `paymentToLister` (`id`, `listerId`, `date`, `amount`, `userPaymentId`) VALUES (1, 1, '2017-01-01', 45.54, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `listerAddress`
-- -----------------------------------------------------
START TRANSACTION;
USE `prk`;
INSERT INTO `listerAddress` (`id`, `street`, `street2`, `postalCode`, `city`, `state`, `latitude`, `longitude`, `listerId`) VALUES (1, '111 1st st.', NULL, 809231, 'Denver', 'CO', DEFAULT, DEFAULT, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `reservation`
-- -----------------------------------------------------
START TRANSACTION;
USE `prk`;
INSERT INTO `reservation` (`id`, `reservedFromDate`, `reservedToDate`, `rate`, `userId`, `vehicleId`, `parkingSpotId`) VALUES (1, '2017-07-01 12:00:00', '2017-07-01 02:00:00', 2.99, 1, 1, 1);
INSERT INTO `reservation` (`id`, `reservedFromDate`, `reservedToDate`, `rate`, `userId`, `vehicleId`, `parkingSpotId`) VALUES (2, '2017-07-01 03:00:00', '2017-07-01 03:15:00', 2.99, 1, 1, 1);
INSERT INTO `reservation` (`id`, `reservedFromDate`, `reservedToDate`, `rate`, `userId`, `vehicleId`, `parkingSpotId`) VALUES (3, '2017-01-01 12:00:00', '2017-01-01 01:00:00', 2.99, 1, 1, 1);
INSERT INTO `reservation` (`id`, `reservedFromDate`, `reservedToDate`, `rate`, `userId`, `vehicleId`, `parkingSpotId`) VALUES (4, '2017-01-01 12:00:00', '2017-01-01 12:45:00', 2.99, 1, 1, 1);
INSERT INTO `reservation` (`id`, `reservedFromDate`, `reservedToDate`, `rate`, `userId`, `vehicleId`, `parkingSpotId`) VALUES (5, '2017-07-02 14:00:00', '2017-07-02 20:16:00', 2.99, 1, 1, 2);
INSERT INTO `reservation` (`id`, `reservedFromDate`, `reservedToDate`, `rate`, `userId`, `vehicleId`, `parkingSpotId`) VALUES (6, '2017-07-02 14:00:00', '2017-09-02 14:00:00', 2.99, 1, 1, 3);

COMMIT;


-- -----------------------------------------------------
-- Data for table `parkingTag`
-- -----------------------------------------------------
START TRANSACTION;
USE `prk`;
INSERT INTO `parkingTag` (`id`, `serialNumber`, `userId`) VALUES (1, '1234567890abcd', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `parkingSensor`
-- -----------------------------------------------------
START TRANSACTION;
USE `prk`;
INSERT INTO `parkingSensor` (`id`, `occupied`, `parkingSpotId`, `parkingTagId`, `serialNumber`) VALUES (1, 0, 1, 1, '111111111');
INSERT INTO `parkingSensor` (`id`, `occupied`, `parkingSpotId`, `parkingTagId`, `serialNumber`) VALUES (2, 0, 2, NULL, '1234');
INSERT INTO `parkingSensor` (`id`, `occupied`, `parkingSpotId`, `parkingTagId`, `serialNumber`) VALUES (3, 0, 3, NULL, '2345sdfg');
INSERT INTO `parkingSensor` (`id`, `occupied`, `parkingSpotId`, `parkingTagId`, `serialNumber`) VALUES (4, 0, 4, NULL, '2345weg');
INSERT INTO `parkingSensor` (`id`, `occupied`, `parkingSpotId`, `parkingTagId`, `serialNumber`) VALUES (5, 0, 5, NULL, '3456sdfg');
INSERT INTO `parkingSensor` (`id`, `occupied`, `parkingSpotId`, `parkingTagId`, `serialNumber`) VALUES (6, 0, 6, NULL, '234sdf');
INSERT INTO `parkingSensor` (`id`, `occupied`, `parkingSpotId`, `parkingTagId`, `serialNumber`) VALUES (7, 0, 7, NULL, 'sdf2345');
INSERT INTO `parkingSensor` (`id`, `occupied`, `parkingSpotId`, `parkingTagId`, `serialNumber`) VALUES (8, 0, 8, NULL, 'sdf4sdf');
INSERT INTO `parkingSensor` (`id`, `occupied`, `parkingSpotId`, `parkingTagId`, `serialNumber`) VALUES (9, 0, 9, NULL, '234sdf234');
INSERT INTO `parkingSensor` (`id`, `occupied`, `parkingSpotId`, `parkingTagId`, `serialNumber`) VALUES (10, 0, 10, NULL, 'asd1342');

COMMIT;

