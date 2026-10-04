CREATE TABLE roles (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       email VARCHAR(255) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       enabled BOOLEAN DEFAULT TRUE,
                       created_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE user_roles (
                            user_id INT REFERENCES users(id),
                            role_id INT REFERENCES roles(id),
                            PRIMARY KEY (user_id, role_id)
);
