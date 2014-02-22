-- Database generated with pgModeler (PostgreSQL Database Modeler).
-- pgModeler  version: 0.7.0-alpha
-- PostgreSQL version: 9.3
-- Project Site: pgmodeler.com.br
-- Model Author: ---

SET check_function_bodies = false;
-- ddl-end --


-- Database creation must be done outside an multicommand file.
-- These commands were put in this file only for convenience.
-- -- object: db_streethistory | type: DATABASE --
-- -- DROP DATABASE db_streethistory;
-- CREATE DATABASE db_streethistory
-- ;
-- -- Appended SQL commands --
CREATE EXTENSION IF NOT EXISTS postgis;		
-- CREATE EXTENSION fuzzystrmatch;
-- CREATE EXTENSION postgis_tiger_geocoder;
-- -- ddl-end --
-- 

-- object: public.photo | type: TABLE --
-- DROP TABLE public.photo;
CREATE TABLE public.photo(
	id serial NOT NULL,
	title varchar(100) NOT NULL,
	extract char(250),
	description text,
	location geometry(POINT, 4326),
	date date,
	url varchar(250),
	CONSTRAINT pk_photo_id PRIMARY KEY (id)

);
-- ddl-end --
-- object: public.tag | type: TABLE --
-- DROP TABLE public.tag;
CREATE TABLE public.tag(
	tag varchar(25) NOT NULL,
	CONSTRAINT pk_tag PRIMARY KEY (tag)

);
-- ddl-end --
-- object: public.scene | type: TABLE --
-- DROP TABLE public.scene;
CREATE TABLE public.scene(
	id serial NOT NULL,
	title varchar(200) NOT NULL,
	description text,
	location geometry(POINT, 4326),
	CONSTRAINT pk_scene_id PRIMARY KEY (id)

);
-- ddl-end --
-- object: public.many_scene_has_many_photo | type: TABLE --
-- DROP TABLE public.many_scene_has_many_photo;
CREATE TABLE public.many_scene_has_many_photo(
	id_scene integer,
	id_photo integer,
	CONSTRAINT many_scene_has_many_photo_pk PRIMARY KEY (id_scene,id_photo)

);
-- ddl-end --
-- object: scene_fk | type: CONSTRAINT --
-- ALTER TABLE public.many_scene_has_many_photo DROP CONSTRAINT scene_fk;
ALTER TABLE public.many_scene_has_many_photo ADD CONSTRAINT scene_fk FOREIGN KEY (id_scene)
REFERENCES public.scene (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --


-- object: photo_fk | type: CONSTRAINT --
-- ALTER TABLE public.many_scene_has_many_photo DROP CONSTRAINT photo_fk;
ALTER TABLE public.many_scene_has_many_photo ADD CONSTRAINT photo_fk FOREIGN KEY (id_photo)
REFERENCES public.photo (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --


-- object: public.many_photo_has_many_tag | type: TABLE --
-- DROP TABLE public.many_photo_has_many_tag;
CREATE TABLE public.many_photo_has_many_tag(
	id_photo integer,
	tag_tag varchar(25),
	CONSTRAINT many_photo_has_many_tag_pk PRIMARY KEY (id_photo,tag_tag)

);
-- ddl-end --
-- object: photo_fk | type: CONSTRAINT --
-- ALTER TABLE public.many_photo_has_many_tag DROP CONSTRAINT photo_fk;
ALTER TABLE public.many_photo_has_many_tag ADD CONSTRAINT photo_fk FOREIGN KEY (id_photo)
REFERENCES public.photo (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --


-- object: tag_fk | type: CONSTRAINT --
-- ALTER TABLE public.many_photo_has_many_tag DROP CONSTRAINT tag_fk;
ALTER TABLE public.many_photo_has_many_tag ADD CONSTRAINT tag_fk FOREIGN KEY (tag_tag)
REFERENCES public.tag (tag) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --


-- object: public.many_scene_has_many_tag | type: TABLE --
-- DROP TABLE public.many_scene_has_many_tag;
CREATE TABLE public.many_scene_has_many_tag(
	id_scene integer,
	tag_tag varchar(25),
	CONSTRAINT many_scene_has_many_tag_pk PRIMARY KEY (id_scene,tag_tag)

);
-- ddl-end --
-- object: scene_fk | type: CONSTRAINT --
-- ALTER TABLE public.many_scene_has_many_tag DROP CONSTRAINT scene_fk;
ALTER TABLE public.many_scene_has_many_tag ADD CONSTRAINT scene_fk FOREIGN KEY (id_scene)
REFERENCES public.scene (id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --


-- object: tag_fk | type: CONSTRAINT --
-- ALTER TABLE public.many_scene_has_many_tag DROP CONSTRAINT tag_fk;
ALTER TABLE public.many_scene_has_many_tag ADD CONSTRAINT tag_fk FOREIGN KEY (tag_tag)
REFERENCES public.tag (tag) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --



