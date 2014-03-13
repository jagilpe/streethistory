INSERT INTO photo (id, title, extract, description, location, date, url) VALUES (1, 'Photo 1', 'Photo 1 extract', 'Photo 1 description', ST_GeomFromText('POINT(52.5162198 13.3776884)', 4326) , '2010-10-20', 'img1.jpg');
INSERT INTO photo (id, title, extract, description, location, date, url) VALUES (2, 'Photo 2', 'Photo 2 extract', 'Photo 2 description', ST_GeomFromText('POINT(52.5208415 13.4094134)', 4326) , '2010-10-20', 'img2.jpg');
INSERT INTO photo (id, title, extract, description, location, date, url) VALUES (3, 'Photo 3', 'Photo 3 extract', 'Photo 3 description', ST_GeomFromText('POINT(52.5144484 13.3501743)', 4326) , '2010-10-20', 'img3.jpg');

INSERT INTO scene (id, title, description, location) VALUES (1, 'Scene 1', 'Scene 1 description', ST_GeomFromText('POINT(52.5170832 13.3771906)', 4326));
INSERT INTO scene (id, title, description, location) VALUES (2, 'Scene 2', 'Scene 2 description', ST_GeomFromText('POINT(52.5208415 13.4094134)', 4326));
INSERT INTO scene (id, title, description, location) VALUES (3, 'Scene 3', 'Scene 3 description', ST_GeomFromText('POINT(52.5144484 13.3501743)', 4326));

INSERT INTO tag(tag) VALUES ('Tag_1'), ('Tag_2');

INSERT INTO many_photo_has_many_tag(id_photo, tag_tag) VALUES (1, 'Tag_1'), (1, 'Tag_2'), (2, 'Tag_1');

INSERT INTO many_scene_has_many_photo(id_Scene, id_photo) VALUES (1, 1);

INSERT INTO many_scene_has_many_tag(id_Scene, tag_tag) VALUES (1, 'Tag_1'), (1, 'Tag_2'), (2, 'Tag_1');