CREATE TABLE usr(
 id serial PRIMARY KEY,
 userName VARCHAR (250) NOT NULL
 email VARCHAR (250) UNIQUE NOT NULL
 password VARCHAR (250) UNIQUE NOT NULL
);