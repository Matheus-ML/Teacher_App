CREATE DATABASE TeacherApp;

CREATE TABLE SchoolClass
(
cd_schoolclass INTEGER AUTO_INCREMENT,
nm_schoolclass VARCHAR(40) NOT NULL,
qt_student INTEGER NOT NULL,
PRIMARY KEY(cd_schoolclass)
);

CREATE TABLE Activity
(
cd_activity INTEGER AUTO_INCREMENT,
nm_activity VARCHAR(100) NOT NULL,
ds_activity VARCHAR(255),
dt_activity DATE NOT NULL,
wt_activity DOUBLE,
cd_schoolclass INTEGER,
PRIMARY KEY (cd_activity),
FOREIGN KEY(cd_schoolclass) REFERENCES schoolclass (cd_schoolclass) ON DELETE CASCADE
);

CREATE TABLE login 
(
cd_login INTEGER AUTO_INCREMENT,
id_user VARCHAR(50) UNIQUE,
id_password VARCHAR(16) NOT NULL,
id_nameProfessor VARCHAR(100) NOT NULL,
PRIMARY KEY(cd_login)
);

-- INSERT INTO login (id_user, id_password,id_nameProfessor) VALUES
-- ("Karize", "karize123", "Angelica Karize Viecelli"),
-- ("Golle", "golle123", "Jorge Antonio Golle"),
-- ("Guilherme", "guilherme123", "Guilherme Rafael Deschamps"),
-- ("Henrique", "henrique123", "Henrique Delegrego"),
-- ("","","");
