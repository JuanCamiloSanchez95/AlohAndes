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