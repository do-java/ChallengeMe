CREATE TABLE echo (
 id serial PRIMARY KEY,
 value VARCHAR (250) UNIQUE NOT NULL,
 created_date TIMESTAMP,
 modified_date TIMESTAMP
);