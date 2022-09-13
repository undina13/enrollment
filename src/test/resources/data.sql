DELETE
FROM PUBLIC.ITEMS;

INSERT INTO PUBLIC.ITEMS (id, date, parent_id, url, type, size)
VALUES ('item1', '2022-09-10 21:12:01.00', null, null, 'FOLDER', null);
INSERT INTO PUBLIC.ITEMS (id, date, parent_id, url, type, size)
VALUES ('item2', '2022-09-10 22:12:01.00', 'item1', '/file/url15', 'FILE', 100);
INSERT INTO PUBLIC.ITEMS (id, date, parent_id, url, type, size)
VALUES ('item3', '2022-09-11 00:12:01.00', 'item1', '/file/url15h', 'FILE', 100);