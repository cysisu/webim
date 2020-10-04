DROP DATABASE IF EXISTS webim;
CREATE DATABASE webim DEFAULT CHARACTER SET utf8;
USE webim;
CREATE TABLE user(
	name char(10) primary key,
    password char(10)
);
insert user values ('cy','1234');
insert user values ('cq','1234');