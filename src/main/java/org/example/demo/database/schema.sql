CREATE USER buildog WITH PASSWORD 'your-beautiful-password';
CREATE DATABASE buildog;
GRANT ALL PRIVILEGES ON DATABASE buildog TO buildog;


CREATE TABLE IF NOT EXISTS users (
    id UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
    );
