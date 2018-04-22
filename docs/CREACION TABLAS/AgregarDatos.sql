insert into AlohAndes(id,nombre) values (1,'AlohAndes');

--CLIENTES
insert into  Clientes(documento, nombre,vinculo) values(98713,'Mateo','Estudiante');
insert into  Clientes(documento, nombre,vinculo) values(3213124,'Juan','Estudiante');
insert into  Clientes(documento, nombre,vinculo) values(312321312,'Javier','Profesor');
insert into  Clientes(documento, nombre,vinculo) values(821932,'Joshua','Evento');
insert into  Clientes(documento, nombre,vinculo) values(2312321,'Js','Egresado');
insert into  Clientes(documento, nombre,vinculo) values(9819432,'Jaa','Padre');

--OPERADORES
insert into Operadores(id,tipo,nombre) values (1,'Hotel','Tequendama');
insert into Operadores(id,tipo,nombre) values (2,'Hotel','Inter');
insert into Operadores(id,tipo,nombre) values (3,'Hostal','NocheBuena' );
insert into Operadores(id,tipo,nombre) values (4,'Hostal', 'FirstNuit');
insert into Operadores(id,tipo,nombre) values (5,'Persona', 'Rafael Garzon');
insert into Operadores(id,tipo,nombre) values (6,'Persona', 'Sandra Gibril');
insert into Operadores(id,tipo,nombre) values (7,'Vivienda Universitaria','CityU');
insert into Operadores(id,tipo,nombre) values (8,'Vivienda Universitaria','FeniciaTower');

--SERVICIOS
insert into Servicios(id,nombre,descripcion,costo) values (1,'Ba�era','Ba�o comodo',0);
insert into Servicios(id,nombre,descripcion,costo) values (2,'Yacuzzi', 'Yumi',2313213);
insert into Servicios(id,nombre,descripcion,costo) values (3,'Sala','quiero vagar',0);
insert into Servicios(id,nombre,descripcion,costo) values (4,'Cocineta', 'Plus ',32391);
insert into Servicios(id,nombre,descripcion,costo) values (5,'Wifi', 'Conexion inalambrica',31235);
insert into Servicios(id,nombre,descripcion,costo) values (6,'Recepccion 24', 'dsadsa',0);
insert into Servicios(id,nombre,descripcion,costo) values (7,'Luz', 'dsadas',0);
insert into Servicios(id,nombre,descripcion,costo) values (8,'Agua', 'Cadad',0);
insert into Servicios(id,nombre,descripcion,costo) values (9,'Internet', 'Conexion',31324);
insert into Servicios(id,nombre,descripcion,costo) values (10,'Telefono', 'ewrwr',0);
insert into Servicios(id,nombre,descripcion,costo) values (11,'Restaurante', 'dasdasda',0);
insert into Servicios(id,nombre,descripcion,costo) values (12,'Piscina', 'Cdsadasd',0);
insert into Servicios(id,nombre,descripcion,costo) values (13,'Parqueadero', 'dasada',0);
insert into Servicios(id,nombre,descripcion,costo) values (14,'TV', 'erwrw',232536);

--ALOJAMIENTOS
insert into Alojamientos(id,tipo,nombre,ubicacion,descripccion,costo,minimoperiodo)values (1,'general','Nombre','Ubicacion','Descripcion',12.0,30);
insert into Alojamientos(id,tipo,nombre,ubicacion,descripccion,costo,minimoperiodo)values (2,'general','Nombre','Ubicacion','Descripcion',12.0,30);
insert into Alojamientos(id,tipo,nombre,ubicacion,descripccion,costo,minimoperiodo)values (3,'general','Nombre','Ubicacion','Descripcion',12.0,30);
insert into Alojamientos(id,tipo,nombre,ubicacion,descripccion,costo,minimoperiodo)values (4,'general','Nombre','Ubicacion','Descripcion',12.0,30);
insert into Alojamientos(id,tipo,nombre,ubicacion,descripccion,costo,minimoperiodo)values (5,'general','Nombre','Ubicacion','Descripcion',12.0,30);
insert into Alojamientos(id,tipo,nombre,ubicacion,descripccion,costo,minimoperiodo)values (6,'general','Nombre','Ubicacion','Descripcion',12.0,30);
insert into Alojamientos(id,tipo,nombre,ubicacion,descripccion,costo,minimoperiodo)values (7,'general','Nombre','Ubicacion','Descripcion',12.0,30);
insert into Alojamientos(id,tipo,nombre,ubicacion,descripccion,costo,minimoperiodo)values (8,'general','Nombre','Ubicacion','Descripcion',12.0,30);
insert into Alojamientos(id,tipo,nombre,ubicacion,descripccion,costo,minimoperiodo)values (9,'general','Nombre','Ubicacion','Descripcion',12.0,30);
insert into Alojamientos(id,tipo,nombre,ubicacion,descripccion,costo,minimoperiodo)values (10,'general','Nombre','Ubicacion','Descripcion',12.0,30);

