USE a2;
/*--------------------website priviledge insert--------------------*/
-- DROP TRIGGER IF EXISTS `website_role_insert`;
DELIMITER $$
CREATE DEFINER = CURRENT_USER TRIGGER `website_role_insert` AFTER INSERT ON `website_role` FOR EACH ROW
BEGIN
IF NEW.role = 'owner' OR NEW.role = 'admin' THEN
INSERT INTO `website_priviledge` (`priviledge`, `developer_id`, `website_id`) 
    VALUES ('create', NEW.`developer_id`, NEW.`website_id`),
		   ('read', NEW.`developer_id`, NEW.`website_id`),
           ('update', NEW.`developer_id`, NEW.`website_id`),
           ('delete', NEW.`developer_id`, NEW.`website_id`);
ELSEIF NEW.role = 'writer' THEN
    INSERT INTO `website_priviledge` (`priviledge`, `developer_id`, `website_id`) 
    VALUES ('create', NEW.`developer_id`, NEW.`website_id`),
           ('read', NEW.`developer_id`, NEW.`website_id`),
           ('update', NEW.`developer_id`, NEW.`website_id`);
ELSEIF NEW.role = 'editor' THEN
    INSERT INTO `website_priviledge` (`priviledge`, `developer_id`, `website_id`) 
    VALUES ('update', NEW.`developer_id`, NEW.`website_id`),
           ('read', NEW.`developer_id`, NEW.`website_id`);
ELSEIF NEW.role = 'reviewer' THEN
    INSERT INTO `website_priviledge` (`priviledge`, `developer_id`, `website_id`) 
    VALUES ('read', NEW.`developer_id`, NEW.`website_id`);
END IF;
END $$
DELIMITER;

/*-------------------- website priviledge update --------------------*/
-- DROP TRIGGER IF EXISTS `website_role_update`;
DELIMITER $$
CREATE DEFINER = CURRENT_USER TRIGGER `website_role_update` AFTER UPDATE ON `website_role` FOR EACH ROW
BEGIN
DELETE FROM `website_priviledge` WHERE website_id = OLD.website_id AND developer_id = OLD.developer_id;
IF NEW.role = 'owner' OR NEW.role = 'admin' THEN
    INSERT INTO `website_priviledge` (`priviledge`, `developer_id`, `website_id`) 
    VALUES ('create', NEW.`developer_id`, NEW.`website_id`),
           ('read', NEW.`developer_id`, NEW.`website_id`),
           ('update', NEW.`developer_id`, NEW.`website_id`),
           ('delete', NEW.`developer_id`, NEW.`website_id`);
ELSEIF NEW.role = 'writer' THEN
    INSERT INTO `website_priviledge` (`priviledge`, `developer_id`, `website_id`) 
    VALUES ('create', NEW.`developer_id`, NEW.`website_id`),
           ('read', NEW.`developer_id`, NEW.`website_id`),
		   ('update', NEW.`developer_id`, NEW.`website_id`);
ELSEIF NEW.role = 'editor' THEN
    INSERT INTO `website_priviledge` (`priviledge`, `developer_id`, `website_id`)
    VALUES ('update', NEW.`developer_id`, NEW.`website_id`),
           ('read', NEW.`developer_id`, NEW.`website_id`);
ELSEIF NEW.role = 'reviewer' THEN
    INSERT INTO `website_priviledge` (`priviledge`, `developer_id`, `website_id`) 
    VALUES ('read', NEW.`developer_id`, NEW.`website_id`);
END IF;
END $$
DELIMITER;

/*-------------------- website priviledge delete --------------------*/
-- DROP TRIGGER IF EXISTS `website_role_delete`;

DELIMITER $$
CREATE DEFINER = CURRENT_USER TRIGGER `website_role_delete` AFTER DELETE ON `website_role` FOR EACH ROW
BEGIN
DELETE FROM `website_priviledge` WHERE `website_id` = OLD.`website_id` AND `developer_id` = OLD.`developer_id`;
END $$
DELIMITER;

