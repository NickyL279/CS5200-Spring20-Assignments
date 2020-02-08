USE a2;
#1
SET SQL_SAFE_UPDATES = 0;
UPDATE phone SET phone = '333-444-5555' WHERE `primary` = 1 AND 
person_id = (SELECT person.id FROM person WHERE person.username = 'charlie');
SET SQL_SAFE_UPDATES = 1;

#2
SET SQL_SAFE_UPDATES = 0;
UPDATE widget SET `order` = 3 WHERE name = 'head345';
UPDATE widget SET `order` = 4 - `order` WHERE name != 'head345' AND page_id = 
(SELECT id FROM page WHERE title = 'Contact');
SET SQL_SAFE_UPDATES = 1;

#3
SET SQL_SAFE_UPDATES = 0;
UPDATE page SET title = concat('CNET - ', title) WHERE website_id = (SELECT id FROM website WHERE name = 'CNET');
SET SQL_SAFE_UPDATES = 1;

#4
SET SQL_SAFE_UPDATES = 0;
UPDATE page_role pr1 JOIN page_role pr2 
SET pr1.role = pr2.role, pr2.role = pr1.role
WHERE pr1.developer_id = (SELECT id FROM person p WHERE p.username = 'charlie')
AND pr1.page_id = (SELECT page.id FROM page JOIN website w ON page.website_id = w.id WHERE page.title = 'Home' AND w.name = 'CNET')
AND pr2.developer_id = (SELECT id FROM person p WHERE p.username = 'bob')
AND pr2.page_id = (SELECT page.id FROM page JOIN website w ON page.website_id = w.id WHERE page.title = 'Home' AND w.name = 'CNET');
SET SQL_SAFE_UPDATES = 1;


