DROP TABLE position 		CASCADE CONSTRAINTS;
DROP TABLE qualification	CASCADE CONSTRAINTS;
DROP TABLE staff			CASCADE CONSTRAINTS;
DROP TABLE patientG 		CASCADE CONSTRAINTS;
DROP TABLE hospital 		CASCADE CONSTRAINTS;
DROP TABLE laboratory		CASCADE CONSTRAINTS;
DROP TABLE drugstores   	CASCADE CONSTRAINTS;
DROP TABLE drugs 			CASCADE CONSTRAINTS;
DROP TABLE tests 			CASCADE CONSTRAINTS;
DROP TABLE schedule 		CASCADE CONSTRAINTS;
DROP TABLE patientF			CASCADE CONSTRAINTS;
DROP TABLE availability		CASCADE CONSTRAINTS;

DROP SEQUENCE position_seq;
DROP SEQUENCE qualification_seq;
DROP SEQUENCE staff_seq;
DROP SEQUENCE patientG_seq;
DROP SEQUENCE hospital_seq;
DROP SEQUENCE laboratory_seq;
DROP SEQUENCE drugstores_seq;
DROP SEQUENCE drugs_seq;
DROP SEQUENCE tests_seq;
DROP SEQUENCE schedule_seq;

-- Position table
-----------------
CREATE TABLE position (
id NUMBER(7) CONSTRAINT position_id_pk PRIMARY KEY,
posdesc VARCHAR2(30)
);

CREATE SEQUENCE position_seq;

CREATE OR REPLACE TRIGGER position_bir
BEFORE INSERT ON position
FOR EACH ROW

BEGIN
SELECT position_seq.NEXTVAL
INTO :new.id
FROM dual;
END;
/

-- Qualification table
----------------------
CREATE TABLE qualification (
id NUMBER(7) CONSTRAINT qualification_id_pk PRIMARY KEY,
qualdesc VARCHAR2(30)
);

CREATE SEQUENCE qualification_seq;

CREATE OR REPLACE TRIGGER qualification_bir
BEFORE INSERT ON qualification
FOR EACH ROW

BEGIN
SELECT qualification_seq.NEXTVAL
INTO :new.id
FROM dual;
END;
/

-- Tests table
--------------
CREATE TABLE tests (
id NUMBER(20) CONSTRAINT tests_id_pk PRIMARY KEY,
res VARCHAR(1000)
);

CREATE SEQUENCE tests_seq;

CREATE OR REPLACE TRIGGER tests_bir
BEFORE INSERT ON tests
FOR EACH ROW

BEGIN
SELECT tests_seq.NEXTVAL
INTO :new.id
FROM dual;
END;
/

-- Drugs table
---------------
CREATE TABLE drugs (
id NUMBER(5) CONSTRAINT drugs_id_pk PRIMARY KEY,
name VARCHAR2(30),
dose VARCHAR2(30),
price NUMBER(6,2)
);

CREATE SEQUENCE drugs_seq;

CREATE OR REPLACE TRIGGER drugs_bir
BEFORE INSERT ON drugs
FOR EACH ROW

BEGIN
SELECT drugs_seq.NEXTVAL
INTO :new.id
FROM dual;
END;
/

-- Patients general information table
-------------------------------------
CREATE TABLE patientG (
id NUMBER(11) CONSTRAINT patientG_id PRIMARY KEY,
fname VARCHAR2(30),
lname VARCHAR2(30),
bdate DATE,
email VARCHAR2(30),
address VARCHAR2(30),
zip VARCHAR2(7),
medcard VARCHAR(15),
inssurance VARCHAR(300)
);

CREATE SEQUENCE patientG_seq;

CREATE OR REPLACE TRIGGER patientG_bir
BEFORE INSERT ON patientG
FOR EACH ROW

BEGIN
SELECT patientG_seq.NEXTVAL
INTO :new.id
FROM dual;
END;
/


-- Staff information
--------------------
CREATE TABLE staff (
id NUMBER(7) CONSTRAINT staff_id_pk PRIMARY KEY,
fname VARCHAR2(30),
lname VARCHAR2(30),
bdate DATE,
email VARCHAR2(30),
address VARCHAR2(30),
zip VARCHAR2(7),
phone VARCHAR2(17),
posid NUMBER(7),
qualid NUMBER(7),
CONSTRAINT staff_pos_fk FOREIGN KEY (posid) REFERENCES position(id),
CONSTRAINT staff_qual_fk FOREIGN KEY (qualid) REFERENCES qualification(id)
);

CREATE SEQUENCE staff_seq;

CREATE OR REPLACE TRIGGER staff_bir
BEFORE INSERT ON staff
FOR EACH ROW

BEGIN
SELECT staff_seq.NEXTVAL
INTO :new.id
FROM dual;
END;
/


