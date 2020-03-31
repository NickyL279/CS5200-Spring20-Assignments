#1
SET SQL_SAFE_UPDATES = 0;
DELETE FROM address WHERE `primary` = 1 and person_id = (SELECT person.id FROM person WHERE person.username = 'charlie');
SET SQL_SAFE_UPDATES = 1;

#2
SET SQL_SAFE_UPDATES = 0;
DELETE FROM widget WHERE page_id = (SELECT page.id FROM page WHERE page.title = 'Contact')
ORDER BY `order` DESC LIMIT 1;
SET SQL_SAFE_UPDATES = 1;

#3
SET SQL_SAFE_UPDATES = 0;
DELETE FROM page WHERE website_id = (SELECT website.id FROM website WHERE website.name = 'Wikipedia') 
ORDER BY `updated` DESC LIMIT 1;
SET SQL_SAFE_UPDATES = 1;

#4
SET SQL_SAFE_UPDATES = 0;
DELETE FROM website WHERE name = 'CNET';
SET SQL_SAFE_UPDATES = 1;
