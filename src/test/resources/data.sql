DELETE FROM PUBLIC.ITEMS;

INSERT INTO PUBLIC.ITEMS (id, date, parent_id, url, type, size)
VALUES( 'item1', '2022-09-10T21:12:01.000Z', null, null, 'FOLDER', null);
INSERT INTO PUBLIC.ITEMS (id, date, parent_id, url, type, size)
VALUES( 'item2', '2022-09-10T22:12:01.000Z', 'item1' , '/file/url15', 'FILE', 100);
INSERT INTO PUBLIC.ITEMS (id, date, parent_id, url, type, size)
VALUES( 'item3', '2022-09-11T00:12:01.000Z','item1',  '/file/url15h', 'FILE', 100);