CREATE TABLE challenge_model(
 id SERIAL PRIMARY KEY,
 name VARCHAR (50) NOT NULL,
 type VARCHAR (50) NOT NULL,
 description VARCHAR (225) NOT NULL,
 acess VARCHAR (25) NOT NULL,
 status VARCHAR (25) NOT NULL,
 max_members INTEGER NOT NULL,
 priority INTEGER,
 start_date DATE,
 end_date DATE
);