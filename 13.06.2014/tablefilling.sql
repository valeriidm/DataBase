/*
staff info

CREATE TABLE staff (
id NUMBER(7) CONSTRAINT staff_id_pk PRIMARY KEY,
fname VARCHAR2(30),
lname VARCHAR2(30),
bdate DATE,
email VARCHAR2(30),
address VARCHAR2(30),
zip VARCHAR2(7),
phone VARCHAR2(17),
SSN VARCHAR2(13),
posid VARCHAR2(30),
qualid VARCHAR2(30),
hospid NUMBER(5),
CONSTRAINT staff_pos_fk FOREIGN KEY (posid) REFERENCES position(id),
CONSTRAINT staff_qual_fk FOREIGN KEY (qualid) REFERENCES qualification(id),
CONSTRAINT staff_hosp_fk FOREIGN KEY (hospid) REFERENCES hospital(id)
);
*/

INSERT INTO staff (id, fname,lname, bdate, email, address, zip, phone, SSN, posid, qualid, hospid, login, password )
VALUES (1, 'John F.', 'Gilbert', '08-JAN-1961',  'JohnFGilbert@teleworm.us', 
'96 Rue de la Gauchetière E Montreal', 'L1H 7K5',  '905-431-1311', '673 747 515', 1, 1, 1,
'susanin', 'QwpmBQ+f9pnieRDnLI23GQ==');

INSERT INTO staff (fname,lname, bdate, email, address, zip, phone, SSN, posid, qualid, hospid )
VALUES ('Robert J.', 'Moore', '24-APR-1945' ,  'RobertJMoore@dayrep.com' , '3708 Avenue Henri Julien Montreal', 'V2S 2H7' ,  '604-853-8638', '366 987 444', 1, 1, 2 );

INSERT INTO staff (fname,lname, bdate, email, address, zip, phone, SSN, posid, qualid, hospid )
VALUES ('Shirley', 'Minor', '14-NOV-1967'  ,  'ShirleyJMinor@armyspy.com', '4293 Avenue de Esplanade Montreal' , 'T5J 2R4' ,  '780-996-5578', '726 768 716', 2, 2, 1);

INSERT INTO staff (fname,lname, bdate, email, address, zip, phone, SSN, posid, qualid, hospid )
VALUES ('Lane W.', 'Bentley', '18-JAN-1965' ,  'LaneWBentley@rhyta.com' , '252 Ch de la Côte-Sainte-Catherine Montreal', 'P0T 2L0'  ,  '807-349-5758', '090 601 576', 3, 1, 3);

INSERT INTO staff (fname,lname, bdate, email, address, zip, phone, SSN, posid, qualid, hospid )
VALUES ('Drew B.', 'Dalley', '23-DEC-1971',  'DrewBDalley@dayrep.com ', '444 Avenue Champagneur Montreal ', 'M4W 1J7' ,  '416-318-8430', '521 398 032', 3, 3, 3);

INSERT INTO staff (fname,lname, bdate, email, address, zip, phone, SSN, posid, qualid, hospid )
VALUES ('Frank I.', 'Sims', '27-JUL-1980' ,  'FrankISims@armyspy.com' , '274 67e Av Laval' , 'V7L 2C1' ,  '604-960-7790', '709 809 909', 1, 3, 4);

/*
patient

CREATE TABLE patientG (
id NUMBER(11) CONSTRAINT patientG_id PRIMARY KEY,
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
*/
INSERT INTO patient (id, fname, lname, bdate, email, address, zip, phone, medcard, insurrance, anamnesis, diagnosis, prescid)
VALUES (1, 'Carol B.', 'Chapman', 'March 21 1983', 'CarolBChapman@rhyta.com',  '1214 Avenue Shorecrest Laval', 'P0C 1A0', '514-555-0142' ,'CHAC 1234 5678', '1Z 132 162 58 7392 178 6', '552 180 598', 'Headache and fever (39.3 C). The general indisposition ', 'the flu', 1);