--OFERTAS
insert into Ofertas(id,precioEstadia,nombre,FechaPublicacion,descripcion, alojamientoId,operador,capacidad) values (1, 321312.421,'jasjdja',TO_DATE('24/02/2018', 'DD/MM/YYYY'),'mashu  paila',1,1,6);
insert into Ofertas(id,precioEstadia,nombre,FechaPublicacion,descripcion, alojamientoId,operador,capacidad) values (2, 321312.421,'jasjdja',TO_DATE('04/02/2018', 'DD/MM/YYYY'),'sdadsadsa',2,2,3);
insert into Ofertas(id,precioEstadia,nombre,FechaPublicacion,descripcion, alojamientoId,operador,capacidad) values (3, 321312.421,'jasjdja',TO_DATE('04/02/2018', 'DD/MM/YYYY'),'dsadasdsa',3,3,5);
insert into Ofertas(id,precioEstadia,nombre,FechaPublicacion,descripcion, alojamientoId,operador,capacidad) values (4, 321312.421,'jasjdja',TO_DATE('04/02/2018', 'DD/MM/YYYY'),'msdadsada',4,4,10);
insert into Ofertas(id,precioEstadia,nombre,FechaPublicacion,descripcion, alojamientoId,operador,capacidad) values (5, 321312.421,'jasjdja',TO_DATE('04/02/2018', 'DD/MM/YYYY'),'mdsadadsa',5,5,10);
insert into Ofertas(id,precioEstadia,nombre,FechaPublicacion,descripcion, alojamientoId,operador,capacidad) values (6, 321312.421,'jasjdja',TO_DATE('04/02/2018', 'DD/MM/YYYY'),'masadasdaila',6,7,100);
insert into Ofertas(id,precioEstadia,nombre,FechaPublicacion,descripcion, alojamientoId,operador,capacidad) values (7, 321312.421,'jasjdja',TO_DATE('04/02/2018', 'DD/MM/YYYY'),'gsdfdgfda',7,6,10);

--HOSTALES
insert into Hostales(id,nombre,registroSI,registroCamara,horarioApertura,horarioCierre) values (3,'Noche buena','21932131219','38127382173','se abre a las  7:00 am', 'se cierra a las  7:00pm');
insert into Hostales(id,nombre,registroSI,registroCamara,horarioApertura,horarioCierre) values (4,'FirstNuit','21931219','381231382173','se abre a las  7:00 am', 'se cierra a las  9:00pm');

--PERSONA NATURAL
insert into PersonaNatural(id, vinculo , nombre) values (5,'estudiante','Rafael Garzon');
insert into PersonaNatural(id, vinculo , nombre) values (6,'profesor','Sandra Gibril');

--HOTELES
insert into Hoteles(id,categoria,nombre,registroSI,registroCamara) values (1, 5, 'Tequendama','219331331219','381273862173');
insert into Hoteles(id,categoria,nombre,registroSI,registroCamara) values (2, 3, 'Inter','2193213123123119','381273821873');

--VIVIENDA UNIVERSITARIA
insert into ViviendaUniversitaria(id,nombre) values (7,'CityU');
insert into ViviendaUniversitaria(id,nombre) values (8,'Fenicia Tower');

--OPERADORES REGISTRADOS
insert into operadoresregistrados(alohaid, operadorid)values(1,1);
insert into operadoresregistrados(alohaid, operadorid)values(1,2);
insert into operadoresregistrados(alohaid, operadorid)values(1,3);
insert into operadoresregistrados(alohaid, operadorid)values(1,4);
insert into operadoresregistrados(alohaid, operadorid)values(1,5);
insert into operadoresregistrados(alohaid, operadorid)values(1,6);
insert into operadoresregistrados(alohaid, operadorid)values(1,7);
insert into operadoresregistrados(alohaid, operadorid)values(1,8);

--RESERVAS
insert into reservas(id,fechallegada,cantidadDias,recargo,oferta) values(1,TO_DATE('23/03/2018', 'DD/MM/YYYY'),3,0,2);
insert into reservas(id,fechallegada,cantidadDias,recargo,oferta) values(2,TO_DATE('22/03/2018', 'DD/MM/YYYY'),3,0,3);
insert into reservas(id,fechallegada,cantidadDias,recargo,oferta) values(3,TO_DATE('28/03/2018', 'DD/MM/YYYY'),4,0,4);
insert into reservas(id,fechallegada,cantidadDias,recargo,oferta) values(4,TO_DATE('12/03/2018', 'DD/MM/YYYY'),3,0,7);

--APARTAMENTO
insert into alojamientosApartamentos(id,amoblado,administracion) values (7,'Y',322173871);

