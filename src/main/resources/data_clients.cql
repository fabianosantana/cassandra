USE test;

CREATE TABLE clients (
id INT PRIMARY KEY,
first_name TEXT,
last_name TEXT
);
INSERT INTO clients (id, first_name, last_name) VALUES (1, 'Bob', 'Moore');
INSERT INTO clients (id, first_name, last_name) VALUES (2, 'Brian', 'Nelson');
