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
-- Table `address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `address` ;

CREATE TABLE IF NOT EXISTS `address` (
  `id` INT NOT NULL,
  `street` VARCHAR(45) NOT NULL,
  `street2` VARCHAR(45) NULL,
  `postalCode` INT NOT NULL,
  `city` VARCHAR(45) NOT NULL,
  `state` VARCHAR(45) NOT NULL,
  `customerId` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_address_customer1`
    FOREIGN KEY (`customerId`)
    REFERENCES `customer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_address_customer1_idx` ON `address` (`customerId` ASC);


-- -----------------------------------------------------
-- Table `lister`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `lister` ;

CREATE TABLE IF NOT EXISTS `lister` (
  `id` INT NOT NULL,
  `parkingSpotId` INT NOT NULL,
  `socialSecurity` INT NOT NULL,
  `addressId` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_lister_address1`
    FOREIGN KEY (`addressId`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `parkingSpotId_UNIQUE` ON `lister` (`parkingSpotId` ASC);

CREATE UNIQUE INDEX `socialSecurity_UNIQUE` ON `lister` (`socialSecurity` ASC);

CREATE INDEX `fk_lister_address1_idx` ON `lister` (`addressId` ASC);

CREATE UNIQUE INDEX `id_UNIQUE` ON `lister` (`id` ASC);


-- -----------------------------------------------------
-- Table `parkingTag`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `parkingTag` ;

CREATE TABLE IF NOT EXISTS `parkingTag` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `userId` INT NOT NULL,
  `serialNumber` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;

CREATE UNIQUE INDEX `serialNumber_UNIQUE` ON `parkingTag` (`serialNumber` ASC);


-- -----------------------------------------------------
-- Table `customer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `customer` ;

CREATE TABLE IF NOT EXISTS `customer` (
  `id` INT NOT NULL,
  `firstName` VARCHAR(45) NOT NULL,
  `lastName` VARCHAR(45) NOT NULL,
  `addressId` INT NOT NULL,
  `phoneNumber` INT NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `listerId` INT NOT NULL,
  `userId` INT NOT NULL,
  `parkingTagId` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_customer_lister1`
    FOREIGN KEY (`listerId`)
    REFERENCES `lister` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_customer_parkingTag1`
    FOREIGN KEY (`parkingTagId`)
    REFERENCES `parkingTag` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `phoneNumber_UNIQUE` ON `customer` (`phoneNumber` ASC);

CREATE UNIQUE INDEX `email_UNIQUE` ON `customer` (`email` ASC);

CREATE INDEX `fk_customer_lister1_idx` ON `customer` (`listerId` ASC);

CREATE UNIQUE INDEX `id_UNIQUE` ON `customer` (`id` ASC);

CREATE INDEX `fk_customer_parkingTag1_idx` ON `customer` (`parkingTagId` ASC);


-- -----------------------------------------------------
-- Table `vehicle`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `vehicle` ;

CREATE TABLE IF NOT EXISTS `vehicle` (
  `id` INT NOT NULL,
  `year` INT NOT NULL,
  `make` VARCHAR(45) NOT NULL,
  `model` VARCHAR(45) NOT NULL,
  `licensePlate` VARCHAR(45) NOT NULL,
  `color` VARCHAR(45) NOT NULL,
  `userId` INT NOT NULL,
  `customer_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_vehicle_customer1`
    FOREIGN KEY (`customer_id`)
    REFERENCES `customer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE UNIQUE INDEX `licensePlate_UNIQUE` ON `vehicle` (`licensePlate` ASC);

CREATE INDEX `fk_vehicle_customer1_idx` ON `vehicle` (`customer_id` ASC);


-- -----------------------------------------------------
-- Table `parkingSpot`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `parkingSpot` ;

CREATE TABLE IF NOT EXISTS `parkingSpot` (
  `id` INT NOT NULL,
  `addressID` INT NOT NULL,
  `description` VARCHAR(45) NOT NULL,
  `pictureURL` VARCHAR(45) NULL,
  `rate` DOUBLE NOT NULL,
  `listerId` INT NOT NULL,
  `parkingTag_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_parkingSpot_lister1`
    FOREIGN KEY (`listerId`)
    REFERENCES `lister` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_parkingSpot_parkingTag1`
    FOREIGN KEY (`parkingTag_id`)
    REFERENCES `parkingTag` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_parkingSpot_lister1_idx` ON `parkingSpot` (`listerId` ASC);

CREATE INDEX `fk_parkingSpot_parkingTag1_idx` ON `parkingSpot` (`parkingTag_id` ASC);


-- -----------------------------------------------------
-- Table `creditCard`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `creditCard` ;

CREATE TABLE IF NOT EXISTS `creditCard` (
  `Id` INT NOT NULL,
  `creditCardNumber` INT NOT NULL,
  `expirationDate` DATE NOT NULL,
  `cvv` INT NOT NULL,
  `activeStatus` TINYINT(1) NOT NULL,
  `userId` INT NOT NULL,
  `addressId` INT NOT NULL,
  PRIMARY KEY (`Id`),
  CONSTRAINT `fk_creditCard_address1`
    FOREIGN KEY (`addressId`)
    REFERENCES `address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_creditCard_address1_idx` ON `creditCard` (`addressId` ASC);

CREATE UNIQUE INDEX `creditCardNumber_UNIQUE` ON `creditCard` (`creditCardNumber` ASC);


-- -----------------------------------------------------
-- Table `paymentToLister`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `paymentToLister` ;

CREATE TABLE IF NOT EXISTS `paymentToLister` (
  `id` INT NOT NULL,
  `paypalAccountNumber` INT NOT NULL,
  `listerId` INT NOT NULL,
  `date` DATE NOT NULL,
  `creditCardId` INT NOT NULL,
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

CREATE UNIQUE INDEX `paypalAccountNumber_UNIQUE` ON `paymentToLister` (`paypalAccountNumber` ASC);

CREATE INDEX `fk_payment_lister1_idx` ON `paymentToLister` (`listerId` ASC);

CREATE INDEX `fk_payment_creditCard1_idx` ON `paymentToLister` (`creditCardId` ASC);


-- -----------------------------------------------------
-- Table `userPayment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `userPayment` ;

CREATE TABLE IF NOT EXISTS `userPayment` (
  `id` INT NULL,
  `date` DATE NOT NULL,
  `rate` DOUBLE NOT NULL,
  `creditCardId` INT NOT NULL,
  `parkingSpotId` INT NOT NULL,
  `listerId` INT NOT NULL,
  `userId` INT NOT NULL,
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
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_payments_creditCard1_idx` ON `userPayment` (`creditCardId` ASC);

CREATE INDEX `fk_payments_parkingSpot1_idx` ON `userPayment` (`parkingSpotId` ASC);

CREATE INDEX `fk_payments_lister1_idx` ON `userPayment` (`listerId` ASC);


-- -----------------------------------------------------
-- Table `reservation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `reservation` ;

CREATE TABLE IF NOT EXISTS `reservation` (
  `id` INT NOT NULL,
  `reservedFrom` DATETIME NOT NULL,
  `reservedToo` DATETIME NOT NULL,
  `rate` DOUBLE NULL,
  `userId` INT NOT NULL,
  `parkingSpotId` INT NOT NULL,
  `creditCardId` INT NOT NULL,
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
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_reservation_parkingSpot1_idx` ON `reservation` (`parkingSpotId` ASC);

CREATE INDEX `fk_reservation_creditCard1_idx` ON `reservation` (`creditCardId` ASC);


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