INSERT INTO patient (fname, lname, bdate, email, address, zip, phone, medcard, insurrance, anamnesis, diagnosis, prescid)
VALUES ('Margaret', 'Egan', 'December 25 1925' , 'MargaretDEgan@jourrapide.com','7493 Rue André Breton Laval', 'M5H 1P6', '514-435-0172'  ,'EGAM 5521 8059', '1Z 578 473 93 3515 049 9', '756 945 994','tingling, pins and needles or numbness, muscle weakness, very pronounced reflexes, muscle spasms, or difficulty in moving; difficulties with coordination and balance (ataxia); problems with speech or swallowing, visual problems (nystagmus, optic neuritis or double vision), feeling tired, acute or chronic pain, and bladder ','Multiple sclerosis',2);

INSERT INTO patient (fname, lname, bdate, email, address, zip, phone, medcard, insurrance, anamnesis, diagnosis, prescid)
VALUES ('Walter', 'Thompson', 'November 21 1955' , 'WalterLThompson@rhyta.com', '66 Rue Caumartin Laval', 'Y0B 1G', '514-785-0112' ,'THOW 1234 5678', '1Z 878 A10 27 1315 029 1', '226 724 300',' shortness of breath, fatigue, non-productive cough, angina pectoris, fainting or syncope' ,'Pulmonary hypertension ' , 'redness, itching and discomfort between the fingers', 'mycosis', 4);

INSERT INTO patient (fname, lname, bdate, email, address, zip, phone, medcard, insurrance, anamnesis, diagnosis, prescid)
VALUES ('Susan', 'Patterson', 'June 13 1958' , 'SusanWPatterson@jourrapide.com',  '12216 Rue Richer Montreal', 'V6B 3K9', '514-565-0122' ,'PATS 2267 2430', '11Z 891 W99 44 6594 797 8', '466 050 168', 'repetetive headaches', 'Migraine', 3);

INSERT INTO patient (fname, lname, bdate, email, address, zip, phone, medcard, insurrance, anamnesis, diagnosis, prescid)
VALUES ('John', 'Pinckney', 'March 21 1983', 'JohnIPinckney@armyspy.com' ,  '12751 Rue Tracy Montreal', 'V3C 4S7', '514-995-0170' ,'PINJ 4660 5016', '1Z 100 693 94 9107 530 0', '026 961 748','headaches particularly at the back of the head and in the morning, as well as lightheadedness, vertigo, tinnitus (buzzing or hissing in the ears), high pressure 1790/120', 'Hypertension', 5);

INSERT INTO patient (fname, lname, bdate, email, address, zip, phone, medcard, insurrance, anamnesis, diagnosis, prescid)
VALUES ('Andy', 'Evans', 'June 8 1945' , 'AndyMEvans@jourrapide.com' ,  '4740 Boulevard Saint Joseph Montreal', 'S4P 3Y2', '514-258-0106' , 'EVAA 0269 6174', '1Z 189 63E 17 4015 269 7', '473 349 066', 'Raised areas of inflamed skin covered with silvery white scaly skin on the elbows, knees, scalp, and back. Inflammation and exfoliation of the skin in these areas', 'Psoriasis', 6 );

/*
hospital

CREATE TABLE hospital (
id NUMBER(5) CONSTRAINT hospital_id_pk PRIMARY KEY,
name VARCHAR2(30),
address VARCHAR2(30),
phone VARCHAR2(17)
);
*/

INSERT INTO hospital (id, name, address, phone) VALUES (1, 'The Jewish General Hospital' , '3755 Cote Ste Catherine Montreal Qc', '514-340-8222');

INSERT INTO hospital (name, address, phone) VALUES ('St. Marys Hospital', '3830 Lacombe Avenue Montreal Qc' , '514-345-3511');

INSERT INTO hospital (name, address, phone) VALUES ('Lakeshore General Hospital' , '160 Stillview Suite 1249 Pointe-Claire Qc', '514-630-2081');

INSERT INTO hospital (name, address, phone) VALUES ('Montreal General Hospital', '1650 Cedar Avenue Montreal Qc', '514-934-1934');

INSERT INTO hospital (name, address, phone) VALUES ('CHU Sainte-Justine', '3175 Chemin de la Côte-Sainte-Catherine Montreal Qc', '514-345-4931');

/*

CREATE TABLE prescription (
id NUMBER(10) CONSTRAINT prescription_id_pk PRIMARY KEY,
prescription VARCHAR(100),
drugid NUMBER(5),
CONSTRAINT prescription_drugid_fk FOREIGN KEY (drugid) REFERENCES drugs(id)
);
*/

