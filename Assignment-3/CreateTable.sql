-- DROP SCHEMA  IF EXISTS `cs5200`;

-- CREATE SCHEMA `cs5200`;

/*--- create tables ---*/
SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `person`;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE `person` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(255) NOT NULL,
    `last_name` VARCHAR(255) NOT NULL,
    `username` VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NOT NULL,
    `email` VARCHAR(255) NOT NULL,
    `dob` DATE NULL DEFAULT NULL,
    PRIMARY KEY (`id`)
);

SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `developer`;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE `developer` (
	`id` INT NOT NULL AUTO_INCREMENT,
    `developer_key` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `developer_person_generalization` FOREIGN KEY (`id`) REFERENCES `person` (`id`)
	ON DELETE CASCADE ON UPDATE CASCADE
);

SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `user`;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE `user` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `user_agreement` TINYINT NOT NULL DEFAULT 1,
    PRIMARY KEY (`id`),
    CONSTRAINT `user_person_generalization` FOREIGN KEY (`id`) REFERENCES `person` (`id`)
	ON DELETE CASCADE ON UPDATE CASCADE
);
SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `phone`;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE `phone` (
	-- `id` INT NOT NULL AUTO_INCREMENT,
    `phone` VARCHAR(255) NULL,
    `primary` TINYINT(1) NULL,
    `person_id` INT NULL,
    CONSTRAINT `phone_person_composition` FOREIGN KEY (`person_id`) REFERENCES `person` (`id`)
	ON DELETE CASCADE ON UPDATE CASCADE
    );
-- insert into `phone`(`phone`,`primary`,`person_id`) values (0,1,34);

SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `address`;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE `address` (
    -- `id` INT NOT NULL AUTO_INCREMENT,
    `street1` VARCHAR(255) NOT NULL,
    `street2` VARCHAR(255) NULL,
    `city` VARCHAR(255) NOT NULL,
    `state` VARCHAR(255) NULL DEFAULT NULL,
    `zip` VARCHAR(255) NOT NULL,
    `primary` TINYINT NULL,
    `person_id` INT NULL
    -- PRIMARY KEY (`id`)
);

SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `website`;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE `website` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    `description` VARCHAR(255) NULL,
    `created` DATE NULL,
    `updated` DATE NULL,
    `visits` INT(11) NULL,
    `developer_id` INT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `wesite_developer_aggregation` FOREIGN KEY(`developer_id`) REFERENCES `developer`(`id`)
	ON DELETE CASCADE ON UPDATE CASCADE
);

SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `page`;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE `page` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `title` VARCHAR(255) NULL,
    `description` VARCHAR(255) NULL,
    `created` DATE NULL,
    `updated` DATE NULL,
    `views` INT NULL,
    `website_id` INT NULL,
    PRIMARY KEY (`id`),
     CONSTRAINT `page_website_composition` FOREIGN KEY (`website_id`) REFERENCES `website` (`id`)
	ON DELETE CASCADE ON UPDATE CASCADE
);

SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `dtype`;
SET FOREIGN_KEY_CHECKS = 1;

-- CREATE TABLE `dtype` (
--     `name` VARCHAR(255) NOT NULL,
--     PRIMARY KEY (`name`)
-- );

-- INSERT INTO `dtype` (`name`) VALUES ('youtube'), ('image'), ('heading'), ('html');

SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `widget`;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE `widget` (    
    `id` INT NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(255) NOT NULL,
    `width` INT NULL,
    `height` INT NULL,
    `css_class` VARCHAR(255) NULL,
    `css_style` VARCHAR(255) NULL,
    `text` VARCHAR(255) NULL,
    `order` INT NOT NULL,
    `page_id` INT NULL,
    -- `dtype` VARCHAR(255) NOT NULL,
-- 	`size` INT NULL DEFAULT 2,
    PRIMARY KEY (`id`),
   --  FOREIGN KEY (`dtype`) REFERENCES `dtype` (`name`)
-- 	ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `widget_page_composition` FOREIGN KEY (`page_id`) REFERENCES `page` (`id`)
	ON DELETE CASCADE ON UPDATE CASCADE
);

SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `priviledge`;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE `priviledge` (
    `name` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`name`)
);

INSERT INTO `priviledge` (`name`) VALUES ('create'), ('read'), ('update'), ('delete');
	
SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `role`;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE `role` (
    `name` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`name`)
);

INSERT INTO `role` (`name`) VALUES ('owner'), ('admin'), ('writer'), ('editor'), ('reviewer');

DROP TABLE IF EXISTS `website_role`;

CREATE TABLE `website_role` (
    `role` VARCHAR(255) NULL,
    `developer_id` INT NULL,
    `website_id` INT NULL,
    CONSTRAINT `website_role_portableenum` FOREIGN KEY (`role`) REFERENCES `role` (`name`)
	ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `websiterole_developer_association` FOREIGN KEY (`developer_id`) REFERENCES `developer` (`id`)
	ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `websiterole_website_association` FOREIGN KEY (`website_id`) REFERENCES `website` (`id`)
	ON DELETE CASCADE ON UPDATE CASCADE
);

SET FOREIGN_KEY_CHECKS = 0;
DROP TABLE IF EXISTS `page_role`;
SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE `page_role` (
    `role` VARCHAR(255) NULL,
    `developer_id` INT NULL,
    `page_id` INT NULL,
    CONSTRAINT `page_role_portableenum` FOREIGN KEY (`role`) REFERENCES `role` (`name`)
	ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `pagerole_developer_association` FOREIGN KEY (`developer_id`) REFERENCES `developer` (`id`)
	ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `pagerole_page_association` FOREIGN KEY (`page_id`) REFERENCES `page` (`id`)
	ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS `website_priviledge`;

CREATE TABLE `website_priviledge` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `priviledge` VARCHAR(255) NULL,
    `developer_id` INT NULL,
    `website_id` INT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `website_priviledge_portableenum` FOREIGN KEY (`priviledge`) REFERENCES `priviledge` (`name`)
	ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `websitepriviledge_developer_association` FOREIGN KEY (`developer_id`) REFERENCES `developer` (`id`)
	ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `websitepriviledge_website_association` FOREIGN KEY (`website_id`) REFERENCES `website` (`id`)
	ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS `page_priviledge`;

CREATE TABLE `page_priviledge` (
    `id` INT NOT NULL AUTO_INCREMENT,
    `priviledge` VARCHAR(255) NULL,
    `developer_id` INT NULL,
    `page_id` INT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `page_priviledge_portableenum` FOREIGN KEY (`priviledge`) REFERENCES `priviledge` (`name`)
	ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `pagepriviledge_developer_association` FOREIGN KEY (`developer_id`) REFERENCES `developer` (`id`)
	ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `pagepriviledge_page_association` FOREIGN KEY (`page_id`) REFERENCES `page` (`id`)
	ON DELETE CASCADE ON UPDATE CASCADE
);

-- SELECT * FROM `person`;
-- SELECT * FROM `developer`;
-- SELECT * FROM `user`;
-- SELECT * FROM `website`;