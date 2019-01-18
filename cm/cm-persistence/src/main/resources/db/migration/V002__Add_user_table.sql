CREATE TABLE user_model(
 id serial PRIMARY KEY,
 user_name VARCHAR (250) NOT NULL,
 email VARCHAR (250) UNIQUE NOT NULL,
 password VARCHAR (250) NOT NULL,
 confirmed boolean  DEFAULT false,
 deleted boolean  DEFAULT false,
 first_login DATE DEFAULT CURRENT_DATE NOT NULL,
 last_login DATE  DEFAULT CURRENT_DATE NOT NULL
);
