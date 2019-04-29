DROP TABLE IF EXISTS Users;


CREATE TABLE Users(
  user_id BIGINT PRIMARY KEY NOT NULL,
  password VARCHAR(40) NOT NULL,
  first_name VARCHAR(128) NOT NULL,
  last_name VARCHAR(128) NOT NULL,
  email VARCHAR(256) NOT NULL,
  type CHAR(1) NOT NULL,
  enabled BOOLEAN,
  last_access DATE NOT NULL,
  enrol_date DATE NOT NULL
);

INSERT INTO Users (user_id,password,first_name,last_name,email,type,enabled,last_access,enrol_date)
VALUES (100111111,encode(digest('password', 'sha1'), 'hex'),'Mike','Jones','mike.jones@dcmail.ca',
's','true','11-Sep-2015','11-Sep-2015');

INSERT INTO Users (user_id,password,first_name,last_name,email,type,enabled,last_access,enrol_date)
VALUES (100454543,encode(digest('password', 'sha1'), 'hex'),'Musab','Nazir','musab.nazir@dcmail.ca',
's','true','11-Jan-2019','11-Sep-2017');

INSERT INTO Users (user_id,password,first_name,last_name,email,type,enabled,last_access,enrol_date)
VALUES (100252525,encode(digest('password', 'sha1'), 'hex'),'Bruce','Wayne','bruce.wayne@dcmail.ca',
's','true','10-Oct-2018','06-Sep-2016');

INSERT INTO Users (user_id,password,first_name,last_name,email,type,enabled,last_access,enrol_date)
VALUES (100333333,encode(digest('password', 'sha1'), 'hex'),'Austin','Garrod','austin.garrod@dcmail.ca',
'f','true',CURRENT_DATE, CURRENT_DATE);

INSERT INTO Users (user_id,password,first_name,last_name,email,type,enabled,last_access,enrol_date)
VALUES (100444444,encode(digest('password', 'sha1'), 'hex'),'Stephen','Forbes','stephen.forbes@dcmail.ca',
'f','true',CURRENT_DATE, CURRENT_DATE);

INSERT INTO Users (user_id,password,first_name,last_name,email,type,enabled,last_access,enrol_date)
VALUES (100555555,encode(digest('password', 'sha1'), 'hex'),'Darren','Puffer','darren.puffer@dcmail.ca',
'f','true',CURRENT_DATE, CURRENT_DATE);