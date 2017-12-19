DROP DATABASE IF EXISTS Bioscoop;
CREATE DATABASE Bioscoop;
USE Bioscoop;

CREATE TABLE Film (
	FilmID int NOT NULL AUTO_INCREMENT,
	Titel varchar(40) NOT NULL,
	Beschrijving varchar(500),
	Regisseur varchar(40) NOT NULL,
	Jaar int,
	Tijd int NOT NULL,
	Genre varchar(20),
	CONSTRAINT PK PRIMARY KEY (FilmID)
);

DELETE FROM Film;
INSERT INTO Film (Titel, Regisseur, Jaar, Tijd, Genre, Beschrijving) VALUES ('De Database','Alexander van den Bulck',2017,120,'Horror','Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim.');
INSERT INTO Film (Titel, Regisseur, Jaar, Tijd, Genre, Beschrijving) VALUES ('Het Programma','Robin Schellius',2016,180,'Avontuur','Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim.' );
INSERT INTO Film (Titel, Regisseur, Jaar, Tijd, Genre, Beschrijving) VALUES ('Het koffiescript','Atal Kakar',2015,120,'Misdaad','Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim.');
INSERT INTO Film (Titel, Regisseur, Jaar, Tijd, Genre, Beschrijving) VALUES ('Doctor Chicago','Monique van der Heijden',1961,180,'Romantiek','Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim.');
INSERT INTO Film (Titel, Regisseur, Jaar, Tijd, Genre, Beschrijving) VALUES ('De Penguins','Ruud Hermans',2014,180,'Misdaad','Ruud in een penguin pak');
INSERT INTO Film (Titel, Regisseur, Jaar, Tijd, Genre, Beschrijving) VALUES ('De Zweetvlek','Rick Hamers',2017,30,'Horror','Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim.');
INSERT INTO Film (Titel, Regisseur, Jaar, Tijd, Genre, Beschrijving) VALUES ('De stekkerdoos','Arantxio',2017,180,'Techniek','Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Aenean commodo ligula eget dolor. Aenean massa. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Donec quam felis, ultricies nec, pellentesque eu, pretium quis, sem. Nulla consequat massa quis enim.');

CREATE TABLE Locatie (
	Naam varchar(40) NOT NULL UNIQUE,
	Straat varchar(40) NOT NULL,
	Huisnummer int NOT NULL,
	Stad varchar(30) NOT NULL,
	CONSTRAINT LocatiePK PRIMARY KEY (Naam)
);

INSERT INTO Locatie(Naam, Straat, Huisnummer, Stad) VALUES ('Kinepolis','Belgi�staat',20,'Antwerpen');
INSERT INTO Locatie(Naam, Straat, Huisnummer, Stad) VALUES ('BOZScoop','Bozstaat',20,'Bergen op Zoom');

CREATE TABLE Zaal (
	ZaalID varchar(20) NOT NULL,
	Rijen int NOT NULL,
	Kolommen int NOT NULL,
	Locatie varchar(40) NOT NULL,
	CONSTRAINT ZaalPK PRIMARY KEY (ZaalID),
	CONSTRAINT LocatieZaal
	FOREIGN KEY (Locatie)
	REFERENCES Locatie(Naam)
		ON UPDATE CASCADE
		ON DELETE CASCADE
);

INSERT INTO Zaal (Rijen, Kolommen, ZaalID, Locatie) VALUES (15,14,'BOZ-001','BOZScoop');
INSERT INTO Zaal (Rijen, Kolommen, ZaalID, Locatie) VALUES (10,14,'KIN-001','Kinepolis');
INSERT INTO Zaal (Rijen, Kolommen, ZaalID, Locatie) VALUES (40,10,'KIN-002','Kinepolis');
INSERT INTO Zaal (Rijen, Kolommen, ZaalID, Locatie) VALUES (10,10,'BOZ-002','BOZScoop');