INSERT INTO prescription (id, prescription, drugid) 
VALUES(1, 'bed rest and TYLENOL Extra Strength, 3 times a day', 9);
INSERT INTO prescription (prescription, drugid) 
VALUES('Tizanidine, twice a day', 8);
INSERT INTO prescription (prescription, drugid) 
VALUES('SILDENAFIL, once a day', 3);
INSERT INTO prescription (prescription, drugid) 
VALUES('KETOCONAZOLE, once a day', 2);
INSERT INTO prescription (prescription, drugid) 
VALUES('VASOTEC, once a day', 5);
INSERT INTO prescription (prescription, drugid) 
VALUES('TAZORAC twice a day ', 7);


/*
 drugstores
 
 REATE TABLE drugstores (
id NUMBER(5) CONSTRAINT drugstores_id_pk PRIMARY KEY,
name VARCHAR2(30),
address VARCHAR2(30),
phone VARCHAR2(17),
openh VARCHAR(10)
);
*/
INSERT INTO drugstores (id, name, address, phone, openh) VALUES (1, 'Pharmaprix', '1500 Rue Sainte-Catherine Ouest', '514-933-4744', '8:00 am - 12:00 am daily');

INSERT INTO drugstores (name, address, phone, openh) VALUES ('Jean Coutu' , '7145 St Denis Montreal', '514-495-8686', '9:00 am - 10:00 pm daily' );

INSERT INTO drugstores (name, address, phone, openh) VALUES ('Proxim' , '7684 Blvd St. Michel Montreal', '514-725-4738', '9:00 am - 9:00 pm daily' );

INSERT INTO drugstores (name, address, phone, openh) VALUES ('Brunet Pharmacies Affiliees', '151 Atwater Av Montreal', '514-935-5637',  '9:00 am - 9:00 pm daily' );

INSERT INTO drugstores (name, address, phone, openh) VALUES ('Uniprix', '4349 Belanger Rue Montreal', '514-725-5273', '9:00 am - 9:00 pm daily' );

/*
drugs

CREATE TABLE drugs (
id NUMBER(5) CONSTRAINT drugs_id_pk PRIMARY KEY,
name VARCHAR2(30),
dose VARCHAR2(30),
price NUMBER(6,2),
avlb CHAR(1) DEFAULT 'N'
);
*/
INSERT INTO drugs (id, name, dose, price, avlb) VALUES (1, 'ACCOLATE', '20 MG TABLET', 135.80, 'Y');

INSERT INTO drugs (name, dose, price, avlb) VALUES ('KETOCONAZOLE', '2% CREAM', 12.46, 'N' );

INSERT INTO drugs (name, dose, price, avlb) VALUES ('SILDENAFIL', '20 MG TABLET', 24.31, 'Y' );

INSERT INTO drugs (name, dose, price, avlb) VALUES ('PREVACID', '15 MG CAPSULE', 300.77, 'Y' );

INSERT INTO drugs (name, dose, price, avlb) VALUES ('VASOTEC', '20 MG TABLET', 306.02, 'N' );

INSERT INTO drugs (name, dose, price, avlb) VALUES ('ELMIRON', '100 MG CAPSULE', 220.31, 'Y');

INSERT INTO drugs (name, dose, price, avlb) VALUES ('TAZORAC', '0.05% GEL' , 299.86, 'Y');

INSERT INTO drugs (name, dose, price, avlb) VALUES ('TIZANIDINE', '2 MG TABLET', 11.45, 'N'  );

INSERT INTO drugs (name, dose, price, avlb) VALUES ('TYLENOL Extra Strength' , '500 mg caplets', 12.99, 'Y');

/*
qualification

CREATE TABLE qualification (
id NUMBER(7) CONSTRAINT qualification_id_pk PRIMARY KEY,
qualdesc VARCHAR2(30)
);
*/

INSERT INTO qualification (id, qualdesc) VALUES (1, 'GP CatA');
INSERT INTO qualification (qualdesc) VALUES ('GP CatB');
INSERT INTO qualification (qualdesc) VALUES ('Physician CatB');
INSERT INTO qualification (qualdesc) VALUES ('Surgeon CatA');
INSERT INTO qualification (qualdesc) VALUES ('Registered Nurse');
INSERT INTO qualification (qualdesc) VALUES ('Medical Administrative Assistant');
INSERT INTO qualification (qualdesc) VALUES ('Medical Laboratory Assistant');
INSERT INTO qualification (qualdesc) VALUES ('Medical Laboratory Technologist');

