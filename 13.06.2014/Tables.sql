DROP TABLE position 		CASCADE CONSTRAINTS;
DROP TABLE qualification	CASCADE CONSTRAINTS;
DROP TABLE staff			CASCADE CONSTRAINTS;
DROP TABLE patient 			CASCADE CONSTRAINTS;
DROP TABLE hospital 		CASCADE CONSTRAINTS;
DROP TABLE laboratory		CASCADE CONSTRAINTS;
DROP TABLE drugstores   	CASCADE CONSTRAINTS;
DROP TABLE drugs 			CASCADE CONSTRAINTS;
DROP TABLE tests 			CASCADE CONSTRAINTS;
DROP TABLE schedule 		CASCADE CONSTRAINTS;
DROP TABLE prescription 		CASCADE CONSTRAINTS;

DROP SEQUENCE position_seq;
DROP SEQUENCE qualification_seq;
DROP SEQUENCE staff_seq;
DROP SEQUENCE patient_seq;
DROP SEQUENCE hospital_seq;
DROP SEQUENCE laboratory_seq;
DROP SEQUENCE drugstores_seq;
DROP SEQUENCE drugs_seq;
DROP SEQUENCE tests_seq;
DROP SEQUENCE schedule_seq;
DROP SEQUENCE prescription_seq;

--position table 
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

--Qualification table
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

--Laboratory Table
CREATE TABLE laboratory (
id NUMBER(5) CONSTRAINT laboratory_id_pk PRIMARY KEY,
labname  VARCHAR2(30),
labaddress  VARCHAR2(30),
labphone VARCHAR2(17)
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

--Test Table
CREATE TABLE tests (
id NUMBER(20) CONSTRAINT test_id_pk PRIMARY KEY,
res VARCHAR(1000),
ardate DATE,
depdate DATE,
labid NUMBER(5),
CONSTRAINT tests_labid_fk FOREIGN KEY (labid) REFERENCES laboratory(id)
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

--Drugstore Information Table
CREATE TABLE drugstores (
id NUMBER(5) CONSTRAINT drugstores_id_pk PRIMARY KEY,
name VARCHAR2(30),
address VARCHAR2(30),
phone VARCHAR2(17),
openh VARCHAR(10),
avlb CHAR(1) DEFAULT 'N'
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

--Drugs Table
CREATE TABLE drugs (
id NUMBER(5) CONSTRAINT drugs_id_pk PRIMARY KEY,
storeid NUMBER(5),
name VARCHAR2(30),
dose VARCHAR2(30),
price NUMBER(6,2),
drugstoreid NUMBER(5),
CONSTRAINT drugstores_drugstoreid_fk FOREIGN KEY (drugstoreid) REFERENCES drugstores(id)
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

--Prescription table
CREATE TABLE prescription (
id NUMBER(10) CONSTRAINT prescription_id_pk PRIMARY KEY,
prescription VARCHAR(100),
drugid NUMBER(5),
CONSTRAINT prescription_drugid_fk FOREIGN KEY (drugid) REFERENCES drugs(id)
);

CREATE SEQUENCE prescription_seq;

CREATE OR REPLACE TRIGGER prescription_bir
BEFORE INSERT ON prescription
FOR EACH ROW

BEGIN
SELECT prescription_seq.NEXTVAL
INTO :new.id
FROM dual;
END;
/

--Patient General Information table
 CREATE TABLE patient (
id NUMBER(11) CONSTRAINT patient_id_pk PRIMARY KEY,
fname VARCHAR2(30),
lname VARCHAR2(30),
bdate DATE,
email VARCHAR2(30),
address VARCHAR2(30),
zip VARCHAR2(7),
phone VARCHAR2(17),
medcard VARCHAR(15),
inssurance VARCHAR(300),
anamnesis VARCHAR(3000),
diagnosis VARCHAR(1000),
prescid NUMBER(10),
CONSTRAINT patient_prescid_fk FOREIGN KEY (prescid) REFERENCES prescription(id)
);

CREATE SEQUENCE patient_seq;

CREATE OR REPLACE TRIGGER patient_bir
BEFORE INSERT ON patient
FOR EACH ROW

BEGIN
SELECT patient_seq.NEXTVAL
INTO :new.id
FROM dual;
END;
/


--Hospital Information Table
CREATE TABLE hospital (
id NUMBER(5) CONSTRAINT hospital_id_pk PRIMARY KEY,
name VARCHAR2(30),
address VARCHAR2(30),
phone VARCHAR2(17)
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

--Staff Information
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
hospid NUMBER(5),
login VARCHAR2(16),
password VARCHAR2(128),
sess CHAR(1) DEFAULT 'N',
CONSTRAINT staff_pos_fk FOREIGN KEY (posid) REFERENCES position(id),
CONSTRAINT staff_qual_fk FOREIGN KEY (qualid) REFERENCES qualification(id),
CONSTRAINT staff_hosp_fk FOREIGN KEY (hospid) REFERENCES hospital(id)
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




--Schedule Table
CREATE TABLE schedule (
id NUMBER(20), 
patientid NUMBER(11),
sdate DATE,
staffid NUMBER(7),
testid NUMBER(11),
CONSTRAINT schedule_id_patientid_pk PRIMARY KEY (id, patientid),
CONSTRAINT schedule_staffid_fk FOREIGN KEY (staffid) REFERENCES staff(id),
CONSTRAINT schedule_testid_fk FOREIGN KEY (testid) REFERENCES tests(id),
CONSTRAINT schedule_patientid_fk FOREIGN KEY (patientid) REFERENCES patient(id)
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

commit;




