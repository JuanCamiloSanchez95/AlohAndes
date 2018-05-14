CREATE TABLE Hostales(
id int,
nombreHostal varchar(32),
registroSI varchar(32) NOT NULL, 
registroCamara varchar(32) NOT NULL,
horarioApertura varchar(5) NOT NULL,
horarioCierre varchar(5) NOT NULL,
CONSTRAINT PKhostales PRIMARY KEY (id),
CONSTRAINT FKoperadorHostales FOREIGN KEY (id) REFERENCES Operadores(id)
);

CREATE TABLE Hoteles(
id int,
nombreHotel varchar(32),
categoria int NOT NULL,
registroSI varchar(32) NOT NULL, 
registroCamara varchar(32) NOT NULL,
CONSTRAINT PKhoteles PRIMARY KEY (id),
CONSTRAINT FKoperadorHoteles FOREIGN KEY (id) REFERENCES Operadores(id)
);

CREATE TABLE PersonaNatural(
id int,
vinculo varchar(32) NOT NULL,
CONSTRAINT PKpersonaNatural PRIMARY KEY (id),
CONSTRAINT FKoperadorPersonaNatural FOREIGN KEY (id) REFERENCES Operadores(id),
CONSTRAINT CKvinculoPersonaNat CHECK (vinculo IN ('Estudiante','Padre','Egresado','Empleado','Profesor','Evento','Profesor invitado'))
);

CREATE TABLE ViviendaUniversitaria(
id int,
nombreVivienda varchar(32) NOT NULL,
CONSTRAINT PKviviendaUniversitaria PRIMARY KEY (id),
CONSTRAINT FKoperadorVivUni FOREIGN KEY (id) REFERENCES Operadores(id)
);


