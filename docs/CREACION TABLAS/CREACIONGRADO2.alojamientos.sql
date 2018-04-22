CREATE TABLE AlojamientosApartamentos(
id int,
amoblado char(1)  NOT NULL,
administracion double precision NOT NULL, 
CONSTRAINT PKapartamentos PRIMARY KEY (id),
CONSTRAINT CKamobladoAparto CHECK (amoblado = 'Y' OR amoblado='F'),
CONSTRAINT FKapartamentos FOREIGN KEY (id) REFERENCES Alojamientos(id)
);

CREATE TABLE HabitacionesH(
id int,
capacidad int NOT NULL,
tipo varchar(32) NOT NULL,
numeroHabitacion int NOT NULL UNIQUE,
ubicacionH varchar(32) NOT NULL,
tamano varchar(32),
CONSTRAINT PKHabitacionesH PRIMARY KEY (id),
CONSTRAINT FKalojHabitacionesH FOREIGN KEY  (id) REFERENCES Alojamientos (id)
);

CREATE TABLE HabitacionesUniversitarias(
id int,
compartida char(1) NOT NULL,
numeroHabitacion int NOT NULL UNIQUE,
capacidad int NOT NULL,
menaje varchar(32),
ubicacionH varchar(32) NOT NULL,
CONSTRAINT PKHabitacionesUniver PRIMARY KEY (id),
CONSTRAINT FKalojHabitacionUniv FOREIGN KEY  (id) REFERENCES Alojamientos (id),
CONSTRAINT CKcompartidaU CHECK (compartida='Y'  OR  compartida='N')
);

CREATE TABLE HabitacionesVivienda(
id int,
compartida char(1) NOT NULL,
urlEsquema varchar(32),
CONSTRAINT PKHabsVivi PRIMARY KEY (id),
CONSTRAINT FKalojamientoHabsVivi FOREIGN KEY (id) REFERENCES Alojamientos(id),
CONSTRAINT CKbooleanCompa CHECK (compartida in ( 'Y', 'N' ))
);

CREATE TABLE ALOJAMIENTOSOPERADORES(
operadorid int,
alojamientoid int,
CONSTRAINT PKalojamientosOperadores PRIMARY KEY(operadorid,alojamientoid),
CONSTRAINT FKalojamientosOpera FOREIGN KEY (alojamientoid) REFERENCES alojamientos(id),
CONSTRAINT FKoperadorAloj FOREIGN KEY (operadorid) REFERENCES operadores(id)
);
