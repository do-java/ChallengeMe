CREATE TABLE echo(
 id serial PRIMARY KEY,
 value VARCHAR (250) UNIQUE NOT NULL,
 createDate TIMESTAMP,
 updatingDate TIMESTAMP
);