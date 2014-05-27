DROP TABLE users CASCADE CONSTRAINT;
DROP TABLE suppliers CASCADE CONSTRAINT;
DROP TABLE songs CASCADE CONSTRAINT;
DROP TABLE payments CASCADE CONSTRAINT;
DROP SEQUENCE users_seq;
DROP SEQUENCE supp_seq;
DROP SEQUENCE song_seq;


CREATE TABLE users (
id NUMBER(7) CONSTRAINT users_id_pk PRIMARY KEY,
login VARCHAR2(16),
pass VARCHAR2(60),
role CHAR(1) DEFAULT 'U',
fname VARCHAR2(30),
lname VARCHAR2(30),
email VARCHAR2(30),
country VARCHAR2(30) DEFAULT 'Canada',
state VARCHAR2(15),
address VARCHAR2(30),
zip VARCHAR2(7),
phone VARCHAR2(17),
sess VARCHAR2(1) DEFAULT 'N');


CREATE TABLE suppliers (
id NUMBER(5) CONSTRAINT suppliers_id_pk PRIMARY KEY,
name VARCHAR2(30),
contact NUMBER(7),
CONSTRAINT suppliers_contact_fk FOREIGN KEY (contact) REFERENCES users(id)
);

CREATE TABLE songs (
id NUMBER(11) CONSTRAINT songs_id PRIMARY KEY,
title VARCHAR2(30),
singer VARCHAR2(60),
album VARCHAR2(60),
price NUMBER(6,2),
enterPrice NUMBER(6,2),
numOfLic NUMBER(5),
endOfReal DATE,
booked NUMBER(10),
suppid NUMBER(5),
CONSTRAINT songs_suppid_fk FOREIGN KEY (suppid) REFERENCES suppliers(id)
);

CREATE TABLE payments (
userid NUMBER(7),
songid NUMBER(11),
transNum VARCHAR2(60),
paymentDate DATE,
paymentMethod VARCHAR2(15),
CONSTRAINT payments_usid_songid_pk PRIMARY KEY(userid, transnum),
CONSTRAINT payments_userid_fk FOREIGN KEY (userid) REFERENCES users(id),
CONSTRAINT payments_songid_fk FOREIGN KEY (songid) REFERENCES songs(id)
);


CREATE SEQUENCE users_seq;
CREATE SEQUENCE supp_seq;
CREATE SEQUENCE song_seq;

CREATE OR REPLACE TRIGGER user_bir
BEFORE INSERT ON users
FOR EACH ROW

BEGIN
SELECT users_seq.NEXTVAL
INTO :new.id
FROM dual;
END;
/

CREATE OR REPLACE TRIGGER supp_bir
BEFORE INSERT ON suppliers
FOR EACH ROW

BEGIN
SELECT supp_seq.NEXTVAL
INTO :new.id
FROM dual;
END;
/

CREATE OR REPLACE TRIGGER song_bir
BEFORE INSERT ON songs
FOR EACH ROW

BEGIN
SELECT song_seq.NEXTVAL
INTO :new.id
FROM dual;
END;
/


INSERT INTO users VALUES(1, 'susanin', 'QwpmBQ+f9pnieRDnLI23GQ==', 'M', 'Ivan', 'Susanin', 
'email@site.com', 'Canada', 'QC', '1256 rue Vanier', 'H3X-S3F', '555-111-5324', 'N'); 

INSERT INTO users (id, login, pass, role, fname, lname, email, zip, phone) 
VALUES(2, 'stenM', 'QwpmBQ+f9pnieRDnLI23GQ==', 'S', 'Sten', 'Murray', 
'stenM@mail.com', '123-548', '555-658-5489'); 

INSERT INTO users (login, pass, role, fname, lname, email) 
VALUES('scbob', 'QwpmBQ+f9pnieRDnLI23GQ==', 'U', 'Bob', 'Scott', 
'bob@mail.com'); 

INSERT INTO suppliers VALUES(1, 'EuroRecord', 2);
INSERT INTO suppliers (id, name) VALUES(2, 'LabelRecord');
INSERT INTO suppliers (id, name) VALUES(3, 'MusicStudio');

INSERT INTO songs (title, singer, album, price, enterPrice, endOfReal, numOfLic, suppid) 
VALUES ('Deser Rose', 'Sting', 'Brand New Day', 1.0, 0.7, '30-MAR-2014', 20,  1);

INSERT INTO songs (title, singer, album, price, enterPrice, endOfReal, numOfLic, suppid) 
VALUES ('Knocking on Heaven''s dooor', 'Bob Dylan', 'Patt Garette and Billy the kid', 
0.22, 0.1, '15-APR-2014', 10, 1);

INSERT INTO songs (title, singer, album, price, enterPrice, endOfReal, numOfLic, suppid) 
VALUES ('Yellow submarine', 'The Beatles', 'Yellow submarine', 0.1, 0.05, 
'30-MAR-2014', 20, 2);

INSERT INTO songs (title, singer, album, price, enterPrice, endOfReal, numOfLic, suppid) 
VALUES ('Voodoo People', 'The Prodigy', 'Music for the Jilted Generation', 
1.3, 1, '15-APR-2015', 5, 3);

INSERT INTO songs (title, singer, album, price, enterPrice, endOfReal, numOfLic, suppid)
VALUES ('Lemon Tree', 'The Beatles', 'Single', 0.6, 0.5, '1-JUL-2014', 10, 2);

INSERT INTO songs (title, singer, album, price, enterPrice, endOfReal, numOfLic, suppid) 
VALUES ('Just give me a reason', 'Pink', 'The truth about love', 2, 1.8, '18-SEP-2016', 40, 3);

INSERT INTO songs (title, singer, album, price, enterPrice, endOfReal, numOfLic, suppid) 
VALUES ('Let me go', 'Avril Lavigne', 'Avril Lavigne', 1.4, 1.1, '18-SEP-2016', 20, 1);

INSERT INTO songs (title, singer, album, price, enterPrice, endOfReal, numOfLic, suppid)
VALUES ('Waterloo', 'ABBA', 'Waterloo', 0.2, 0.15, '11-DEC-2014', 5, 2);

INSERT INTO songs (title, singer, album, price, enterPrice, endOfReal, numOfLic, suppid)
VALUES ('Cartoon Heroes', 'Aqua', 'Aquirius', 0.15, 0.10, '17-AUG-2014', 5, 2);

INSERT INTO songs (title, singer, album, price, enterPrice, endOfReal, numOfLic, suppid) 
VALUES ('Try', 'Pink', 'The truth about love', 1, 0.9, '13-JUL-2014', 20, 1);

INSERT INTO payments VALUES (3, 2, 's15468-1548', '03-may-2014', 'DEBIT');
INSERT INTO payments VALUES (2, 2, 's154568-5848', '02-may-2014', 'VISA');

update songs set booked=7 where id = 1;
update songs set booked=6 where id = 2;
update songs set booked=2 where id = 3;

commit;