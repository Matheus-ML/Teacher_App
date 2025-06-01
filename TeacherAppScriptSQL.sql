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
PRIMARY KEY(cd_login)
);

INSERT INTO login (id_user, id_password) VALUES
("Karize", "karize123"),
("Golle", "golle123"),
("Guilherme", "guilherme123"),
("Henrique", "henrique123");



-- CRUD Activity
-- Create
INSERT INTO activity (nm_activity, ds_activity, dt_activity, wt_activity) VALUES (?,?,?,?)

-- List
SELECT cd_activity, nm_activity FROM activity

-- Delete
DELETE FROM activity WHERE cd_activity = ?

-- CRUD SchoolClass
-- Create
INSERT INTO schoolclass (nm_schoolclass, qt_student) VALUES (?,?)