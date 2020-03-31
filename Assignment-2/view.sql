USE a2;
SET FOREIGN_KEY_CHECKS=0;
DROP VIEW IF EXISTS `a2`.`developer_roles_and_priviledges`;
SET FOREIGN_KEY_CHECKS=1;

CREATE VIEW `developer_roles_and_priviledges` AS

SELECT p.`first_name`, p.`last_name`, p.`username`, p.`email`, w.`name` AS `web_info`, 
w.`visits` AS `web_visits`, 
w.`updated` AS `web_updated`,
wr.`role` AS `web_role`,
wp.`priviledge` AS `web_priviledge`,
pg.`title` AS `page_title`, 
pg.`views` AS `page_views`, 
pg.`updated` AS `page_updated`,
pgr.`role` AS `page_role`,
pgp.`priviledge` AS `page_priviledge`

FROM `person` p

JOIN `developer` d ON p.id = d.id
JOIN `website_role` wr ON d.id = wr.developer_id
JOIN `website` w ON wr.website_id = w.id
JOIN `website_priviledge` wp ON wp.developer_id = d.id AND wp.website_id = w.id
LEFT JOIN `page` pg ON w.id = pg.website_id
LEFT JOIN `page_role` pgr ON pg.id = pgr.page_id AND d.id = pgr.developer_id
LEFT JOIN `page_priviledge` pgp ON pg.id = pgp.page_id AND d.id = pgp.developer_id

-- SELECT * FROM `developer_roles_and_priviledges`;