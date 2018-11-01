CREATE TABLE user_model(
 id serial PRIMARY KEY,
 user_name VARCHAR (250) NOT NULL,
 email VARCHAR (250) UNIQUE NOT NULL,
 password VARCHAR (250) NOT NULL,
 confirmed boolean NOT NULL DEFAULT false,
 deleted boolean NOT  NULL DEFAULT false,
 first_login DATE NOT NULL DEFAULT CURRENT_DATE,
 last_login DATE NOT NULL DEFAULT CURRENT_DATE
);