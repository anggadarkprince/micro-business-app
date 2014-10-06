SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema microbusinessapp
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `microbusinessapp` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `microbusinessapp` ;

-- -----------------------------------------------------
-- Table `microbusinessapp`.`ap_level`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `microbusinessapp`.`ap_level` (
  `lvl_id` VARCHAR(3) NOT NULL,
  `lvl_level` VARCHAR(45) NOT NULL,
  `lvl_created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `lvl_updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`lvl_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `microbusinessapp`.`ap_user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `microbusinessapp`.`ap_user` (
  `usr_id` VARCHAR(45) NOT NULL,
  `usr_password` VARCHAR(100) NOT NULL,
  `usr_name` VARCHAR(45) NOT NULL,
  `usr_level` VARCHAR(3) NOT NULL,
  `usr_avatar` VARCHAR(100) NOT NULL,
  `usr_created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `usr_updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`usr_id`, `usr_level`),
  INDEX `fk_ap_user_ap_level_idx` (`usr_level` ASC),
  CONSTRAINT `fk_ap_user_ap_level`
    FOREIGN KEY (`usr_level`)
    REFERENCES `microbusinessapp`.`ap_level` (`lvl_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `microbusinessapp`.`ps_customer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `microbusinessapp`.`ps_customer` (
  `cus_id` INT NOT NULL AUTO_INCREMENT,
  `cus_name` VARCHAR(45) NOT NULL,
  `cus_address` VARCHAR(100) NOT NULL,
  `cus_contact` VARCHAR(50) NOT NULL,
  `cus_pid` VARCHAR(50) NOT NULL,
  `cus_mother` VARCHAR(45) NOT NULL,
  `cus_created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `cus_updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`cus_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `microbusinessapp`.`ps_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `microbusinessapp`.`ps_type` (
  `typ_id` INT NOT NULL AUTO_INCREMENT,
  `typ_name` VARCHAR(45) NOT NULL,
  `typ_created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `typ_updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`typ_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `microbusinessapp`.`ps_motor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `microbusinessapp`.`ps_motor` (
  `mtr_id` INT NOT NULL,
  `mtr_type` INT NOT NULL,
  `mtr_name` VARCHAR(45) NOT NULL,
  `mtr_stnk` VARCHAR(100) NOT NULL,
  `mtr_color` VARCHAR(45) NOT NULL,
  `mtr_year` YEAR NOT NULL,
  `mtr_chasis` VARCHAR(100) NOT NULL,
  `mtr_purchase_price` DOUBLE NOT NULL,
  `mtr_resale_price` VARCHAR(45) NOT NULL,
  `mtr_created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `mtr_updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`mtr_id`, `mtr_type`),
  INDEX `fk_ps_item_ps_type1_idx` (`mtr_type` ASC),
  CONSTRAINT `fk_ps_item_ps_type1`
    FOREIGN KEY (`mtr_type`)
    REFERENCES `microbusinessapp`.`ps_type` (`typ_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `microbusinessapp`.`ps_transaction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `microbusinessapp`.`ps_transaction` (
  `trn_id` VARCHAR(50) NOT NULL,
  `trn_customer` INT NOT NULL,
  `trn_motor` INT NOT NULL,
  `trn_acc` TINYINT(1) NOT NULL,
  `trn_instalment` ENUM('ADIRA','CS') NOT NULL,
  `trn_user` VARCHAR(45) NOT NULL,
  `trn_created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `trn_updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`trn_id`, `trn_customer`, `trn_motor`, `trn_user`),
  INDEX `fk_ps_transaction_ps_item1_idx` (`trn_motor` ASC),
  INDEX `fk_ps_transaction_ps_customer1_idx` (`trn_customer` ASC),
  INDEX `fk_ps_transaction_ap_user1_idx` (`trn_user` ASC),
  CONSTRAINT `fk_ps_transaction_ps_item1`
    FOREIGN KEY (`trn_motor`)
    REFERENCES `microbusinessapp`.`ps_motor` (`mtr_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ps_transaction_ps_customer1`
    FOREIGN KEY (`trn_customer`)
    REFERENCES `microbusinessapp`.`ps_customer` (`cus_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ps_transaction_ap_user1`
    FOREIGN KEY (`trn_user`)
    REFERENCES `microbusinessapp`.`ap_user` (`usr_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `microbusinessapp`.`ap_application`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `microbusinessapp`.`ap_application` (
  `app_id` VARCHAR(5) NOT NULL,
  `app_codename` VARCHAR(45) NOT NULL,
  `app_name` VARCHAR(45) NOT NULL,
  `app_version` VARCHAR(45) NOT NULL,
  `app_created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `app_updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`app_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `microbusinessapp`.`ap_setting`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `microbusinessapp`.`ap_setting` (
  `stg_id` INT NOT NULL AUTO_INCREMENT,
  `stg_application` VARCHAR(5) NOT NULL,
  `stg_setting` VARCHAR(100) NOT NULL,
  `stg_value` TEXT NOT NULL,
  `stg_created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `stg_updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`stg_id`, `stg_application`),
  INDEX `fk_ap_setting_ap_application1_idx` (`stg_application` ASC),
  CONSTRAINT `fk_ap_setting_ap_application1`
    FOREIGN KEY (`stg_application`)
    REFERENCES `microbusinessapp`.`ap_application` (`app_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `microbusinessapp`.`ap_application_level`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `microbusinessapp`.`ap_application_level` (
  `apl_id` INT NOT NULL AUTO_INCREMENT,
  `apl_level` VARCHAR(3) NOT NULL,
  `apl_application` VARCHAR(5) NOT NULL,
  `apl_created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  `apl_updated_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`apl_id`, `apl_application`, `apl_level`),
  INDEX `fk_ap_application_level_ap_application1_idx` (`apl_application` ASC),
  INDEX `fk_ap_application_level_ap_level1_idx` (`apl_level` ASC),
  CONSTRAINT `fk_ap_application_level_ap_application1`
    FOREIGN KEY (`apl_application`)
    REFERENCES `microbusinessapp`.`ap_application` (`app_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ap_application_level_ap_level1`
    FOREIGN KEY (`apl_level`)
    REFERENCES `microbusinessapp`.`ap_level` (`lvl_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `microbusinessapp`.`cr_creditor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `microbusinessapp`.`cr_creditor` (
  `cdr_id` INT NOT NULL AUTO_INCREMENT,
  `cdr_name` VARCHAR(45) NOT NULL,
  `cdr_address` VARCHAR(100) NOT NULL,
  `crd_contact` VARCHAR(50) NOT NULL,
  `crd_pid` VARCHAR(50) NOT NULL,
  `crd_mother` VARCHAR(45) NOT NULL,
  `crd_created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `crd_updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`cdr_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `microbusinessapp`.`cr_loan`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `microbusinessapp`.`cr_loan` (
  `lon_id` INT NOT NULL AUTO_INCREMENT,
  `lon_loan` DOUBLE NOT NULL,
  `lon_total_month` INT NOT NULL,
  `lon_instalment_value` DOUBLE NOT NULL,
  `lon_created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `lon_updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`lon_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `microbusinessapp`.`cr_loan_creditor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `microbusinessapp`.`cr_loan_creditor` (
  `lcr_id` INT NOT NULL AUTO_INCREMENT,
  `lcr_creditor` INT NOT NULL,
  `lcr_loan` INT NOT NULL,
  `lcr_status` ENUM('PAID','DEBT') NOT NULL DEFAULT 'DEBT',
  `lcr_user` VARCHAR(45) NOT NULL,
  `lcr_created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `lcr_updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`lcr_id`, `lcr_user`, `lcr_creditor`, `lcr_loan`),
  INDEX `fk_cr_loan_creditor_cr_creditor1_idx` (`lcr_creditor` ASC),
  INDEX `fk_cr_loan_creditor_cr_loan1_idx` (`lcr_loan` ASC),
  INDEX `fk_cr_loan_creditor_ap_user1_idx` (`lcr_user` ASC),
  CONSTRAINT `fk_cr_loan_creditor_cr_creditor1`
    FOREIGN KEY (`lcr_creditor`)
    REFERENCES `microbusinessapp`.`cr_creditor` (`cdr_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cr_loan_creditor_cr_loan1`
    FOREIGN KEY (`lcr_loan`)
    REFERENCES `microbusinessapp`.`cr_loan` (`lon_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cr_loan_creditor_ap_user1`
    FOREIGN KEY (`lcr_user`)
    REFERENCES `microbusinessapp`.`ap_user` (`usr_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `microbusinessapp`.`cr_instalment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `microbusinessapp`.`cr_instalment` (
  `ins_id` INT NOT NULL,
  `ins_creditor` INT NOT NULL,
  `ins_loan_creditor` INT NOT NULL,
  `ins_overdue` INT NOT NULL DEFAULT 0,
  `ins_penalty` DOUBLE NOT NULL DEFAULT 0,
  `ins_payment` DOUBLE NOT NULL,
  `ins_user` VARCHAR(45) NOT NULL,
  `ins_created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ins_updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`ins_id`, `ins_user`, `ins_loan_creditor`, `ins_creditor`),
  INDEX `fk_cr_instalment_cr_creditor1_idx` (`ins_creditor` ASC),
  INDEX `fk_cr_instalment_cr_loan_creditor1_idx` (`ins_loan_creditor` ASC),
  INDEX `fk_cr_instalment_ap_user1_idx` (`ins_user` ASC),
  CONSTRAINT `fk_cr_instalment_cr_creditor1`
    FOREIGN KEY (`ins_creditor`)
    REFERENCES `microbusinessapp`.`cr_creditor` (`cdr_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cr_instalment_cr_loan_creditor1`
    FOREIGN KEY (`ins_loan_creditor`)
    REFERENCES `microbusinessapp`.`cr_loan_creditor` (`lcr_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cr_instalment_ap_user1`
    FOREIGN KEY (`ins_user`)
    REFERENCES `microbusinessapp`.`ap_user` (`usr_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `microbusinessapp`.`ap_level`
-- -----------------------------------------------------
START TRANSACTION;
USE `microbusinessapp`;
INSERT INTO `microbusinessapp`.`ap_level` (`lvl_id`, `lvl_level`, `lvl_created_at`, `lvl_updated_at`) VALUES ('DEV', 'Developer', NULL, NULL);
INSERT INTO `microbusinessapp`.`ap_level` (`lvl_id`, `lvl_level`, `lvl_created_at`, `lvl_updated_at`) VALUES ('CEO', 'Owner', NULL, NULL);
INSERT INTO `microbusinessapp`.`ap_level` (`lvl_id`, `lvl_level`, `lvl_created_at`, `lvl_updated_at`) VALUES ('MGR', 'Manager', NULL, NULL);
INSERT INTO `microbusinessapp`.`ap_level` (`lvl_id`, `lvl_level`, `lvl_created_at`, `lvl_updated_at`) VALUES ('ADM', 'Administrator', NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `microbusinessapp`.`ap_user`
-- -----------------------------------------------------
START TRANSACTION;
USE `microbusinessapp`;
INSERT INTO `microbusinessapp`.`ap_user` (`usr_id`, `usr_password`, `usr_name`, `usr_level`, `usr_avatar`, `usr_created_at`, `usr_updated_at`) VALUES ('dev-su', '$2a$10$R1uD4BBxQWJ4sN7luZGYIeFZ3kF9vF70tCxms4MhZqLkIo36EJLFW', 'Angga Ari Wijaya', 'DEV', 'avatar.png', NULL, NULL);
INSERT INTO `microbusinessapp`.`ap_user` (`usr_id`, `usr_password`, `usr_name`, `usr_level`, `usr_avatar`, `usr_created_at`, `usr_updated_at`) VALUES ('admin', '$2a$10$R1uD4BBxQWJ4sN7luZGYIeFZ3kF9vF70tCxms4MhZqLkIo36EJLFW', 'Angga Ari Wijaya', 'CEO', 'avatar.png', NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `microbusinessapp`.`ps_customer`
-- -----------------------------------------------------
START TRANSACTION;
USE `microbusinessapp`;
INSERT INTO `microbusinessapp`.`ps_customer` (`cus_id`, `cus_name`, `cus_address`, `cus_contact`, `cus_pid`, `cus_mother`, `cus_created_at`, `cus_updated_at`) VALUES (NULL, 'Rio Dharmawan', 'Lingerine 34', '085644793563', '10241010101045', 'Diana', NULL, NULL);
INSERT INTO `microbusinessapp`.`ps_customer` (`cus_id`, `cus_name`, `cus_address`, `cus_contact`, `cus_pid`, `cus_mother`, `cus_created_at`, `cus_updated_at`) VALUES (NULL, 'Dhika Raharga', 'Krista 97', '085733495845', '102410101074', 'Riani', NULL, NULL);
INSERT INTO `microbusinessapp`.`ps_customer` (`cus_id`, `cus_name`, `cus_address`, `cus_contact`, `cus_pid`, `cus_mother`, `cus_created_at`, `cus_updated_at`) VALUES (NULL, 'Mika Dwiyanti', 'Wallstreet 97', '081666754324', '102410101034', 'Diah', NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `microbusinessapp`.`ps_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `microbusinessapp`;
INSERT INTO `microbusinessapp`.`ps_type` (`typ_id`, `typ_name`, `typ_created_at`, `typ_updated_at`) VALUES (NULL, 'Honda', NULL, NULL);
INSERT INTO `microbusinessapp`.`ps_type` (`typ_id`, `typ_name`, `typ_created_at`, `typ_updated_at`) VALUES (NULL, 'Yamaha', NULL, NULL);
INSERT INTO `microbusinessapp`.`ps_type` (`typ_id`, `typ_name`, `typ_created_at`, `typ_updated_at`) VALUES (NULL, 'Suzuki', NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `microbusinessapp`.`ap_application`
-- -----------------------------------------------------
START TRANSACTION;
USE `microbusinessapp`;
INSERT INTO `microbusinessapp`.`ap_application` (`app_id`, `app_codename`, `app_name`, `app_version`, `app_created_at`, `app_updated_at`) VALUES ('APPPS', 'Ethan', 'Micro Business App : Point of Sale', '0.1', NULL, NULL);
INSERT INTO `microbusinessapp`.`ap_application` (`app_id`, `app_codename`, `app_name`, `app_version`, `app_created_at`, `app_updated_at`) VALUES ('APPCR', 'Livia', 'Micro Business App : Credits', '0.1', NULL, NULL);

COMMIT;


-- -----------------------------------------------------
-- Data for table `microbusinessapp`.`ap_application_level`
-- -----------------------------------------------------
START TRANSACTION;
USE `microbusinessapp`;
INSERT INTO `microbusinessapp`.`ap_application_level` (`apl_id`, `apl_level`, `apl_application`, `apl_created_at`, `apl_updated_at`) VALUES (NULL, 'DEV', 'APPPS', NULL, NULL);
INSERT INTO `microbusinessapp`.`ap_application_level` (`apl_id`, `apl_level`, `apl_application`, `apl_created_at`, `apl_updated_at`) VALUES (NULL, 'DEV', 'APPCR', NULL, NULL);
INSERT INTO `microbusinessapp`.`ap_application_level` (`apl_id`, `apl_level`, `apl_application`, `apl_created_at`, `apl_updated_at`) VALUES (NULL, 'CEO', 'APPPS', NULL, NULL);
INSERT INTO `microbusinessapp`.`ap_application_level` (`apl_id`, `apl_level`, `apl_application`, `apl_created_at`, `apl_updated_at`) VALUES (NULL, 'CEO', 'APPCR', NULL, NULL);
INSERT INTO `microbusinessapp`.`ap_application_level` (`apl_id`, `apl_level`, `apl_application`, `apl_created_at`, `apl_updated_at`) VALUES (NULL, 'MGR', 'APPCR', NULL, NULL);
INSERT INTO `microbusinessapp`.`ap_application_level` (`apl_id`, `apl_level`, `apl_application`, `apl_created_at`, `apl_updated_at`) VALUES (NULL, 'ADM', 'APPPS', NULL, NULL);

COMMIT;

