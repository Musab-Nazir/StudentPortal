DROP TABLE IF EXISTS students;
CREATE TABLE IF NOT EXISTS students (
    id bigint PRIMARY KEY NOT NULL REFERENCES Users (user_id) ON DELETE CASCADE,
    program_code VARCHAR(8) NOT NULL REFERENCES programs (programcode),
    year CHAR(1) NOT NULL
);
	
insert into students VALUES(100111110,'CPA',2);
INSERT INTO students VALUES (100111111, 'CPA', 3);
INSERT INTO students VALUES (100454543, 'CPA', 2);
INSERT INTO students VALUES (100222222, 'ADP', 3);
INSERT INTO students VALUES (100333333, 'BTYF', 2);