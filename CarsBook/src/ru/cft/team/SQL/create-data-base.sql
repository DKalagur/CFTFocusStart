DROP DATABASE autoguide;
CREATE DATABASE autoguide;
USE autoguide;

CREATE TABLE auto
(
	id INT AUTO_INCREMENT PRIMARY KEY,
	brand NVARCHAR(255) NOT NULL,
	model NVARCHAR(255) NOT NULL,
    bodyType NVARCHAR(255) NOT NULL,
    year INT NOT NULL
);

INSERT INTO auto (brand, model, bodyType, year)
VALUES ('VW', 'GOLF', 'хэтчбек', 2009), ('ВАЗ', '2115', 'седан', 2016), ('ЛАДА', 'ГРАНТА', 'хэтчбэк', 2018);