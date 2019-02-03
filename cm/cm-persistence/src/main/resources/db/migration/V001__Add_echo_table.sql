CREATE TABLE echo (
 id serial PRIMARY KEY,
 value VARCHAR (250) UNIQUE NOT NULL,
 created_date TIMESTAMP,
 modified_date TIMESTAMP
);

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

INSERT INTO user_model VALUES
    (1, 'Bananas', '105', '1971-07-13');
INSERT INTO user_model VALUES (2, 'Bananas', '106', '1971-07-13');
INSERT INTO user_model VALUES
    (3, 'Bananas', '107', '1971-07-13');
INSERT INTO user_model VALUES
    (4, 'Bananas', '108', '1971-07-13');