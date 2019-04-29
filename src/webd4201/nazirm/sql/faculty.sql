DROP TABLE IF EXISTS Faculty;

CREATE TABLE Faculty(
  id BIGINT PRIMARY KEY NOT NULL REFERENCES Users (user_id),
  school_code VARCHAR(8) NOT NULL,
  school_description VARCHAR(128) NOT NULL,
  office VARCHAR(10) NOT NULL,
  phone_ext VARCHAR(4) NOT NULL
);

INSERT INTO faculty(
	id, school_code, school_description, office, phone_ext)
	VALUES (100333333, 'BITM', 'Business IT and Management', 'SW103', '2342');

INSERT INTO faculty(
	id, school_code, school_description, office, phone_ext)
	VALUES (100444444, 'BITM', 'Business IT and Management', 'H206', '1045');

INSERT INTO faculty(
	id, school_code, school_description, office, phone_ext)
	VALUES (100555555, 'BITM', 'Business IT and Management', 'C305', '6741');