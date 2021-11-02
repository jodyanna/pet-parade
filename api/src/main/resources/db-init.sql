
-- Create PetParade schema
CREATE SCHEMA `PetParade` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;

-- Create User table 
CREATE TABLE `PetParade`.`Users` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(65) NOT NULL,
  `city` VARCHAR(45) NULL,
  `state` VARCHAR(45) NULL,
  `dateCreated` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dateModified` DATETIME NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE);
  
-- Create Roles table
CREATE TABLE `PetParade`.`Roles` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `role` VARCHAR(45) NULL,
  `FK_Roles_Users` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_Roles_Users_idx` (`FK_Roles_Users` ASC) VISIBLE,
  CONSTRAINT `FK_Roles_Users`
    FOREIGN KEY (`FK_Roles_Users`)
    REFERENCES `PetParade`.`Users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
  
-- Create Species table
CREATE TABLE `PetParade`.`Species` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE);

-- Create Pets table
CREATE TABLE `PetParade`.`Pets` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `bio` VARCHAR(255) NULL,
  `birthday` DATETIME NULL,
  `isFlagged` TINYINT NULL,
  `dateCreated` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `dateModified` DATETIME NULL,
  `image` NVARCHAR(260) NULL,
  `FK_Pets_Users` INT UNSIGNED NOT NULL,
  `FK_Pets_Species` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_Pets_Users_idx` (`FK_Pets_Users` ASC) VISIBLE,
  INDEX `FK_Pets_Species_idx` (`FK_Pets_Species` ASC) VISIBLE,
  CONSTRAINT `FK_Pets_Users`
    FOREIGN KEY (`FK_Pets_Users`)
    REFERENCES `PetParade`.`Users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_Pets_Species`
    FOREIGN KEY (`FK_Pets_Species`)
    REFERENCES `PetParade`.`Species` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
-- Create Likes table
CREATE TABLE `PetParade`.`Likes` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `dateCreated` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `FK_Likes_Pets` INT UNSIGNED NOT NULL,
  `FK_Likes_Users` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_Likes_Pets_idx` (`FK_Likes_Pets` ASC) VISIBLE,
  INDEX `FK_Likes_Users_idx` (`FK_Likes_Users` ASC) VISIBLE,
  CONSTRAINT `FK_Likes_Pets`
    FOREIGN KEY (`FK_Likes_Pets`)
    REFERENCES `PetParade`.`Pets` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_Likes_Users`
    FOREIGN KEY (`FK_Likes_Users`)
    REFERENCES `PetParade`.`Users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
-- Create Ratings table
CREATE TABLE `PetParade`.`Ratings` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `rating` INT NOT NULL,
  `dateCreated` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `FK_Ratings_Users` INT UNSIGNED NOT NULL,
  `FK_Ratings_Pets` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `FK_Ratings_Users_idx` (`FK_Ratings_Users` ASC) VISIBLE,
  INDEX `FK_Ratings_Pets_idx` (`FK_Ratings_Pets` ASC) VISIBLE,
  CONSTRAINT `FK_Ratings_Users`
    FOREIGN KEY (`FK_Ratings_Users`)
    REFERENCES `PetParade`.`Users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_Ratings_Pets`
    FOREIGN KEY (`FK_Ratings_Pets`)
    REFERENCES `PetParade`.`Pets` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
