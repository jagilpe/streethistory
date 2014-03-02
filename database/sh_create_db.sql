-- Create the role used by the application to connect with the DB --
CREATE ROLE sh_user PASSWORD 'sh_user' NOSUPERUSER NOCREATEDB NOCREATEROLE INHERIT LOGIN;
		
-- Database creation --
CREATE DATABASE db_streethistory OWNER sh_user;

-- Connect to db_streethistory DB
\c db_streethistory

-- PostGIS extension creation --
CREATE EXTENSION IF NOT EXISTS postgis;

SET ROLE sh_user;

\i sh_create_schema.sql