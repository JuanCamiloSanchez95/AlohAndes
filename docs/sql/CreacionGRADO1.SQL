--CREO Alohandes
CREATE TABLE AlohAndes(
id int,
nombre varchar(32) NOT NULL,CONSTRAINT PKcliente PRIMARY KEY (id)
);


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
CONSTRAINT CKminimo CHECK (minimoPeriodo>=0),
CONSTRAINT CKcostoAloja CHECK (costo>0)
);