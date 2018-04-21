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
CONSTRAINT CHvinculo CHECK (vinculo = 'Estudiante' OR vinculo ='Padre' OR vinculo ='Egresado'OR vinculo ='Empleado'OR vinculo ='Profesor'OR vinculo ='Evento'OR vinculo ='Profesor invitado')
);

--CREO OPERADORES
CREATE TABLE Operadores(
id int,
tipo varchar(32) NOT NULL,
 nombre varchar(32),CONSTRAINT CKtipo  CHECK (tipo = 'Hotel' OR tipo ='Hostal' OR tipo ='Persona' OR tipo='Vivienda Universitaria'
) ,CONSTRAINT PKoperadores PRIMARY KEY(id)
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
descripccion varchar(32),
costo double precision NOT NULL,
minimoPeriodo int,
CONSTRAINT PKaloj PRIMARY KEY (id)
);