--HABITACIONES HOTEL
insert into HabitacionesH(id,capacidad,tipo,numeroHabitacion,ubicacionH,tamano) values(6,3,'Suite',124,'Torre al lado de la piscina','Grande');

--HABITACION UNIVERSITARIA
insert into HabitacionesUniversitarias(id, compartida, numeroHabitacion,capacidad,menaje,ubicacionH) values (2,'Y', 312,3,'wddasda','dasdadas');
insert into HabitacionesUniversitarias(id, compartida, numeroHabitacion,capacidad,menaje,ubicacionH) values (4,'N', 432,1,'dadsad','dasdwr');

--HABITACION VIVIENDA
insert into habitacionesVivienda(id,compartida,urlEsquema) values(1,'Y','WEQEWQEQ');

--ALOJAMIENTOS OPERADORES
insert into alojamientosoperadores(operadorid, alojamientoid) values (5,1);
insert into alojamientosoperadores(operadorid, alojamientoid) values (5,2);
insert into alojamientosoperadores(operadorid, alojamientoid) values (6,3);
insert into alojamientosoperadores(operadorid, alojamientoid) values (8,4);
insert into alojamientosoperadores(operadorid, alojamientoid) values (5,5);
insert into alojamientosoperadores(operadorid, alojamientoid) values (1,6);
insert into alojamientosoperadores(operadorid, alojamientoid) values (6,7);

--SEGUROS
insert into seguros(id,nombre,costo) values (1,'ksadajdka', 31231.31);
insert into seguros(id,nombre,costo) values (2,'weqeqweqe', 321312.312);
insert into seguros(id,nombre,costo) values (3,'dipsaudpi', 318923812.321);

--VIVIENDAS
insert into viviendas(id,numeroHabitaciones,menaje,idS) values (3,2,'dsaakj',1);
insert into viviendas(id,numeroHabitaciones,menaje,idS) values (5,3,'dsaakj',2);

--CLIENTES REGISTRADOS
insert into ClientesRegistrados(idCliente,idAloha)values(98713,1);
insert into ClientesRegistrados(idCliente,idAloha)values(3213124,1);
insert into ClientesRegistrados(idCliente,idAloha)values(312321312,1);
insert into ClientesRegistrados(idCliente,idAloha)values(821932,1);
insert into ClientesRegistrados(idCliente,idAloha)values(2312321,1);
insert into ClientesRegistrados(idCliente,idAloha)values(9819432,1);

--RESERVAS CLIENTE
insert into reservascliente(CLIENTEID,reservaid) values (98713,1);
insert into reservascliente(CLIENTEID,reservaid) values (3213124,2);
insert into reservascliente(CLIENTEID,reservaid) values (821932,3);
insert into reservascliente(CLIENTEID,reservaid) values (9819432,4);

--SERVICIOS DE ALOJAMIENTO
insert into SERVICIOSDEALOJAMIENTOS(SERVICIO,ALOJAMIENTO) values (1,2);
insert into SERVICIOSDEALOJAMIENTOS(SERVICIO,ALOJAMIENTO) values (2,2);
insert into SERVICIOSDEALOJAMIENTOS(SERVICIO,ALOJAMIENTO) values (3,2);
insert into SERVICIOSDEALOJAMIENTOS(SERVICIO,ALOJAMIENTO) values (4,2);
insert into SERVICIOSDEALOJAMIENTOS(SERVICIO,ALOJAMIENTO) values (1,3);
insert into SERVICIOSDEALOJAMIENTOS(SERVICIO,ALOJAMIENTO) values (2,3);
insert into SERVICIOSDEALOJAMIENTOS(SERVICIO,ALOJAMIENTO) values (1,1);
insert into SERVICIOSDEALOJAMIENTOS(SERVICIO,ALOJAMIENTO) values (2,1);
insert into SERVICIOSDEALOJAMIENTOS(SERVICIO,ALOJAMIENTO) values (12,2);
insert into SERVICIOSDEALOJAMIENTOS(SERVICIO,ALOJAMIENTO) values (10,2);

--SERVICIOS DE OPERADOR
insert into ServiciosDeOperadores(servicio, operador) values (1,1);
insert into ServiciosDeOperadores(servicio, operador) values (1,2);
insert into ServiciosDeOperadores(servicio, operador) values (1,3);
insert into ServiciosDeOperadores(servicio, operador) values (2,4);
insert into ServiciosDeOperadores(servicio, operador) values (1,4);
insert into ServiciosDeOperadores(servicio, operador) values (3,7);
insert into ServiciosDeOperadores(servicio, operador) values (10,6);
insert into ServiciosDeOperadores(servicio, operador) values (11,7);
insert into ServiciosDeOperadores(servicio, operador) values (9,8);
insert into ServiciosDeOperadores(servicio, operador) values (4,4);
insert into ServiciosDeOperadores(servicio, operador) values (5,5);
insert into ServiciosDeOperadores(servicio, operador) values (6,8);
