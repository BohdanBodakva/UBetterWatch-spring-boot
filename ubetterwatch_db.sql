-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema ubetterwatch_db
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ubetterwatch_db
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `ubetterwatch_db`;
CREATE SCHEMA `ubetterwatch_db` DEFAULT CHARACTER SET utf8 ;
USE `ubetterwatch_db` ;


-- DROP TABLE IF EXISTS `ubetterwatch_db`.`supervisor`;
-- DROP TABLE IF EXISTS `ubetterwatch_db`.`user`;
-- DROP TABLE IF EXISTS `ubetterwatch_db`.`bracelet`;
-- DROP TABLE IF EXISTS `ubetterwatch_db`.`coordinates`;
-- DROP TABLE IF EXISTS `ubetterwatch_db`.`bracelet_data`;
-- DROP TABLE IF EXISTS `ubetterwatch_db`.`voice_messages`;
-- DROP TABLE IF EXISTS `ubetterwatch_db`.`bracelet_storage`;


-- -----------------------------------------------------
-- Table `ubetterwatch_db`.`supervisor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ubetterwatch_db`.`supervisor` (
  `username` VARCHAR(100) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `role` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`username`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `ubetterwatch_db`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ubetterwatch_db`.`user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `birth_date` DATE NOT NULL,
  `start_use_date` TIMESTAMP NOT NULL,
  `supervisor_username` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_supervisor1_idx` (`supervisor_username` ASC) VISIBLE,
  CONSTRAINT `fk_user_supervisor1`
    FOREIGN KEY (`supervisor_username`)
    REFERENCES `ubetterwatch_db`.`supervisor` (`username`))
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `ubetterwatch_db`.`bracelet_data`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ubetterwatch_db`.`bracelet_data` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `sim_name` VARCHAR(45) NOT NULL,
  `apn` VARCHAR(70) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `ubetterwatch_db`.`bracelet`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ubetterwatch_db`.`bracelet` (
  `serial_number` VARCHAR(50) NOT NULL,
  `user_id` BIGINT NOT NULL,
  `bracelet_data_id` BIGINT NOT NULL,
  PRIMARY KEY (`serial_number`),
  INDEX `fk_bracelet_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_bracelet_bracelet_data1_idx` (`bracelet_data_id` ASC) VISIBLE,
  CONSTRAINT `fk_bracelet_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `ubetterwatch_db`.`user` (`id`),
  CONSTRAINT `fk_bracelet_bracelet_data1`
    FOREIGN KEY (`bracelet_data_id`)
    REFERENCES `ubetterwatch_db`.`bracelet_data` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `ubetterwatch_db`.`bracelet_storage`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ubetterwatch_db`.`bracelet_storage` (
  `serial_number` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`serial_number`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `ubetterwatch_db`.`coordinates`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ubetterwatch_db`.`coordinates` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `x` VARCHAR(200) NOT NULL,
  `y` VARCHAR(200) NOT NULL,
  `time` TIMESTAMP NOT NULL,
  `bracelet_serial_number` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_coordinates_bracelet1_idx` (`bracelet_serial_number` ASC) VISIBLE,
  CONSTRAINT `fk_coordinates_bracelet1`
    FOREIGN KEY (`bracelet_serial_number`)
    REFERENCES `ubetterwatch_db`.`bracelet` (`serial_number`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `ubetterwatch_db`.`voice_messages`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ubetterwatch_db`.`voice_messages` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `file_name` VARCHAR(120) NOT NULL,
  `time` TIMESTAMP NOT NULL,
  `bracelet_serial_number` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_voice_messages_bracelet1_idx` (`bracelet_serial_number` ASC) VISIBLE,
  CONSTRAINT `fk_voice_messages_bracelet1`
    FOREIGN KEY (`bracelet_serial_number`)
    REFERENCES `ubetterwatch_db`.`bracelet` (`serial_number`))
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
