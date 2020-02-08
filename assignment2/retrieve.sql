USE a2;
#Question1--a
SELECT * FROM person p, developer d WHERE p.id = d.id;
#b
SELECT * FROM person p WHERE id = 34;
#c
SELECT * FROM person p, developer d, website_role wbr, website wb 
WHERE wbr.role != 'owner' AND d.id = wbr.developer_id AND d.id = p.id AND wbr.website_id = wb.id AND wb.name = 'Twitter';
#d
SELECT * FROM page pg, developer d, page_role pgr, person p WHERE pg.views < 300000 
AND pgr.role = 'reviewer' AND pgr.page_id = pg.id AND pgr.developer_id = d.id  and d.id = p.id;
#e
SELECT * FROM person p WHERE p.id IN (SELECT page_role.developer_id FROM page_role
WHERE page_role.role = 'writer' AND page_role.page_id = (SELECT widget.page_id FROM widget WHERE widget.dtype = 'heading'
AND widget.page_id = (SELECT page.id FROM page WHERE page.title = 'Home')));

#Question2--a
SELECT * FROM website WHERE website.visits = (SELECT min(website.visits) FROM website) ;
#b
SELECT * FROM website WHERE website.id = 678;
#c
SELECT * FROM website wb WHERE wb.id IN (SELECT page.website_id FROM page WHERE page.id IN (SELECT pgr.page_id FROM page_role pgr
WHERE pgr.role = 'reviewer' AND pgr.page_id = (SELECT wd.page_id FROM widget wd WHERE wd.dtype = 'youtube') 
AND pgr.developer_id = (SELECT p.id FROM person p WHERE p.username = 'bob')));
#d
SELECT * FROM website WHERE id IN (SELECT wbr.website_id FROM website_role wbr
WHERE wbr.role = 'owner' AND wbr.developer_id = (SELECT p.id FROM person p WHERE p.username = 'alice'));
#e
SELECT * FROM website wb WHERE wb.id IN (SELECT wbr.website_id FROM website_role wbr WHERE 
wbr.developer_id =  (SELECT p.id FROM person p WHERE p.username = 'charlie') AND wbr.role = 'admin'  
AND wbr.website_id = (SELECT wb.id FROM website wb WHERE wb.visits > 6000000));

#Question3--a
SELECT * FROM page WHERE views = (SELECT MAX(views) FROM page);
#b
SELECT title FROM page WHERE id = 234;
#c
SELECT * FROM page pg, person p, page_role pgr
WHERE p.username = 'alice' AND p.id = pgr.developer_id AND pgr.role = 'editor' AND pgr.page_id = pg.id;
#d
SELECT SUM(pg.views) FROM page pg, website wb WHERE wb.name = 'CNET' AND wb.id = pg.website_id;
#e
SELECT AVG(pg.views) FROM page pg, website wb WHERE wb.name = 'Wikipedia' AND wb.id = pg.website_id;

#Question4--a
SELECT * FROM page pg, website wb, widget wd
WHERE wb.name = 'CNET' AND wb.id = pg.website_id AND wd.page_id = pg.id AND pg.title = 'Home' ;
#b
SELECT * FROM page pg, website wb, widget wd
WHERE wi.dtype = 'youtube' AND wi.page_id = pa.id AND pa.website_id = w.id AND w.name = 'CNN';
#c
SELECT * FROM person p, page pg, page_role pgr, website wb, widget wd
WHERE wd.page_id = pg.id AND wd.dtype = 'image' AND pgr.page_id = pg.id AND pgr.role = 'reviewer' AND p.username = 'alice' AND p.id = pgr.developer_id;
#d#
SELECT COUNT(wd.id) FROM widget wd, page pg, website wb
WHERE wb.name = 'Wikipedia' AND wb.id = pg.website_id AND pg.id = wd.page_id;

#Question5--a
SELECT name FROM website wb WHERE wb.id IN (SELECT website_id FROM website_priviledge WHERE priviledge = 'delete' 
AND website_priviledge.developer_id = (SELECT p.id FROM person p WHERE p.username = 'bob'));
#b
SELECT title FROM page pg WHERE pg.id IN (SELECT page_id FROM page_priviledge WHERE priviledge = 'create' 
AND page_priviledge.developer_id = (SELECT p.id FROM person p WHERE p.username = 'charlie'));