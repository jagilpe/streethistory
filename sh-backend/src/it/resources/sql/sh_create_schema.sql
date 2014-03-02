-- Tables creation --
-- Photo --
CREATE TABLE public.photo(
	id serial NOT NULL,
	title varchar(100) NOT NULL,
	extract text,
	description text,
	location geometry(POINT, 4326),
	date date,
	url varchar(250),
	CONSTRAINT pk_photo_id PRIMARY KEY (id)

);

-- Tag --
CREATE TABLE public.tag(
	tag varchar(25) NOT NULL,
	CONSTRAINT pk_tag PRIMARY KEY (tag)

);

-- Scene --
CREATE TABLE public.scene(
	id serial NOT NULL,
	title varchar(200) NOT NULL,
	description text,
	location geometry(POINT, 4326),
	CONSTRAINT pk_scene_id PRIMARY KEY (id)

);

-- Many to many relationships
-- Scene - Photo --
CREATE TABLE public.many_scene_has_many_photo(
	id_scene integer,
	id_photo integer,
	CONSTRAINT many_scene_has_many_photo_pk PRIMARY KEY (id_scene,id_photo)

);

ALTER TABLE public.many_scene_has_many_photo ADD CONSTRAINT scene_fk FOREIGN KEY (id_scene)
REFERENCES public.scene (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE public.many_scene_has_many_photo ADD CONSTRAINT photo_fk FOREIGN KEY (id_photo)
REFERENCES public.photo (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;

-- Photo - Tag --
CREATE TABLE public.many_photo_has_many_tag(
	id_photo integer,
	tag_tag varchar(25),
	CONSTRAINT many_photo_has_many_tag_pk PRIMARY KEY (id_photo,tag_tag)

);

ALTER TABLE public.many_photo_has_many_tag ADD CONSTRAINT photo_fk FOREIGN KEY (id_photo)
REFERENCES public.photo (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE public.many_photo_has_many_tag ADD CONSTRAINT tag_fk FOREIGN KEY (tag_tag)
REFERENCES public.tag (tag) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;

-- Scene - Tag --
CREATE TABLE public.many_scene_has_many_tag(
	id_scene integer,
	tag_tag varchar(25),
	CONSTRAINT many_scene_has_many_tag_pk PRIMARY KEY (id_scene,tag_tag)

);

ALTER TABLE public.many_scene_has_many_tag ADD CONSTRAINT scene_fk FOREIGN KEY (id_scene)
REFERENCES public.scene (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;

ALTER TABLE public.many_scene_has_many_tag ADD CONSTRAINT tag_fk FOREIGN KEY (tag_tag)
REFERENCES public.tag (tag) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;