CREATE TABLE Voorstelling (
	VoorstellingID int AUTO_INCREMENT NOT NULL PRIMARY KEY,
	FilmID int NOT NULL,
	Datum datetime NOT NULL,
	ZaalID varchar(20) NOT NULL,
	Bedrag decimal(4,2),
	FilmUitvoering varchar(40),
	CONSTRAINT ZaalConstraint
	FOREIGN KEY (ZaalID)
	REFERENCES Zaal(ZaalID)
		ON UPDATE CASCADE
		ON DELETE CASCADE,
	CONSTRAINT FilmConstraint
	FOREIGN KEY (FilmID)
	REFERENCES Film(FilmID)
		ON UPDATE CASCADE
		ON DELETE CASCADE
);

INSERT INTO Voorstelling (FilmID, Datum, ZaalID, Bedrag, FilmUitvoering) VALUES (1,CONVERT(datetime,'Oct 09 12:18:52 2017'),'BOZ-001',9.00,'3D');
INSERT INTO Voorstelling (FilmID, Datum, ZaalID, Bedrag, FilmUitvoering) VALUES (2,CONVERT(datetime,'Nov 11 15:30:00 2017'),'KIN-001',8.00,'2D');
INSERT INTO Voorstelling (FilmID, Datum, ZaalID, Bedrag, FilmUitvoering) VALUES (3,CONVERT(datetime,'Dec 28 10:50:00 2017'),'BOZ-002',10.00,'3D');
INSERT INTO Voorstelling (FilmID, Datum, ZaalID, Bedrag, FilmUitvoering) VALUES (4,CONVERT(datetime,'Oct 25 7:20:00 2017'),'KIN-002',5.00,'3D');
INSERT INTO Voorstelling (FilmID, Datum, ZaalID, Bedrag, FilmUitvoering) VALUES (5,CONVERT(datetime,'Dec 30 4:50:00 2017'),'KIN-002',10.00,'3D');
INSERT INTO Voorstelling (FilmID, Datum, ZaalID, Bedrag, FilmUitvoering) VALUES (6,CONVERT(datetime,'Nov 21 02:00:00 2017'),'KIN-001',11.00,'2D');
INSERT INTO Voorstelling (FilmID, Datum, ZaalID, Bedrag, FilmUitvoering) VALUES (7,CONVERT(datetime,'Oct 30 14:25:00 2017'),'BOZ-001',9.00,'2D');


CREATE TABLE Kaart (
	KaartID int AUTO_INCREMENT NOT NULL PRIMARY KEY,
	VoorstellingID int NOT NULL,
	Stoelnummer int,
	Rijnummer int,
	Code varchar(50),
	CONSTRAINT KaartVoorstelling
	FOREIGN KEY (VoorstellingID)
	REFERENCES Voorstelling(VoorstellingID)
	ON UPDATE CASCADE
	ON DELETE CASCADE
);

INSERT INTO Kaart (VoorstellingID, Stoelnummer, Rijnummer) VALUES (1,1,1);
INSERT INTO Kaart (VoorstellingID, Stoelnummer, Rijnummer) VALUES (1,2,1);
INSERT INTO Kaart (VoorstellingID, Stoelnummer, Rijnummer) VALUES (1,3,1);
INSERT INTO Kaart (VoorstellingID, Stoelnummer, Rijnummer) VALUES (1,4,1);
INSERT INTO Kaart (VoorstellingID, Stoelnummer, Rijnummer) VALUES (2,7,2);
INSERT INTO Kaart (VoorstellingID, Stoelnummer, Rijnummer) VALUES (2,7,5);
INSERT INTO Kaart (VoorstellingID, Stoelnummer, Rijnummer) VALUES (2,7,4);
INSERT INTO Kaart (VoorstellingID, Stoelnummer, Rijnummer) VALUES (2,7,3);
INSERT INTO Kaart (VoorstellingID, Stoelnummer, Rijnummer) VALUES (3,1,1);
INSERT INTO Kaart (VoorstellingID, Stoelnummer, Rijnummer) VALUES (3,2,1);
INSERT INTO Kaart (VoorstellingID, Stoelnummer, Rijnummer) VALUES (3,2,2);
INSERT INTO Kaart (VoorstellingID, Stoelnummer, Rijnummer) VALUES (2,2,2);

SHOW TABLES;
