CREATE SCHEMA `dollarsbank`;
CREATE TABLE `dollarsbank`.`accounts` (
  `account_number` INT NOT NULL AUTO_INCREMENT,
  `id` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NULL,
  `address` VARCHAR(45) NULL,
  `phone` VARCHAR(45) NULL,
  `funds` DOUBLE NOT NULL,
  'savings' INT NULL,
  `history` MEDIUMTEXT NULL,
  PRIMARY KEY (`account_number`),
  UNIQUE INDEX `account_number_UNIQUE` (`account_number` ASC) VISIBLE);

CREATE TABLE `dollarsbank`.`savings` (
  `account_number` INT NOT NULL AUTO_INCREMENT,
  ` funds` DOUBLE NOT NULL,
  PRIMARY KEY (`account_number`),
  UNIQUE INDEX `account_number_UNIQUE` (`account_number` ASC) VISIBLE);

ALTER TABLE `dollarsbank`.`accounts`ADD FOREIGN KEY (savings) REFERENCES savings(account_number);