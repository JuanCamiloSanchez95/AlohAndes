--CREO CLIENTES
CREATE  TABLE  Clientes(
documento int,
nombre varchar(32) NOT NULL,
vinculo varchar(32)NOT NULL,
CONSTRAINT PKclientes PRIMARY  KEY (documento),
CONSTRAINT CHvinculo CHECK (vinculo IN ('Estudiante','Padre','Egresado','Empleado','Profesor','Evento','Profesor invitado'))
);

--CREO OPERADORES
CREATE TABLE Operadores(
id int,
tipo varchar(32) NOT NULL,
 nombre varchar(32),
 CONSTRAINT CKtipo  CHECK (tipo IN ('Hotel','Hostal','Persona','Vivienda Universitaria')),
 CONSTRAINT PKoperadores PRIMARY KEY(id)
);

--CREO SERVICIOS
CREATE TABLE Servicios(
id int,
nombre varchar(32) NOT NULL,
descripcion varchar(32) NOT NULL, 
costo double precision NOT NULL,
CONSTRAINT CKcosto CHECK (costo>=0),
CONSTRAINT PKservicios PRIMARY KEY (id)
);

--CREO ALOJAMIENTOS
CREATE TABLE Alojamientos(
id int, 
tipo varchar(32) NOT NULL,
nombre varchar(32) NOT NULL,
ubicacion varchar(32) NOT NULL,
descripcion varchar(32),
costo double precision NOT NULL,
minimoPeriodo int NOT NULL,
CONSTRAINT PKaloj PRIMARY KEY (id),
CONSTRAINT CKtipoAloja  CHECK (tipo IN ('Apartamento','HabitacionHotel','HabitacionHostal','Vivienda','HabitacionVivienda','HabitacionUniversitaria')),
CONSTRAINT CKminimo CHECK (minimoPeriodo>=0),
CONSTRAINT CKcostoAloja CHECK (costo>0)
);


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

CREATE TABLE Apartamentos(
id int,
amoblado char(1)  NOT NULL,
administracion double precision NOT NULL, 
CONSTRAINT PKapartamentos PRIMARY KEY (id),
CONSTRAINT CKamobladoAparto CHECK (amoblado = 'Y' OR amoblado='N'),
CONSTRAINT FKapartamentos FOREIGN KEY (id) REFERENCES Alojamientos(id)
);

CREATE TABLE HabitacionesHotel(
id int,
capacidad int NOT NULL,
tipo varchar(32) NOT NULL,
numeroHabitacion int NOT NULL UNIQUE,
tamano varchar(32),
CONSTRAINT PKHabitacionesHotel PRIMARY KEY (id),
CONSTRAINT FKalojHabitacionesHotel FOREIGN KEY  (id) REFERENCES Alojamientos (id)
);

CREATE TABLE HabitacionesHostal(
id int,
capacidad int NOT NULL,
tipo varchar(32) NOT NULL,
numeroHabitacion int NOT NULL UNIQUE,
tamano varchar(32),
CONSTRAINT PKHabitacionesHostal PRIMARY KEY (id),
CONSTRAINT FKalojHabitacionesHostal FOREIGN KEY  (id) REFERENCES Alojamientos (id)
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

CREATE TABLE Ofertas(
id int,
precioEstadia double precision NOT NULL,
nombre  varchar(32) NOT NULL,
FechaPublicacion Date NOT NULL,
descripcion varchar(32),
alojamientoId int NOT NULL,
operador int NOT NULL,
capacidad int not null,
CONSTRAINT PKoferta PRIMARY KEY (id),
CONSTRAINT FKofertaAlojamiento FOREIGN KEY (alojamientoId) REFERENCES Alojamientos(id),
CONSTRAINT FKoperaAloj FOREIGN KEY (operador) REFERENCES Operadores(id)
);

--CREO RESERVAS
CREATE TABLE Reservas(
id int,
FechaLlegada Date NOT NULL,
recargo double precision NOT NULL,
cantidadDias  int NOT NULL,
oferta int NOT NULL,
vigente char(1) NOT NULL,
CONSTRAINT CKvigente CHECK (vigente = 'Y' OR vigente='N'),
CONSTRAINT PKreservaaa PRIMARY KEY (id),
CONSTRAINT FKofertaRese FOREIGN KEY (oferta) REFERENCES Ofertas(id)
);

CREATE TABLE  RESERVASCLIENTE(
clienteid int,
reservaid int, 
CONSTRAINT PKreservascliente PRIMARY KEY (clienteid,reservaid),
CONSTRAINT FKclienteidres FOREIGN KEY (clienteid) REFERENCES clientes(documento),
CONSTRAINT FKreservasClie FOREIGN KEY (reservaid) REFERENCES reservas (id)
);

CREATE TABLE ServiciosDeAlojamientos(
servicio int,
alojamiento int,
CONSTRAINT PKServiciosDeAlojamientos PRIMARY KEY(servicio,alojamiento),
CONSTRAINT FKalojamientoServsAlojs FOREIGN KEY (alojamiento) REFERENCES alojamientos(id),
CONSTRAINT FKservicioServsAlojs FOREIGN KEY (servicio) REFERENCES Servicios(id)
);

CREATE TABLE ServiciosDeOperadores(
servicio int,
operador int,
CONSTRAINT PKServiciosOperadores PRIMARY KEY(operador,servicio),
CONSTRAINT FKoperadorServiciosOperadores FOREIGN KEY (operador) REFERENCES Operadores(id),
CONSTRAINT FKservicioServiciosOperadores FOREIGN KEY (servicio) REFERENCES Servicios(id)
);