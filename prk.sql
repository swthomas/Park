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


-- -----------------------------------------------------
-- Table `address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `address` ;

CREATE TABLE IF NOT EXISTS `address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `street` VARCHAR(45) NOT NULL,
  `street2` VARCHAR(45) NULL,
  `postalCode` INT NOT NULL,
  `city` VARCHAR(45) NOT NULL,
  `state` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `lister`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lister` ;

CREATE TABLE IF NOT EXISTS `lister` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `parkingSpotId` INT NOT NULL,
  `socialSecurity` INT NOT NULL,
  `addressId` INT NOT NULL,
  `userId` INT NOT NULL,
  `paypalAccount` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_lister_address1`
    FOREIGN KEY (`addressId`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_lister_user1`
    FOREIGN KEY (`userId`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `parkingSpotId_UNIQUE` ON `lister` (`parkingSpotId` ASC);

CREATE UNIQUE INDEX `socialSecurity_UNIQUE` ON `lister` (`socialSecurity` ASC);

CREATE INDEX `fk_lister_address1_idx` ON `lister` (`addressId` ASC);

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
-- Table `parkingSpot`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `parkingSpot` ;

CREATE TABLE IF NOT EXISTS `parkingSpot` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(45) NOT NULL,
  `pictureURL` VARCHAR(45) NULL,
  `rate` DOUBLE NOT NULL,
  `listerId` INT NOT NULL,
  `parkingTagId` INT NOT NULL,
  `addressId` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_parkingSpot_lister1`
    FOREIGN KEY (`listerId`)
    REFERENCES `lister` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_parkingSpot_parkingTag1`
    FOREIGN KEY (`parkingTagId`)
    REFERENCES `parkingTag` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_parkingSpot_address1`
    FOREIGN KEY (`addressId`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_parkingSpot_lister1_idx` ON `parkingSpot` (`listerId` ASC);

CREATE INDEX `fk_parkingSpot_parkingTag1_idx` ON `parkingSpot` (`parkingTagId` ASC);

CREATE INDEX `fk_parkingSpot_address1_idx` ON `parkingSpot` (`addressId` ASC);


-- -----------------------------------------------------
-- Table `creditCard`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `creditCard` ;

CREATE TABLE IF NOT EXISTS `creditCard` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `creditCardNumber` INT NOT NULL,
  `expirationDate` DATE NOT NULL,
  `cvv` INT NOT NULL,
  `activeStatus` TINYINT(1) NOT NULL DEFAULT 1,
  `addressId` INT NOT NULL,
  `userId` INT NOT NULL,
  PRIMARY KEY (`Id`),
  CONSTRAINT `fk_creditCard_address1`
    FOREIGN KEY (`addressId`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_creditCard_user1`
    FOREIGN KEY (`userId`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_creditCard_address1_idx` ON `creditCard` (`addressId` ASC);

CREATE INDEX `fk_creditCard_user1_idx` ON `creditCard` (`userId` ASC);


-- -----------------------------------------------------
-- Table `paymentToLister`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `paymentToLister` ;

CREATE TABLE IF NOT EXISTS `paymentToLister` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `listerId` INT NOT NULL,
  `date` DATE NOT NULL,
  `creditCardId` INT NOT NULL,
  `amount` DOUBLE NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_payment_lister1`
    FOREIGN KEY (`listerId`)
    REFERENCES `lister` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_payment_creditCard1`
    FOREIGN KEY (`creditCardId`)
    REFERENCES `creditCard` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_payment_lister1_idx` ON `paymentToLister` (`listerId` ASC);

CREATE INDEX `fk_payment_creditCard1_idx` ON `paymentToLister` (`creditCardId` ASC);


-- -----------------------------------------------------
-- Table `userPayment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `userPayment` ;

CREATE TABLE IF NOT EXISTS `userPayment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date` DATE NOT NULL,
  `rate` DOUBLE NOT NULL,
  `creditCardId` INT NOT NULL,
  `parkingSpotId` INT NOT NULL,
  `listerId` INT NOT NULL,
  `userId` INT NOT NULL,
  `amount` DOUBLE NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_payments_creditCard1`
    FOREIGN KEY (`creditCardId`)
    REFERENCES `creditCard` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_payments_parkingSpot1`
    FOREIGN KEY (`parkingSpotId`)
    REFERENCES `parkingSpot` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_payments_lister1`
    FOREIGN KEY (`listerId`)
    REFERENCES `lister` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_userPayment_user1`
    FOREIGN KEY (`userId`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_payments_creditCard1_idx` ON `userPayment` (`creditCardId` ASC);

CREATE INDEX `fk_payments_parkingSpot1_idx` ON `userPayment` (`parkingSpotId` ASC);

CREATE INDEX `fk_payments_lister1_idx` ON `userPayment` (`listerId` ASC);

CREATE INDEX `fk_userPayment_user1_idx` ON `userPayment` (`userId` ASC);


-- -----------------------------------------------------
-- Table `reservation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `reservation` ;

CREATE TABLE IF NOT EXISTS `reservation` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `reservedFromDate` DATETIME NOT NULL,
  `reservedToDate` DATETIME NOT NULL,
  `rate` DOUBLE NOT NULL,
  `parkingSpotId` INT NOT NULL,
  `creditCardId` INT NOT NULL,
  `userId` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_reservation_parkingSpot1`
    FOREIGN KEY (`parkingSpotId`)
    REFERENCES `parkingSpot` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_reservation_creditCard1`
    FOREIGN KEY (`creditCardId`)
    REFERENCES `creditCard` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_reservation_user1`
    FOREIGN KEY (`userId`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_reservation_parkingSpot1_idx` ON `reservation` (`parkingSpotId` ASC);

CREATE INDEX `fk_reservation_creditCard1_idx` ON `reservation` (`creditCardId` ASC);

CREATE INDEX `fk_reservation_user1_idx` ON `reservation` (`userId` ASC);


-- -----------------------------------------------------
-- Table `parkingSensor`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `parkingSensor` ;

CREATE TABLE IF NOT EXISTS `parkingSensor` (
  `id` INT NOT NULL,
  `occupied` TINYINT(1) NOT NULL,
  `parkingSpotId` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_parkingSensor_parkingSpot1`
    FOREIGN KEY (`parkingSpotId`)
    REFERENCES `parkingSpot` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `parkingSensorId_UNIQUE` ON `parkingSensor` (`id` ASC);

CREATE INDEX `fk_parkingSensor_parkingSpot1_idx` ON `parkingSensor` (`parkingSpotId` ASC);


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
-- Data for table `address`
-- -----------------------------------------------------
START TRANSACTION;
USE `prk`;
INSERT INTO `address` (`id`, `street`, `street2`, `postalCode`, `city`, `state`) VALUES (1, '111 1st st.', NULL, 809231, 'Denver', 'CO');

COMMIT;


-- -----------------------------------------------------
-- Data for table `lister`
-- -----------------------------------------------------
START TRANSACTION;
USE `prk`;
INSERT INTO `lister` (`id`, `parkingSpotId`, `socialSecurity`, `addressId`, `userId`, `paypalAccount`) VALUES (1, 1, 111223333, 1, 1, DEFAULT);

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
-- Data for table `parkingTag`
-- -----------------------------------------------------
START TRANSACTION;
USE `prk`;
INSERT INTO `parkingTag` (`id`, `serialNumber`, `userId`) VALUES (1, '1234567890abcd', 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `parkingSpot`
-- -----------------------------------------------------
START TRANSACTION;
USE `prk`;
INSERT INTO `parkingSpot` (`id`, `description`, `pictureURL`, `rate`, `listerId`, `parkingTagId`, `addressId`) VALUES (1, 'Parking spot for your car at my house', NULL, 2.99, 1, 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `creditCard`
-- -----------------------------------------------------
START TRANSACTION;
USE `prk`;
INSERT INTO `creditCard` (`Id`, `creditCardNumber`, `expirationDate`, `cvv`, `activeStatus`, `addressId`, `userId`) VALUES (1, 1111222233334444, '2019-05-01', 111, 1, 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `paymentToLister`
-- -----------------------------------------------------
START TRANSACTION;
USE `prk`;
INSERT INTO `paymentToLister` (`id`, `listerId`, `date`, `creditCardId`, `amount`) VALUES (1, 1, '2017-01-01', 1, 45.54);

COMMIT;


-- -----------------------------------------------------
-- Data for table `userPayment`
-- -----------------------------------------------------
START TRANSACTION;
USE `prk`;
INSERT INTO `userPayment` (`id`, `date`, `rate`, `creditCardId`, `parkingSpotId`, `listerId`, `userId`, `amount`) VALUES (1, '2019-05-01', 2.99, 1, 1, 1, 1, 23.12);

COMMIT;


-- -----------------------------------------------------
-- Data for table `reservation`
-- -----------------------------------------------------
START TRANSACTION;
USE `prk`;
INSERT INTO `reservation` (`id`, `reservedFromDate`, `reservedToDate`, `rate`, `parkingSpotId`, `creditCardId`, `userId`) VALUES (1, '2017-01-01 12:00:00', '2017-01-01 02:00:00', 2.99, 1, 1, 1);

COMMIT;


-- -----------------------------------------------------
-- Data for table `parkingSensor`
-- -----------------------------------------------------
START TRANSACTION;
USE `prk`;
INSERT INTO `parkingSensor` (`id`, `occupied`, `parkingSpotId`) VALUES (1, 1, 1);

COMMIT;

