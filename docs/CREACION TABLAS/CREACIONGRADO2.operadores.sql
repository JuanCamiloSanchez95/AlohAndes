CREATE TABLE OPERADORESREGISTRADOS(
alohaid int, 
Operadorid int,
CONSTRAINT PKoperadorReg PRIMARY KEY (alohaid,operadorid),
CONSTRAINT FKoperadoralo FOREIGN KEY (operadorid) references operadores(id),
CONSTRAINT FKAlojaopera FOREIGN KEY (alohaid) references Alohandes (id)
);


CREATE TABLE Hostales(
id int,
nombre varchar(32) NOT NULL,
registroSI varchar(32) NOT NULL, 
registroCamara varchar(32) NOT NULL,
horarioApertura varchar(32) NOT NULL,
horarioCierre varchar(32) NOT NULL,
CONSTRAINT PKhostales PRIMARY KEY (id),
CONSTRAINT FKoperadorHostales FOREIGN KEY (id) REFERENCES Operadores(id)
);

CREATE TABLE Hoteles(
id int,
categoria int NOT NULL,
nombre varchar(32) NOT NULL,
registroSI varchar(32) NOT NULL, 
registroCamara varchar(32) NOT NULL,
CONSTRAINT PKhoteles PRIMARY KEY (id),
CONSTRAINT FKoperadorHoteles FOREIGN KEY (id) REFERENCES Operadores(id)
);

CREATE TABLE PersonaNatural(
id int,
vinculo varchar(32) NOT NULL,
nombre varchar(32) NOT NULL,
CONSTRAINT PKpersonaNatural PRIMARY KEY (id),
CONSTRAINT FKoperadorPersonaNatural FOREIGN KEY (id) REFERENCES Operadores(id),
CONSTRAINT CKvinculoPersonaNat CHECK (vinculo = 'estudiante' OR vinculo ='padre'OR vinculo ='egresado'
OR vinculo ='empleado'OR vinculo ='profesor'OR vinculo ='evento'OR vinculo ='profinvitado')
);

CREATE TABLE ViviendaUniversitaria(
id int,
nombre varchar(32) NOT NULL,
CONSTRAINT PKviviendaUniversitaria PRIMARY KEY (id),
CONSTRAINT FKoperadorVivUni FOREIGN KEY (id) REFERENCES Operadores(id)
);

CREATE TABLE Seguros(
id int NOT NULL,
nombre varchar(32) NOT NULL,
costo double precision NOT NULL,
CONSTRAINT PKseguro PRIMARY KEY(id)
);


CREATE TABLE Viviendas(
id int  not null,
numeroHabitaciones  int NOT NULL,
menaje varchar(32) NOT NULL,
idS int NOT NULL,
CONSTRAINT PKVivienda PRIMARY KEY (id),
CONSTRAINT FKvivienda FOREIGN KEY (id) REFERENCES Alojamientos(id),
CONSTRAINT FKsegura FOREIGN KEY (idS) REFERENCES Seguros(id)
);

