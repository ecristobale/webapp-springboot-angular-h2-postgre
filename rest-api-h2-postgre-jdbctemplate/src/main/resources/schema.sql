DROP TABLE IF EXISTS entity;

CREATE TABLE entity(
   id serial PRIMARY KEY,
   name VARCHAR (250) NOT NULL,
   description VARCHAR (250) NOT NULL
);