/*
laboratory 

CREATE TABLE laboratory (
id NUMBER(5) CONSTRAINT laboratory_id_pk PRIMARY KEY,
labname  VARCHAR2(30),
labaddress  VARCHAR2(30),
labphone VARCHAR2(17)
);
*/
INSERT INTO laboratory (id, labname, labaddress, labphone) 
VALUES (1, 'CDL Medical Laboratories', '5990, ch de la Côte-des-Neiges, Montréal, QC H3S 1Z5', '514-344-8022');
INSERT INTO laboratory (labname, labaddress, labphone) 
VALUES ('Radiologie Varad ', '150, rue Sainte-Catherine O, Montréal, QC H2X 3Y2', '514-281-1355');
INSERT INTO laboratory (labname, labaddress, labphone) 
VALUES (' RMD Sommeil ','142-100, ch Rockland, Mont-Royal, QC H3P 2V9','514-341-2111');
INSERT INTO laboratory (labname, labaddress, labphone) 
VALUES ('Clinique Médicale Plexo ', '6100 Avenue du Boisé, Montréal, QC H3S 2W1', '514-251-9331');
INSERT INTO laboratory (labname, labaddress, labphone) 
VALUES ('PrélèvExpress Enr ', '510-201, ch du Club-Marin, Verdun, QC H3E 1T4', '514-644-7264');

/*
tests 
the lack of imagination results in the following 

CREATE TABLE tests (
id NUMBER(20) CONSTRAINT test_id_pk PRIMARY KEY,
res VARCHAR(1000),
ardate DATE,
depdate DATE,
labid NUMBER(5),
CONSTRAINT tests_labid_fk FOREIGN KEY (labid) REFERENCES laboratory(id)
);
*/
INSERT INTO tests (id, res, ardate, depdate, labid) VALUES (1, 'good', '2 July 2014', '3 July 2014', 1);
INSERT INTO tests (res) VALUES ('very good', '5 July 2014', '6 July 2014', 2);
INSERT INTO tests (res) VALUES ('not good', '7 July 2014','9 July 2014', 3);
INSERT INTO tests (res) VALUES ('excellent', '2 July 2014', '3 July 2014', 4 );
INSERT INTO tests (res) VALUES ('perfect', '5 July 2014', '7 July 2014', 5);
INSERT INTO tests (res) VALUES ('so so', '7 July 2014', '8 July 2014', 3);

/*
schedule 

CREATE TABLE schedule (
id NUMBER(7) CONSTRAINT schedule_id_pk PRIMARY KEY,
patientid NUMBER(11),
sdate DATE,
staffid NUMBER(7),
testid NUMBER(11),
CONSTRAINT schedule_id_patientid_pk PRIMARY KEY (id, patientid),
CONSTRAINT schedule_staffid_fk FOREIGN KEY (staffid) REFERENCES staff(id),
CONSTRAINT schedule_testid_fk FOREIGN KEY (testid) REFERENCES tests(id)
);
*/
INSERT INTO schedule (id, patientid, sdate, staffid, testid) 
VALUES (1, 1, '2 August 2014, 12:30 p.m.', 1, 1);
INSERT INTO schedule (patientid, sdate, staffid, testid) 
VALUES (2,'2 August 2014, 1:00 p.m.', 1, 2);
INSERT INTO schedule (patientid, sdate, staffid, testid) 
VALUES (3, '2 August 2014, 1:00 p.m.', 2, 3);
INSERT INTO schedule (patientid, sdate, staffid, testid) 
VALUES (1, '2 August 2014, 1:30 p.m.', 3, 4);
INSERT INTO schedule (patientid, sdate, staffid, testid) 
VALUES (4, '2 August 2014, 12:30 p.m.', 2, 5);


/*
CREATE TABLE position (
id NUMBER(7) CONSTRAINT position_id_pk PRIMARY KEY,
posdesc VARCHAR2(30)
);
*/
INSERT INTO position (id, posdesc)
VALUES (1, 'GP');
INSERT INTO position (id, posdesc)
VALUES (1, 'Nurse');
INSERT INTO position (id, posdesc)
VALUES (1, 'GP');