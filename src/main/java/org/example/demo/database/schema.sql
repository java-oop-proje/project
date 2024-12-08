CREATE USER oop WITH PASSWORD 'your-beautiful-password';
CREATE DATABASE oop;
GRANT ALL PRIVILEGES ON DATABASE oop TO oop;


CREATE TABLE users (
    user_id SERIAL PRIMARY KEY,
    firstname VARCHAR(50),
    lastname VARCHAR(50),
    email VARCHAR(100),
    password VARCHAR(255)
);

CREATE TABLE user_details (
   detail_id SERIAL PRIMARY KEY,
   user_id INTEGER REFERENCES users(user_id),
   phone_number VARCHAR(20),
   street_address TEXT,
   city VARCHAR(50),
   state VARCHAR(50),
   zip VARCHAR(10),
   education TEXT,
   study_abroad TEXT,
   high_school TEXT,
   experience TEXT,
   leadership_activities TEXT,
   skills_interests TEXT
);