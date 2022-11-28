-- -----------------------------------------------------
-- Schema ubetterwatch_db
-- -----------------------------------------------------
DROP DATABASE IF EXISTS `ubetterwatch_db`;
CREATE DATABASE `ubetterwatch_db`;
USE `ubetterwatch_db` ;

DROP TABLE IF EXISTS `ubetterwatch_db`.`bracelet_storage`;
DROP TABLE IF EXISTS `ubetterwatch_db`.`voice_messages`;
DROP TABLE IF EXISTS `ubetterwatch_db`.`coordinates`;
DROP TABLE IF EXISTS `ubetterwatch_db`.`bracelet`;
DROP TABLE IF EXISTS `ubetterwatch_db`.`bracelet_data`;
DROP TABLE IF EXISTS `ubetterwatch_db`.`user`;
DROP TABLE IF EXISTS `ubetterwatch_db`.`supervisor`;

-- -----------------------------------------------------
-- Table `ubetterwatch_db`.`supervisor`
-- -----------------------------------------------------
CREATE TABLE `ubetterwatch_db`.`supervisor` (
  `username` VARCHAR(100) NOT NULL,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `role` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`username`)
);


-- -----------------------------------------------------
-- Table `ubetterwatch_db`.`user`
-- -----------------------------------------------------
CREATE TABLE `ubetterwatch_db`.`user` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `birth_date` DATE NOT NULL,
  `start_use_date` TIMESTAMP,
  `supervisor_username` VARCHAR(100),
  PRIMARY KEY (`id`),
  INDEX `fk_user_supervisor1_idx` (`supervisor_username` ASC) VISIBLE,
  CONSTRAINT `fk_user_supervisor1`
    FOREIGN KEY (`supervisor_username`)
    REFERENCES `ubetterwatch_db`.`supervisor` (`username`)
     ON DELETE SET NULL
     ON UPDATE CASCADE
);


-- -----------------------------------------------------
-- Table `ubetterwatch_db`.`bracelet_data`
-- -----------------------------------------------------
CREATE TABLE `ubetterwatch_db`.`bracelet_data` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `sim_name` VARCHAR(45),
  `apn` VARCHAR(70), 
  `username` VARCHAR(45),
  `password` VARCHAR(45),
  PRIMARY KEY (`id`)
);


-- -----------------------------------------------------
-- Table `ubetterwatch_db`.`bracelet`
-- -----------------------------------------------------
CREATE TABLE `ubetterwatch_db`.`bracelet` (
  `serial_number` VARCHAR(50) NOT NULL,
  `user_id` BIGINT,
  `bracelet_data_id` BIGINT NOT NULL,
  PRIMARY KEY (`serial_number`),
  INDEX `fk_bracelet_user1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_bracelet_bracelet_data1_idx` (`bracelet_data_id` ASC) VISIBLE,
  CONSTRAINT `fk_bracelet_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `ubetterwatch_db`.`user` (`id`)
     ON DELETE SET NULL
     ON UPDATE CASCADE,
  CONSTRAINT `fk_bracelet_bracelet_data1`
    FOREIGN KEY (`bracelet_data_id`)
    REFERENCES `ubetterwatch_db`.`bracelet_data` (`id`)
     ON DELETE CASCADE
     ON UPDATE CASCADE
);


-- -----------------------------------------------------
-- Table `ubetterwatch_db`.`bracelet_storage`
-- -----------------------------------------------------
CREATE TABLE `ubetterwatch_db`.`bracelet_storage` (
  `serial_number` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`serial_number`)
);


-- -----------------------------------------------------
-- Table `ubetterwatch_db`.`coordinates`
-- -----------------------------------------------------
CREATE TABLE `ubetterwatch_db`.`coordinates` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `x` VARCHAR(200) NOT NULL,
  `y` VARCHAR(200) NOT NULL,
  `time` TIMESTAMP NOT NULL,
  `bracelet_serial_number` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_coordinates_bracelet1_idx` (`bracelet_serial_number` ASC) VISIBLE,
  CONSTRAINT `fk_coordinates_bracelet1`
    FOREIGN KEY (`bracelet_serial_number`)
    REFERENCES `ubetterwatch_db`.`bracelet` (`serial_number`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);


-- -----------------------------------------------------
-- Table `ubetterwatch_db`.`voice_messages`
-- -----------------------------------------------------
CREATE TABLE `ubetterwatch_db`.`voice_messages` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `file_name` VARCHAR(120) NOT NULL,
  `time` TIMESTAMP NOT NULL,
  `bracelet_serial_number` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_voice_messages_bracelet1_idx` (`bracelet_serial_number` ASC) VISIBLE,
  CONSTRAINT `fk_voice_messages_bracelet1`
    FOREIGN KEY (`bracelet_serial_number`)
    REFERENCES `ubetterwatch_db`.`bracelet` (`serial_number`)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

insert into supervisor(username, first_name, last_name, password, role) values('admin', 'admin', 'admin', '$2a$10$xh2fvb9Kh8vr9yQ9JZ2.8.6H7oygVXrihrF/GKWWCpE21p2qc0r4i', 'ROLE_ADMIN'
);
insert into supervisor(username, first_name, last_name, password, role) values('s1', 's1', 's1', '$2a$10$HRTvJznxENS4ahwoKDP6d.B67AR9J.xu7gl87eJ8p/QNFoLoJ/UaC', 'ROLE_SUPERVISOR'
);
insert into supervisor(username, first_name, last_name, password, role) values('s2', 's2', 's2', '$2a$10$qfP0L7nKVqGKlZlAJhT3G.7i/95pio4fNDBDo09/zZoYHmsQe19y2', 'ROLE_SUPERVISOR'
);
insert into supervisor(username, first_name, last_name, password, role) values('s3', 's3', 's3', '$2a$10$qB81aAxZiuyMVm4ly3By9.vdhQnyJVMudMQCJOXJwg.5Arof9k76i', 'ROLE_SUPERVISOR'
);


insert into user(first_name, last_name, birth_date, start_use_date, supervisor_username) values("u1", "u1", "2020-01-01", "2020-01-01", "s1");
insert into user(first_name, last_name, birth_date, start_use_date, supervisor_username) values("u2", "u2", "2020-01-01", "2020-01-01", "s2");




insert into bracelet_data(sim_name, apn, username, password) values("sdfdsffewew", "fsdafdsafadsfds", "sdfdsfdsf", "sadfadsfdsf");
insert into bracelet_data(sim_name, apn, username, password) values("sdfdsffewew", "fsdafdsafadsfds", "sdfdsfdsf", "sadfadsfdsf");




insert into bracelet(serial_number, user_id, bracelet_data_id) values("xxx1", 1, 1);
insert into bracelet(serial_number, user_id, bracelet_data_id) values("xxx2", 2, 2);