-- Drugstores informations
--------------------------
CREATE TABLE drugstores (
id NUMBER(5) CONSTRAINT drugstores_id_pk PRIMARY KEY,
name VARCHAR2(30),
address VARCHAR2(30),
phone VARCHAR2(17),
openh VARCHAR(10),
drugid NUMBER(5),
CONSTRAINT drugstores_drug_fk FOREIGN KEY (drugid) REFERENCES drugs(id)
);

CREATE SEQUENCE drugstores_seq;

CREATE OR REPLACE TRIGGER drugstores_bir
BEFORE INSERT ON drugstores
FOR EACH ROW

BEGIN
SELECT drugstores_seq.NEXTVAL
INTO :new.id
FROM dual;
END;
/


-- Availability of drugs in the drugstores
----------------------------------------------
CREATE TABLE availability(
storeid NUMBER(5),
drugid NUMBER(5),
avlb CHAR(1) DEFAULT 'N',
CONSTRAINT availability_storid_drugid_pk PRIMARY KEY (storeid, drugid),
CONSTRAINT availability_storid_fk FOREIGN KEY (storeid) REFERENCES drugstores (id),
CONSTRAINT availability_drugid_fk FOREIGN KEY (drugid) REFERENCES drugs (id)
);


-- hospital information table
-----------------------------
CREATE TABLE hospital (
id NUMBER(5) CONSTRAINT hospital_id_pk PRIMARY KEY,
name VARCHAR2(30),
address VARCHAR2(30),
phone VARCHAR2(17),
staffid NUMBER(7),
CONSTRAINT hospital_staffid_fk FOREIGN KEY (staffid) REFERENCES staff(id)
);

CREATE SEQUENCE hospital_seq;

CREATE OR REPLACE TRIGGER hospital_bir
BEFORE INSERT ON hospital
FOR EACH ROW

BEGIN
SELECT hospital_seq.NEXTVAL
INTO :new.id
FROM dual;
END;
/

-- laboratory table
-------------------
CREATE TABLE laboratory (
id NUMBER(5) CONSTRAINT laboratory_id_pk PRIMARY KEY,
ardate DATE,
depdate DATE,
patientid NUMBER(11),
hospid NUMBER(5),
staffid NUMBER(7),
testid NUMBER(20),
CONSTRAINT laboratory_patientid_fk FOREIGN KEY (patientid) REFERENCES patientG(id),
CONSTRAINT laboratory_hospid_fk FOREIGN KEY (hospid) REFERENCES hospital(id),
CONSTRAINT laboratory_staffid_fk FOREIGN KEY (staffid) REFERENCES staff(id),
CONSTRAINT laboratory_testid_fk FOREIGN KEY (testid) REFERENCES tests(id)
);

CREATE SEQUENCE laboratory_seq;

CREATE OR REPLACE TRIGGER laboratory_bir
BEFORE INSERT ON laboratory
FOR EACH ROW

BEGIN
SELECT laboratory_seq.NEXTVAL
INTO :new.id
FROM dual;
END;
/


-- Schedule table
-----------------
CREATE TABLE schedule (
id NUMBER(7) CONSTRAINT schedule_id_pk PRIMARY KEY,
datenext DATE,
dateprev DATE,
history VARCHAR(1000),
staffid NUMBER(7),
patientid NUMBER(11),
CONSTRAINT schedule_staffid_fk FOREIGN KEY (staffid) REFERENCES staff(id),
CONSTRAINT schedule_patientid_fk FOREIGN KEY (patientid) REFERENCES patientG(id)
);

CREATE SEQUENCE schedule_seq;

CREATE OR REPLACE TRIGGER schedule_bir
BEFORE INSERT ON schedule
FOR EACH ROW

BEGIN
SELECT schedule_seq.NEXTVAL
INTO :new.id
FROM dual;
END;
/


-- Patient medical information table
------------------------------------
CREATE TABLE patientF(
patientid NUMBER(11),
staffid NUMBER(7),
hospid NUMBER(5),
testid NUMBER(20),
schedid NUMBER(7),
prescription VARCHAR(1000),
anamnesis VARCHAR(3000),
diagnosis VARCHAR(1000),
CONSTRAINT patientF_patid_schedid_pk PRIMARY KEY (patientid, schedid),
CONSTRAINT patientF_patientid_fk FOREIGN KEY (patientid) REFERENCES patientG(id),
CONSTRAINT patientF_staffid_fk FOREIGN KEY (staffid) REFERENCES staff(id),
CONSTRAINT patientF_hospid_fk FOREIGN KEY (hospid) REFERENCES hospital(id),
CONSTRAINT patientF_schedid_fk FOREIGN KEY (schedid) REFERENCES schedule(id),
CONSTRAINT patientF_testid_fk FOREIGN KEY (testid) REFERENCES tests(id)
);

commit;




