-- This script should be executed by a user with superuser permission in the test db server
-- Drop the existent database --
DROP DATABASE IF EXISTS sh_test;

-- Create the database --
CREATE DATABASE sh_test;

-- Connect to sh_test DB
\c sh_test

-- PostGIS extension creation --
CREATE EXTENSION IF NOT EXISTS postgis;

-- Include the schema creation script --
\i sh_create_schema.sql