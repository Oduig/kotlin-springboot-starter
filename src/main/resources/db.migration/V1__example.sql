CREATE TABLE examples
(
   id SERIAL PRIMARY KEY NOT NULL,
   name TEXT UNIQUE NOT NULL,
   created_on TIMESTAMP NOT NULL DEFAULT (NOW() AT TIME ZONE 'UTC')
);

INSERT INTO examples (id, name)
VALUES (1, 'Example 1');