/*--------------------page priviledge insert--------------------*/
-- DROP TRIGGER IF EXISTS `page_role_insert`;
DELIMITER $$
CREATE DEFINER = CURRENT_USER TRIGGER `page_role_insert` AFTER INSERT ON `page_role` FOR EACH ROW
BEGIN
IF NEW.role = 'owner' OR NEW.role = 'admin' THEN
    INSERT INTO `page_priviledge` (`priviledge`, `developer_id`, `page_id`) 
	VALUES ('create', NEW.`developer_id`, NEW.`page_id`),
	('read', NEW.`developer_id`, NEW.`page_id`),
    ('update', NEW.`developer_id`, NEW.`page_id`),
    ('delete', NEW.`developer_id`, NEW.`page_id`);
 ELSEIF NEW.role = 'writer' THEN
    INSERT INTO `page_priviledge` (`priviledge`, `developer_id`, `page_id`) 
    VALUES ('create', NEW.`developer_id`, NEW.`page_id`),
    ('read', NEW.`developer_id`, NEW.`page_id`),
    ('update', NEW.`developer_id`, NEW.`page_id`);
ELSEIF NEW.role = 'editor' THEN
    INSERT INTO `page_priviledge` (`priviledge`, `developer_id`, `page_id`) 
    VALUES ('update', NEW.`developer_id`, NEW.`page_id`),
    ('read', NEW.`developer_id`, NEW.`page_id`);
ELSEIF NEW.role = 'reviewer' THEN
    INSERT INTO `page_priviledge` (`priviledge`, `developer_id`, `page_id`) 
    VALUES ('read', NEW.`developer_id`, NEW.`page_id`);
END IF;
END $$
DELIMITER;

/*--------------------page priviledge update--------------------*/
-- DROP TRIGGER IF EXISTS `page_role_update`;
DELIMITER $$
CREATE DEFINER = CURRENT_USER TRIGGER `page_role_update` AFTER UPDATE ON `page_role` FOR EACH ROW
BEGIN
DELETE FROM `page_priviledge` WHERE page_id = OLD.page_id AND developer_id = OLD.developer_id;
IF NEW.role = 'owner' OR NEW.role = 'admin' THEN
    INSERT INTO `page_priviledge` (`priviledge`, `developer_id`, `page_id`) 
    VALUES ('create', NEW.`developer_id`, NEW.`page_id`),
    ('read', NEW.`developer_id`, NEW.`page_id`),
    ('update', NEW.`developer_id`, NEW.`page_id`),
    ('delete', NEW.`developer_id`, NEW.`page_id`);
ELSEIF NEW.role = 'writer' THEN
    INSERT INTO `page_priviledge` (`priviledge`, `developer_id`, `page_id`) 
    VALUES ('create', NEW.`developer_id`, NEW.`page_id`),
    ('read', NEW.`developer_id`, NEW.`page_id`),
    ('update', NEW.`developer_id`, NEW.`page_id`);
ELSEIF NEW.role = 'editor' THEN
    INSERT INTO `page_priviledge` (priviledge, `developer_id`, `page_id`)
    VALUES ('update', NEW.`developer_id`, NEW.`page_id`),
    ('read', NEW.`developer_id`, NEW.`page_id`);
ELSEIF NEW.role = 'reviewer' THEN
    INSERT INTO `page_priviledge` (priviledge, `developer_id`, `page_id`) 
    VALUES ('read', NEW.`developer_id`, NEW.`page_id`);
END IF;
END $$
DELIMITER;

/*--------------------page priviledge update--------------------*/
-- DROP TRIGGER IF EXISTS `page_role_delete`;

DELIMITER $$
CREATE DEFINER = CURRENT_USER TRIGGER `page_role_delete` AFTER DELETE ON `page_role` FOR EACH ROW
BEGIN
DELETE FROM `page_priviledge` WHERE page_id = OLD.page_id AND developer_id = OLD.developer_id;
END;



